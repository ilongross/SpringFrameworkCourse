package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.util.Iterator;
import java.util.Map;

@Component
public class ExternalInfoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceMapBeanPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> mapBeansWithAnnotation = configurableListableBeanFactory.getBeansWithAnnotation(CacheResult.class);
        Iterator<String> iter = configurableListableBeanFactory.getBeanNamesIterator();
        iter.forEachRemaining(b -> System.out.println(b));

        if(!mapBeansWithAnnotation.isEmpty()) {
            for (Object beanWithAnnotation : mapBeansWithAnnotation.values()) {
                LOGGER.warn("{}", (CacheResult)beanWithAnnotation);
            }
        }


    }
}
