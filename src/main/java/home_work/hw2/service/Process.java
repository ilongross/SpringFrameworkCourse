package home_work.hw2.service;

import home_work.hw2.model.ExternalInfo;

public interface Process {

    boolean run(ExternalInfo externalInfo) throws Exception;

}
