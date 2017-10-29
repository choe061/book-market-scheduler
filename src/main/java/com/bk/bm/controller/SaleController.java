package com.bk.bm.controller;

import com.bk.bm.domain.Sale;
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
 * Created by choi on 2017. 10. 11. PM 5:45.
 */
@RestController
@RequestMapping(value = "/sale", produces = "application/json;charset=utf-8")
public class SaleController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BookService<Sale> saleService;

    @Autowired
    public SaleController(@Qualifier(value = "saleService") BookService<Sale> saleService) {
        this.saleService = saleService;
    }

    /**
     * 판매할 책을 등록하는 API
     * @param uid
     * @param sale
     * @return Sale
     * 201 - 등록 성공
     * 409 - DuplicateBookException
     * 500 - 서버 에러, 데이터베이스 에러
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sale> createSaleBook(@RequestHeader(value = "Authorization") int uid, @RequestBody Sale sale) {
        Sale responseBook = null;
        try {
            responseBook = saleService.createBook(uid, sale);
            if (responseBook != null) {
                return new ResponseEntity<>(responseBook, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(responseBook, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DuplicateBookException e) {
            return new ResponseEntity<>(responseBook, HttpStatus.CONFLICT);
        }
    }

    /**
     * 유저가 등록한 모든 책 조회
     * @param uid
     * @return ArrayList<Sale>
     * 200 - 조회 성공
     * 204 - 유저가 등록한 책이 없음
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Sale>> getAllSale(@RequestHeader(value = "Authorization") int uid) {
        ArrayList<Sale> responseBooks = saleService.getAllBooks(uid);
        if (!responseBooks.isEmpty()) {
            return new ResponseEntity<>(responseBooks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseBooks, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 유저가 등록한 책 중 하나를 조회
     * @param sale_id
     * @return Sale
     * 200 - 조회 성공
     * 204 - 등록되지 않은 책 or 조회 실패
     */
    @RequestMapping(value = "/{sale_id}", method = RequestMethod.GET)
    public ResponseEntity<Sale> getSale(@PathVariable int sale_id) {
        Sale responseBook = saleService.getBook(sale_id);
        if (responseBook != null) {
            return new ResponseEntity<>(responseBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(responseBook, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * 등록한 도서 정보를 전체 수정
     * @param sale
     * @return Status Code
     * 205 - 수정 성공
     * 500 - 수정 실패
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateSaleInfo(@RequestBody Sale sale) {
        if (saleService.updateBook(sale)) {
            return new ResponseEntity(HttpStatus.RESET_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 등록한 도서 정보 삭제
     * @param sale_id
     * @return Status Code
     * 200 - 삭제 성공
     * 500 - 삭제 실패
     */
    @RequestMapping(value = "/{sale_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSale(@PathVariable int sale_id) {
        if (saleService.deleteBook(sale_id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
