package home_work.hw1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Flow {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoBeanFactoryPostProcessor.class);

    private ExternalService externalService;
    private Process process;

    public Flow(ExternalService externalService,
                @Lazy Process process) {
        this.externalService = externalService;
        this.process = process;
    }

    public void run(int id) {
        var externalInfo = externalService.getExternalInfo(id);
        if(externalInfo.getInfo() == null) {
            process.run(externalInfo);
        }
        else {
            LOGGER.info("getInfo() != null: {}", externalInfo);
        }
    }

}
