package com.bk.bm.controller;

import com.bk.bm.domain.Sale;
import com.bk.bm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
