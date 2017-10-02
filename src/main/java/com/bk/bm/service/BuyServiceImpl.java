package com.bk.bm.service;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import com.bk.bm.persistence.BuyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Service("BuyService")
public class BuyServiceImpl implements BuyService {

    @Autowired
    private BuyMapper buyMapper;

    @Override
    public Buy createBuyBook(Buy buy) {
        int buy_id = buyMapper.insertBuyBook(buy);
        return buyMapper.getBuy(buy_id);
    }

    @Override
    public ArrayList<Buy> getAllBuy(int uid) {
        return buyMapper.getAllBuy(uid);
    }

    @Override
    public Buy getBuy(int buy_id) {
        return buyMapper.getBuy(buy_id);
    }

    @Override
    public HttpResponse updateBuyInfo(Buy buy) {
        try {
            buyMapper.updateBuyInfo(buy);
        } catch (Exception e) {
            return new HttpResponse(false, "데이터베이스 오류");
        }
        return new HttpResponse(true, "업데이트 성공");
    }

    @Override
    public HttpResponse deleteBuy(int buy_id) {
        try {
            buyMapper.deleteBuy(buy_id);
        } catch (Exception e) {
            return new HttpResponse(false, "데이터베이스 오류");
        }
        return new HttpResponse(true, "도서 정보 삭제 성공");
    }

}
