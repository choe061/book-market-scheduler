package com.bk.bm.controller;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import com.bk.bm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 8:51.
 */
@RestController
@RequestMapping(value = "/buy")
public class BuyController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final BookService<Buy> buyService;

    @Autowired
    public BuyController(@Qualifier(value = "buyService") BookService<Buy> buyService) {
        this.buyService = buyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Buy createBuyBook(@RequestBody Buy buy) {
        return buyService.createBook(buy);
    }

    @RequestMapping(value = "/all/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Buy> getAllBuy(@PathVariable int uid) {
        return buyService.getAllBooks(uid);
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.GET)
    @ResponseBody
    public Buy getBuy(@PathVariable int buy_id) {
        return buyService.getBook(buy_id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateBuyInfo(@RequestBody Buy buy) {
        return buyService.updateBook(buy);
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteBuy(@PathVariable int buy_id) {
        return buyService.deleteBook(buy_id);
    }
}
