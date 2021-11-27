package home_work.hw2.aspect;

import home_work.hw2.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
@Order(0)
public class CheckRequestAspect {

    @Value("${id-not-process}")
    private int idNotProcess;

    @Around("@annotation(home_work.hw2.CheckRequest) && anyMethodWithArgExternalInfo(externalInfo)")
    public Object annotationCheckRequest(ProceedingJoinPoint proceedingJoinPoint, ExternalInfo externalInfo) throws Throwable{
        if(externalInfo.getId() != idNotProcess) {
            return proceedingJoinPoint.proceed();
        } else {
            log.error("EXCEPTION (CheckRequestAspect): Method: {} throws exception: externalInfo.getId() == idNotProcess return {}",
                    proceedingJoinPoint.getSignature().toShortString(),
                    String.valueOf(externalInfo.getId() == idNotProcess).toUpperCase());
            throw new Exception();
        }
    }

    @Pointcut("execution(* *(.., home_work.hw2.model.ExternalInfo, ..)) && args(externalInfo, ..)")
    public void anyMethodWithArgExternalInfo(ExternalInfo externalInfo) {}

}
