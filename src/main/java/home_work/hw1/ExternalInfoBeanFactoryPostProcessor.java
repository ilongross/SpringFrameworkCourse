package home_work.hw1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ExternalInfoBeanFactoryPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(CacheResult.class)) {
                System.out.println("TRUE");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
