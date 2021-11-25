package home_work.hw1;

import com.sun.security.jgss.GSSUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Slf4j
public class ExternalInfoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        for (String name : configurableListableBeanFactory.getBeanDefinitionNames()) {
            var beanDefinition = configurableListableBeanFactory.getBeanDefinition(name);
            if(beanDefinition.isPrototype()) {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
                for (Method method : beanClass.getDeclaredMethods()) {
                    if(method.isAnnotationPresent(CacheResult.class)) {
                        log.warn("Bean: {} isPrototype()={} and has annotation @CacheResult", beanDefinition.getBeanClassName(), beanDefinition.isPrototype());
                    }
                }
            }
        }
    }
}
