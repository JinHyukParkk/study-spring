package com.example.exampletest.postcontruct.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    //    @Around("execution(* com.example..*(..))")
//    @Around("execution(* com.example.exampletest.service..*(..))")
    @Around("@annotation(com.example.exampletest.postcontruct.aop.TimeTrace)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toLongString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toLongString() + " " + timeMs + "ms");
        }
    }
}
