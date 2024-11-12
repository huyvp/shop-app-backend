package com.app.shop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class UserLogAspect {
    @Around("execution(* com.app.shop.controller.UserController.*(..)) && within(@org.springframework.web.bind.annotation.RestController *)")
    public Object logUserActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String address = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        log.info("Request method: {} - IP Address: {}", method, address);
        Object result = joinPoint.proceed();
        log.info("User activity complete");
        return result;
    }
}
