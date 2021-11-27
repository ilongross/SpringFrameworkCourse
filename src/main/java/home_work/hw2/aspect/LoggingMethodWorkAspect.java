package home_work.hw2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
//@Order(1)
public class LoggingMethodWorkAspect {


    @Before("logMethod()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("BEFORE method: {} with args {}",
                joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @After("logMethod()")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("AFTER method: {}", joinPoint.getSignature().toShortString());
    }


    @Pointcut("within(home_work.hw2.service.*)")
    public void logMethod() {}


}
