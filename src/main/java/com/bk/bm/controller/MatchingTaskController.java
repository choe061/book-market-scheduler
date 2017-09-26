package com.bk.bm.controller;

import com.bk.bm.service.MatchingTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by choi on 2017. 9. 25. PM 4:57.
 */

@RestController
@RequestMapping(value = "/matches")
public class MatchingTaskController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MatchingTaskService matchingTaskService;

}
