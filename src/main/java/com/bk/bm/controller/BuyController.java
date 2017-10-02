package com.bk.bm.controller;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.HttpResponse;
import com.bk.bm.service.BuyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 8:51.
 */
@RestController
@RequestMapping(value = "/buy")
public class BuyController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BuyService buyService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Buy createBuyBook(@RequestBody Buy buy) {
        return buyService.createBuyBook(buy);
    }

    @RequestMapping(value = "/all/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Buy> getAllBuy(@PathVariable int uid) {
        return buyService.getAllBuy(uid);
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.GET)
    @ResponseBody
    public Buy getBuy(@PathVariable int buy_id) {
        return buyService.getBuy(buy_id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse updateBuyInfo(@RequestBody Buy buy) {
        return buyService.updateBuyInfo(buy);
    }

    @RequestMapping(value = "/{buy_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteBuy(@PathVariable int buy_id) {
        return buyService.deleteBuy(buy_id);
    }
}
