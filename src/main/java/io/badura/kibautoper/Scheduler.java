package io.badura.kibautoper;

import io.badura.kibautoper.service.SetupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    SetupService setupService;

    @Scheduled(fixedDelay = 60000)
    public void steupPatterns(){
        log.info("Starting task: Setup Index Patterns And Templates");
        setupService.setupIndexPatternsAndTemplates();
    }

    @Scheduled(fixedDelay = 600000)
    public void refreshIndexPatterns(){
        log.info("Starting task: Refresh Index Patterns");
        setupService.rereshIndexPattern();
    }
}
