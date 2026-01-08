package com.application.booker.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.application.booker.serviceImpl.BookServiceImpl.getAllBooks(..))")
    public void logBefore() {
        System.out.println("LoggingAspect Called Before Advice");
    }

    @After("execution(* com.application.booker.serviceImpl.BookServiceImpl.getAllBooks(..))")
    public void logAfter() {
        System.out.println("LoggingAspect Called After Advice");
    }

    @Pointcut("execution(* com.application.booker.serviceImpl.BookServiceImpl.getAllBooks(..))")
    public void getAllBooksPointCut() {
    }

    @Around("getAllBooksPointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("LoggingAspect Called Before Using Around Advice");
        Object result = pjp.proceed();
        System.out.println("LoggingAspect Called After Using Around Advice");
        return result;
    }

}
