package com.example.exampletest.reflection;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 주석과 같은 취급 바이트 코드로 빌드된 Class를 로딩했을 때 메모리 상에는 남지 않음
 */

@Retention(RetentionPolicy.RUNTIME)  // 런타임 시점에 읽어오고 싶다.
@Target({ElementType.TYPE, ElementType.FIELD}) // 타입과 필드에 붙인다.
@Inherited  // 하위 타입에서 리플랙션 API를 통해 해당 어노테이션 찾기 가능
public @interface MyAnnotation {

    // default 값이 없으면 설정 시 값을 주어야 함
    String name() default "hyuk";

    int number() default 100;

    // 만약 value()를 지정한다면 설정 시 따로 값 안줘도 됨
}
