package com.bk.bm.service;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Transactional
public interface BuyService {

    Buy createBuyBook(Buy buy);

    @Transactional(readOnly = true)
    ArrayList<Buy> getAllBuy(int uid);

    @Transactional(readOnly = true)
    Buy getBuy(int buy_id);

    HttpResponse updateBuyInfo(Buy buy);

    HttpResponse deleteBuy(int buy_id);
}
