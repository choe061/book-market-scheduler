package com.bk.bm.service;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import com.bk.bm.persistence.BuyMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Service("buyService")
public class BuyServiceImpl implements BookService<Buy> {

    private final SqlSession sqlSession;
    private final BuyMapper buyMapper;

    @Autowired
    public BuyServiceImpl(BuyMapper buyMapper, SqlSession sqlSession) {
        this.buyMapper = buyMapper;
        this.sqlSession = sqlSession;
    }

    @Override
    public Buy createBook(Buy book) {
        int buy_id = buyMapper.insertBuyBook(book);
        return buyMapper.getBuy(buy_id);
    }

    @Override
    public ArrayList<Buy> getAllBooks(int uid) {
        return buyMapper.getAllBuy(uid);
    }

    @Override
    public Buy getBook(int buy_id) {
        Buy buy = buyMapper.getBuy(buy_id);
        System.out.println("BUY : "+buy+", "+buy.getBuy_id());
        return buyMapper.getBuy(buy_id);
    }

    @Override
    public HttpResponse updateBook(Buy book) {
        try {
            buyMapper.updateBuyInfo(book);
        } catch (Exception e) {
            return new HttpResponse(false, "데이터베이스 오류");
        }
        return new HttpResponse(true, "업데이트 성공");
    }

    @Override
    public HttpResponse deleteBook(int buy_id) {
        try {
            buyMapper.deleteBuy(buy_id);
        } catch (Exception e) {
            return new HttpResponse(false, "데이터베이스 오류");
        }
        return new HttpResponse(true, "도서 정보 삭제 성공");
    }
}
