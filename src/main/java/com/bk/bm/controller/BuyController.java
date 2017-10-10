package com.bk.bm.controller;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import com.bk.bm.domain.User;
import com.bk.bm.exception.DuplicateBookException;
import com.bk.bm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Buy> createBuyBook(@RequestHeader(value = "") int uid, @RequestBody Buy buy) {
        Buy responseBook = null;
        try {
            responseBook = buyService.createBook(uid, buy);
            if (responseBook != null) {
                return new ResponseEntity<Buy>(responseBook, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Buy>(responseBook, HttpStatus.NO_CONTENT);
            }
        } catch (DuplicateBookException e) {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/all/{uid}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Buy>> getAllBuy(@PathVariable int uid) {
        ArrayList<Buy> responseBooks = buyService.getAllBooks(uid);
        if (!responseBooks.isEmpty()) {
            return new ResponseEntity<ArrayList<Buy>>(responseBooks, HttpStatus.OK);
        } else {
            return new ResponseEntity<ArrayList<Buy>>(responseBooks, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.GET)
    public ResponseEntity<Buy> getBuy(@PathVariable int buy_id) {
        Buy responseBook = buyService.getBook(buy_id);
        if (responseBook != null) {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<Buy>(responseBook, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateBuyInfo(@RequestBody Buy buy) {
        if (buyService.updateBook(buy)) {
            return new ResponseEntity(HttpStatus.RESET_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBuy(@PathVariable int buy_id) {
        if (buyService.deleteBook(buy_id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
