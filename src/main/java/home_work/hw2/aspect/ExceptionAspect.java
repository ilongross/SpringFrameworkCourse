package home_work.hw2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

@Component
@Slf4j
@Aspect
@Order(1)
public class ExceptionAspect {

    @Around("throwableMethod()")
    public Object logException(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("EXCEPTION (ExceptionAspect):\n\t\t\tMethod: {} throws exception {}",
                    joinPoint.getSignature().toShortString(), throwable.toString());
            return null;
        }
    }

    @Pointcut("within(home_work.hw2..*)")
    public void throwableMethod() {}

}
