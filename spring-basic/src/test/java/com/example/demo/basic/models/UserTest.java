package com.example.demo.basic.models;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.example.demo.basic")
class UserTest {

    @Test
    @DisplayName("models 패키지 클래스는 다른 패키지에서 참조 가능.")
    void packageDependencyTest() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.example.demo.basic.models");

        ArchRule domainPackageRule = classes().that().resideInAPackage("..models..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..controller..", "..aop..", "..service..", "..repository..", "..models..");

        domainPackageRule.check(classes);
    }

    // 위와 같은 코드 Display Name을 못쓰는게 아쉽다. 확장해서 사용해야 함
    @ArchTest
    ArchRule domainPackageRule = classes().that().resideInAPackage("..models..")
        .should().onlyBeAccessed().byClassesThat()
        .resideInAnyPackage("..controller..", "..aop..", "..service..", "..repository..", "..models..");

    @Test
    @DisplayName("models 패키지는 ioc 패키지를 참조하지 못한다.")
    void packageDependencyTest2() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.example.demo.basic.models");

        ArchRule domainPackageRule = noClasses().that().resideInAPackage("..models..")
            .should().accessClassesThat().resideInAPackage("..ioc..");

        domainPackageRule.check(classes);
    }

    @ArchTest
    ArchRule domainPackageRule2 = noClasses().that().resideInAPackage("..models..")
        .should().accessClassesThat().resideInAPackage("..ioc..");

    @Test
    @DisplayName("basic 패키지 내의 클래스는 basic 패키지에서만 참조 가능")
    void packageDependencyTest3() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.example.demo.basic");

        ArchRule domainPackageRule = classes().that().resideInAPackage("..basic..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..basic..");

        domainPackageRule.check(classes);
    }

    @Test
    @DisplayName("순환 참조는 없어야 한다.")
    void packageDependencyTest4() {
        ArchRule freeOfCycles = slices().matching("..demo.(*)..")
            .should().beFreeOfCycles();
    }
}
