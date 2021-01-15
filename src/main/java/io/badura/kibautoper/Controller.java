package io.badura.kibautoper;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.badura.kibautoper.service.SetupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Autowired
    SetupService setupService;

    @GetMapping(value = "/trigger_patterns")
    public void getPatterns() {
        log.info("Starting task: Setup Index Patterns And Templates");
        setupService.setupIndexPatternsAndTemplates();
    }

    @GetMapping(value = "/trigger_refresh")
    public void refreshPattern()  {
        log.info("Starting task: Refresh Index Patterns");
        setupService.rereshIndexPattern();
    }
}
