package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import java.util.HashMap;
import java.util.Map;

@Component
@CacheDefaults(cacheName = "idCache")
public class ExternalServiceImpl implements ExternalService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceMapBeanPostProcessor.class);
    private Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();


    public void initMap() {
        LOGGER.info("started method initMap()");
        externalInfoMap.put(1, new ExternalInfo(1, null));
        externalInfoMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoMap.put(3, new ExternalInfo(3, "info"));
        externalInfoMap.put(4, new ExternalInfo(4, "information"));
        LOGGER.info("ended method initMap()");
    }

    public void clearMap() {
        LOGGER.info("started method clearMap()");
        externalInfoMap.clear();
        LOGGER.info("ended method clearMap()");
    }

    @Override
    @CacheResult
    public ExternalInfo getExternalInfo(@CacheKey Integer id) {
        LOGGER.info("started method getExternalInfo(id={}) without CACHE", id);
        return externalInfoMap.get(id);
    }
}
