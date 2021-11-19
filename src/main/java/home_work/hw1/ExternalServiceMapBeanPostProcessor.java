package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceMapBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceMapBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("started beanBeforeInit method: beanName: {}", beanName);
        if(bean instanceof ExternalService) {
            LOGGER.info("bean instanceOf {}: beanName: {}", bean.getClass().getSimpleName(), beanName);
            var beanExternalService = ((ExternalServiceImpl) bean);
            beanExternalService.initMap();
            return beanExternalService;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("started beanAfterInit method: beanName: {}", beanName);
        if(bean instanceof ExternalServiceImpl) {
            LOGGER.info("bean instanceOf ExternalServiceImpl: beanName: {}", beanName);
            var beanExternalService = ((ExternalServiceImpl) bean);
//            beanExternalService.clearMap();
            return beanExternalService;
        }
        return bean;
    }
}
