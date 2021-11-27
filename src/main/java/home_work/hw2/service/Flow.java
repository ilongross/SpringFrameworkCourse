package home_work.hw2.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Flow {


    private ExternalService externalService;
    private Process process;

    public Flow(ExternalService externalService,
                @Lazy Process process) {
        this.externalService = externalService;
        this.process = process;
    }

    public void run(int id) throws Exception {
        var externalInfo = externalService.getExternalInfo(id);
        if(externalInfo.getInfo() != null) {
            process.run(externalInfo);
        }
        else {
            log.info("getInfo() == null: {}", externalInfo.getInfo() == null);
        }
    }

}
