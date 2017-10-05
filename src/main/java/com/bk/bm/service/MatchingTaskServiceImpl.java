package com.bk.bm.service;

import com.bk.bm.domain.FcmObject;
import com.bk.bm.domain.Matching;
import com.bk.bm.domain.Message;
import com.bk.bm.domain.User;
import com.bk.bm.persistence.MatchingTaskMapper;
import com.bk.bm.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by choi on 2017. 9. 25. PM 4:10.
 */

@Service("MatchingTaskService")
public class MatchingTaskServiceImpl implements MatchingTaskService {

    private final MatchingTaskMapper matchingTaskMapper;

    @Autowired
    public MatchingTaskServiceImpl(MatchingTaskMapper matchingTaskMapper) {
        this.matchingTaskMapper = matchingTaskMapper;
    }

    @Override
    public void matchBookScheduler() {
        ArrayList<Matching> newMatchingBooks = matchingTaskMapper.getNewMatchingBooks();

        if (!newMatchingBooks.isEmpty()) {
            matchingTaskMapper.matchingBooks(newMatchingBooks);

            ArrayList<Integer> uids = new ArrayList<>();
            for (Matching matching : newMatchingBooks) {
                uids.add(matching.getS_uid());
                uids.add(matching.getB_uid());
            }
            ArrayList<User> users = matchingTaskMapper.getFcmTokenOfUsers(uids);
            sendFcmNewMatching(users);
        }
    }

    private void sendFcmNewMatching(ArrayList<User> users) {
        //TODO Send FCM Message
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
