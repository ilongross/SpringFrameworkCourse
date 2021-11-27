package home_work.hw2.service;

import home_work.hw2.CheckRequest;
import home_work.hw2.model.ExternalInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Lazy
public class ExternalInfoProcess implements Process {
    @Override
    @CheckRequest
    public boolean run(ExternalInfo externalInfo) throws Exception {
            return true;
    }
}
