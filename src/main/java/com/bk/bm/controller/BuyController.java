package com.bk.bm.controller;

import com.bk.bm.domain.Buy;
import com.bk.bm.exception.DuplicateBookException;
import com.bk.bm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 8:51.
 */
@RestController
@RequestMapping(value = "/buy", produces = "application/json;charset=utf-8")
public class BuyController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final BookService<Buy> buyService;

    @Autowired
    public BuyController(@Qualifier(value = "buyService") BookService<Buy> buyService) {
        this.buyService = buyService;
    }

    /**
     * 구매할 책을 등록하는 API
     * @param uid
     * @param buy
     * @return Buy
     * 201 - 등록 성공
     * 409 - DuplicateBookException
     * 500 - 서버 에러, 데이터베이스 에러
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Buy> createBuyBook(@RequestHeader(value = "Authorization") int uid, @RequestBody Buy buy) {
        Buy responseBook = null;
        try {
            responseBook = buyService.createBook(uid, buy);
            if (responseBook != null) {
                return new ResponseEntity<Buy>(responseBook, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Buy>(responseBook, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DuplicateBookException e) {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.CONFLICT);
        }
    }

    /**
     * 유저가 등록한 모든 책 조회
     * @param uid
     * @return ArrayList<Buy>
     * 200 - 조회 성공
     * 204 - 유저가 등록한 책이 없음
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Buy>> getAllBuy(@RequestHeader(value = "Authorization") int uid) {
        ArrayList<Buy> responseBooks = buyService.getAllBooks(uid);
        if (!responseBooks.isEmpty()) {
            return new ResponseEntity<ArrayList<Buy>>(responseBooks, HttpStatus.OK);
        } else {
            return new ResponseEntity<ArrayList<Buy>>(responseBooks, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 유저가 등록한 책 중 하나를 조회
     * @param buy_id
     * @return Buy
     * 200 - 조회 성공
     * 204 - 등록되지 않은 책 or 조회 실패
     */
    @RequestMapping(value = "/{buy_id}", method = RequestMethod.GET)
    public ResponseEntity<Buy> getBuy(@PathVariable int buy_id) {
        Buy responseBook = buyService.getBook(buy_id);
        if (responseBook != null) {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 등록한 도서 정보를 전체 수정
     * @param buy
     * @return Status Code
     * 205 - 수정 성공
     * 500 - 수정 실패
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateBuyInfo(@RequestBody Buy buy) {
        if (buyService.updateBook(buy)) {
            return new ResponseEntity(HttpStatus.RESET_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 등록한 도서 정보 삭제
     * @param buy_id
     * @return Status Code
     * 200 - 삭제 성공
     * 500 - 삭제 실패
     */
    @RequestMapping(value = "/{buy_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBuy(@PathVariable int buy_id) {
        if (buyService.deleteBook(buy_id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
