package com.bk.bm.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by choi on 2017. 9. 25. PM 4:11.
 * 10분마다 스케줄러 작동
 * N:M 매핑, Matching 테이블 구현
 * 조건
 *  1. 기존에 매칭된 레코드들인지 확인해야한다.
 *  2. Sale 또는 Buy 테이블에서 삭제되는 경우 Matching 테이블에서도 삭제시켜야한다.
 */

@Transactional
public interface MatchingTaskService {

    @Scheduled(cron = "0 0/30 * * * *")
    void matchBookScheduler();

}
