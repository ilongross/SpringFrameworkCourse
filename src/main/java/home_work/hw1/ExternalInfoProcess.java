package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ExternalInfoProcess implements Process{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceMapBeanPostProcessor.class);

    @Value("${id-not-process}")
    private int idNotProcess;

    @Override
    public boolean run(ExternalInfo externalInfo) {
        LOGGER.info("externalInfo.id[{}] == id-not-process[{}]: return {}",
                externalInfo.getId(), idNotProcess,
                externalInfo.getId() == idNotProcess);
        if(externalInfo.getId() != idNotProcess) {
            return true;
        }
        else {
            return false;
        }
    }

}
