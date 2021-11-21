package home_work.hw1;

import com.sun.security.jgss.GSSUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class ExternalInfoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoBeanFactoryPostProcessor.class);

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        for (String name : configurableListableBeanFactory.getBeanDefinitionNames()) {
            if(configurableListableBeanFactory.getBeanDefinition(name).isPrototype()) {
                Class<?> beanClass = Class.forName(name);
                for (Method method : beanClass.getMethods()) {
                    if(method.isAnnotationPresent(CacheResult.class)) {
                        LOGGER.warn("Bean \"{}\" is PROTOTYPE and has annotation CacheResult", name);
                    }
                }
            }
        }
    }

}
