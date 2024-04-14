package com.example.demo.basic;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.example.demo.basic")
public class AppArchTest {

    @ArchTest
    ArchRule myRule = classes()
        .that().haveSimpleNameEndingWith("Service")
        .should().onlyBeAccessed().byClassesThat(new DescribedPredicate<JavaClass>("are controllers or services") {
            @Override
            public boolean apply(JavaClass input) {
                return input.getSimpleName().endsWith("Controller") || input.getSimpleName().endsWith("Service");
            }
        });

    @ArchTest
    ArchRule validatorClassRule = noClasses().that().haveSimpleNameEndingWith("Validator")
        .should().accessClassesThat().haveSimpleNameEndingWith("Service");
}
