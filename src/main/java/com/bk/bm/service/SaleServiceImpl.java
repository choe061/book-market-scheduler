package com.bk.bm.service;

import com.bk.bm.domain.HttpResponse;
import com.bk.bm.domain.Sale;
import com.bk.bm.persistence.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Service("SaleService")
public class SaleServiceImpl implements BookService<Sale> {

    private final SaleMapper saleMapper;

    @Autowired
    public SaleServiceImpl(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    @Override
    public Sale createBook(Sale book) {
        return null;
    }

    @Override
    public ArrayList<Sale> getAllBooks(int uid) {
        return null;
    }

    @Override
    public Sale getBook(int buy_id) {
        return null;
    }

    @Override
    public HttpResponse updateBook(Sale book) {
        return null;
    }

    @Override
    public HttpResponse deleteBook(int buy_id) {
        return null;
    }
}
