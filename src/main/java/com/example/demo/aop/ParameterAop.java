package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

//    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
//    private void cut() {
//
//    }

    // 메소드가 실행하기 전
//    @Before("cut()")
//    public void before(JoinPoint joinPoint) {
//        // 메소드 이름 출력
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        System.out.println(method.getName());
//
//        // Request Param 값 출력
//        Object[] args = joinPoint.getArgs();
//
//        for (Object obj : args) {
//            System.out.println("type : " + obj.getClass().getSimpleName());
//            System.out.println("value : " + obj);
//        }
//    }

    // 메소드 실행 후
//    @AfterReturning(value = "cut()", returning = "returnObj")
//    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
//        System.out.println("return obj");
//        System.out.println(returnObj);
//    }
}
