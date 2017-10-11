package com.bk.bm.service;

import com.bk.bm.domain.FcmObject;
import com.bk.bm.domain.Matching;
import com.bk.bm.domain.Message;
import com.bk.bm.domain.User;
import com.bk.bm.persistence.MatchingTaskMapper;
import com.bk.bm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by choi on 2017. 9. 25. PM 4:10.
 */

@Service
@Transactional
public class MatchingTaskService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MatchingTaskMapper matchingTaskMapper;

    @Autowired
    public MatchingTaskService(MatchingTaskMapper matchingTaskMapper) {
        this.matchingTaskMapper = matchingTaskMapper;
    }

    @Scheduled(cron = "0 0/30 9-23 * * *")
    public void matchBookScheduler() {
        logger.debug("Scheduler start...");
        ArrayList<Matching> newMatchingBooks = matchingTaskMapper.getNewMatchingBooks();

        if (!newMatchingBooks.isEmpty()) {
            matchingTaskMapper.matchingBooks(newMatchingBooks);

            ArrayList<Integer> uids = new ArrayList<>();
            for (Matching matching : newMatchingBooks) {
                if (uids.contains(matching.getSale_uid())) {
                    uids.add(matching.getSale_uid());
                }
                if (uids.contains(matching.getBuy_uid())) {
                    uids.add(matching.getBuy_uid());
                }
            }
            ArrayList<User> users = matchingTaskMapper.getFcmTokenOfUsers(uids);
            sendFcmNewMatching(users);
        }
        logger.debug("Scheduler end...");
    }

    private void sendFcmNewMatching(ArrayList<User> users) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "key=" + Constants.FCM_KEY);

        int size = users.size();
        for (int i = 0; i < size; i++) {
            Message message = new Message("췕48 매칭 알림", "찾고있는 책이 들어왔습니다", "");
            FcmObject fcmObject = new FcmObject(users.get(i).getFcm_token(), message);

            HttpEntity request = new HttpEntity(fcmObject, headers);
            restTemplate.exchange(Constants.FCM_SEND_ENDPOINT, HttpMethod.POST, request, HashMap.class);
        }
    }

}
