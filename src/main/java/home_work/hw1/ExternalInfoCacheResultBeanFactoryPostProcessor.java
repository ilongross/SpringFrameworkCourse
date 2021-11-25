package home_work.hw1;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class ExternalInfoCacheResultBeanFactoryPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = bean.getClass().getDeclaredMethods();
        log.info("postProcessAfterInitialization: beanName: {}", beanName);
        for (Method method : methods) {
            if(method.isAnnotationPresent(CacheResult.class)) {
                log.info("Bean{} is proxy. Has annotation @CacheResult on method: {}", beanName, method.getName());
                var proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice(new CacheMethodResultInterceptor());
                return proxyFactory.getProxy();
            }
        }
        return bean;
    }
}
