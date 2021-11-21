package home_work.hw1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExternalInfoCacheResultBeanFactoryPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoBeanFactoryPostProcessor.class);
    private Map<Integer, ExternalInfo> externalInfoCache = new HashMap<>();

    private Map<String, Map<Parameter[], Object>> methodResultsCacheMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessAfterInitialization: beanName: {}", beanName);

        var proxyFactory = new ProxyFactory(bean);

        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                Object proceed = null;
                for (Method method : bean.getClass().getMethods()) {
                    if(method.isAnnotationPresent(CacheResult.class)) {
                        // Проверка КЭШа
                        if(methodResultsCacheMap.containsKey(method.getName())) {
                            return methodResultsCacheMap.get(method.getName()).get(method.getParameters());
                        }
                        // если нет, то
                        else {
                            proceed = invocation.proceed();
                            // Кэширование proceed
                            Map<Parameter[], Object> results = new HashMap<>();
                            results.put(method.getParameters(), proceed);
                            methodResultsCacheMap.put(method.getName(), results);
                        }
                    }
                }
                System.out.println(proceed);
                return proceed;
            }
        });



        return proxyFactory.getProxy();

//        if(bean instanceof ExternalService) {
//            LOGGER.info("Bean instanceOf ExternalInfo.class");
//            var proxyFactory = new ProxyFactory(bean);
//
//            proxyFactory.addAdvice(new MethodInterceptor() {
//                @Override
//                public Object invoke(MethodInvocation invocation) throws Throwable {
//                    LOGGER.info("Before call method in getExternalInfo()");
//                    var proceed = (ExternalInfo) invocation.proceed();
//                    if(!externalInfoCache.containsKey(proceed.getId())) {
//                        return caching(proceed);
//                    }
//                    else {
//                        LOGGER.info("getExternalInfo(id={}) from CACHE!!!", proceed.getId());
//                        return externalInfoCache.get(proceed.getId());
//                    }
//                }
//            });
//            return proxyFactory.getProxy();
//        }

//        return bean;
    }

    private Object caching(ExternalInfo proceed) {
        externalInfoCache.put(proceed.getId(), proceed);
        LOGGER.info("getExternalInfo(id={}) CACHED", proceed.getId());
        return proceed;
    }
}
