package home_work.hw2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@Aspect
public class CashingAspect {

    private static final Map<String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();

    @Around("@annotation(home_work.hw2.CacheResult)")
    public Object caching(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("Method: {} annotated @CacheResult", proceedingJoinPoint.getSignature().toShortString());
        Map<MethodArgs, Object> methodArgsObjectMap = CACHE.get(proceedingJoinPoint.getSignature().toShortString());

        if(methodArgsObjectMap != null) {
            log.info("Method: {} has cache. Cache: {}",
                    proceedingJoinPoint.getSignature().toShortString(), methodArgsObjectMap);
            final var methodArgs = getMethodArgs(proceedingJoinPoint.getArgs());
            log.info("Check cache result by method with args: {}({})",
                    proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs());
            var result = methodArgsObjectMap.get(methodArgs);

            if(result != null) {
                log.info("FROM CACHE: method {}({}), result: {}",
                        proceedingJoinPoint.getSignature().toShortString(), proceedingJoinPoint.getArgs(), result);
                return result;
            } else {
                log.info("Call original method and record result into cache");
                result = proceedingJoinPoint.proceed();
                methodArgsObjectMap.put(methodArgs, result);
                return result;
            }
        } else {
            log.info("Method: {} not cache", proceedingJoinPoint.getSignature().getName());
            var result = proceedingJoinPoint.proceed();
            methodArgsObjectMap = new ConcurrentHashMap<>();
            methodArgsObjectMap.put(getMethodArgs(proceedingJoinPoint.getArgs()), result);
            CACHE.put(proceedingJoinPoint.getSignature().toShortString(), methodArgsObjectMap);
            return result;
        }

    }

    private MethodArgs getMethodArgs(Object[] args) {
        var linkedArgs = new LinkedList<>();
        Collections.addAll(linkedArgs, args);
        return new MethodArgs(linkedArgs);
    }

    private static class MethodArgs {
        private final LinkedList<Object> args;
        private MethodArgs(LinkedList<Object> args) {
            this.args = args;
        }
        public LinkedList<Object> getArgs() {
            return args;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            var that = (MethodArgs) o;
            return Objects.equals(args, that.args);
        }
        @Override
        public int hashCode() {
            return Objects.hash(args);
        }
    }

}
