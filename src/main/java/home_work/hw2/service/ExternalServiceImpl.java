package home_work.hw2.service;

import home_work.hw2.CacheResult;
import home_work.hw2.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Scope("prototype")
public class ExternalServiceImpl implements ExternalService {

    private Map<Integer, ExternalInfo> externalInfoMap = new HashMap<>();


    @PostConstruct
    public void init() {
        log.info("started method initMap(): sizeMap={}", externalInfoMap.size());
        externalInfoMap.put(1, new ExternalInfo(1, null));
        externalInfoMap.put(2, new ExternalInfo(2, "hasInfo"));
        externalInfoMap.put(3, new ExternalInfo(3, "info"));
        externalInfoMap.put(4, new ExternalInfo(4, "information"));
        log.info("ended method initMap(): sizeMap={}", externalInfoMap.size());
    }

    @PreDestroy
    public void destroy() {
        log.info("started method clearMap(): sizeMap={}", externalInfoMap.size());
        externalInfoMap.clear();
        log.info("ended method clearMap(): sizeMap={}", externalInfoMap.size());
    }

    @Override
    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) throws Exception {
        if(!externalInfoMap.containsKey(id)) {
            throw new Exception("NOT FOUND");
        } else {
            return externalInfoMap.get(id);
        }
    }
}
