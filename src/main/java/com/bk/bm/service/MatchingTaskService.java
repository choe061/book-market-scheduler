package com.bk.bm.service;

import com.bk.bm.domain.*;
import com.bk.bm.repository.BuyMapper;
import com.bk.bm.repository.MatchingTaskMapper;
import com.bk.bm.repository.SaleMapper;
import com.bk.bm.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by choi on 2017. 9. 25. PM 4:10.
 */

@Service
@Transactional
public class MatchingTaskService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MatchingTaskMapper matchingTaskMapper;
    @Autowired
    private BuyMapper buyMapper;
    @Autowired
    private SaleMapper saleMapper;

    @Scheduled(cron = "0 0/30 9-23 * * *")
    public void matchBookScheduler() {
        logger.debug("Scheduler start...");
        ArrayList<Matching> matchings = matchingTaskMapper.getFinishedDealOfMatching();
        backupFinishedDeal(matchings);
        deleteFinishedDeal(matchings);

        ArrayList<Matching> newMatchingBooks = matchingTaskMapper.getNewMatchingBooks();

        if (!newMatchingBooks.isEmpty()) {
            matchingTaskMapper.matchingBooks(newMatchingBooks);

            ArrayList<Integer> uids = new ArrayList<>();
            for (Matching matching : newMatchingBooks) {
                if (uids.contains(matching.getSale_id())) {
                    uids.add(matching.getSale_id());
                }
                if (uids.contains(matching.getBuy_id())) {
                    uids.add(matching.getBuy_id());
                }
            }
            ArrayList<User> users = matchingTaskMapper.getFcmTokenOfUsers(uids);
            sendFcmNewMatching(users);
        }
        logger.debug("Scheduler end...");
    }

    private void backupFinishedDeal(ArrayList<Matching> matchings) {
        ArrayList<History> histories = saleMapper.getFinishedDealOfSale(matchings);
        ArrayList<SaleImage> saleImages = saleMapper.getFinishedDealOfSaleImages(matchings);
        matchingTaskMapper.backupMatchingHistory(histories);
        matchingTaskMapper.backupMatchingHistoryImage(saleImages);
    }

    private void deleteFinishedDeal(ArrayList<Matching> matchings) {
        matchingTaskMapper.deleteMatching(matchings);

        buyMapper.deleteFinishedDealOfBuy(matchings);
        buyMapper.deleteFinishedDealOfBuyAreas(matchings);
        buyMapper.deleteFinishedDealOfBuyImages(matchings);

        saleMapper.deleteFinishedDealOfSale(matchings);
        saleMapper.deleteFinishedDealOfSaleAreas(matchings);
        saleMapper.deleteFinishedDealOfSaleImages(matchings);
    }

    private void sendFcmNewMatching(ArrayList<User> users) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "key=" + Constants.FCM_KEY);
        Message message = new Message("췕48 매칭 알림", "찾고있는 책이 들어왔습니다");

        int threadPoolCount = Runtime.getRuntime().availableProcessors() * 2 + 1;
        ExecutorService fcmSendService = Executors.newFixedThreadPool(threadPoolCount);

        int size = users.size();
        for (int i = 0; i < size; i++) {
            String token = users.get(i).getFcm_token();
            fcmSendService.submit(new Runnable() {
                @Override
                public void run() {
                    FcmObject fcmObject = new FcmObject(token, message);
                    HttpEntity request = new HttpEntity(fcmObject, headers);
                    restTemplate.exchange(Constants.FCM_SEND_ENDPOINT, HttpMethod.POST, request, HashMap.class);
                }
            });
        }
        fcmSendService.shutdown();
        while (!fcmSendService.isTerminated()) {}
    }

}
