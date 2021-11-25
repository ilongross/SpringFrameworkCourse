package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class ExternalServiceImpl implements ExternalService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoBeanFactoryPostProcessor.class);
    private Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();


    @PostConstruct
    public void init() {
        LOGGER.info("started method initMap(): sizeMap={}", externalInfoMap.size());
        externalInfoMap.put(1, new ExternalInfo(1, null));
        externalInfoMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoMap.put(3, new ExternalInfo(3, "info"));
        externalInfoMap.put(4, new ExternalInfo(4, "information"));
        LOGGER.info("ended method initMap(): sizeMap={}", externalInfoMap.size());
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("started method clearMap(): sizeMap={}", externalInfoMap.size());
        externalInfoMap.clear();
        LOGGER.info("ended method clearMap(): sizeMap={}", externalInfoMap.size());
    }

    @Override
    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) {
        return externalInfoMap.get(id);
    }
}
