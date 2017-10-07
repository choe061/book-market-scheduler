package com.bk.bm.service;

import com.bk.bm.domain.Sale;
import com.bk.bm.persistence.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Sale getBook(int book_id) {
        return null;
    }

    @Override
    public boolean updateBook(Sale book) {
        return true;
    }

    @Override
    public boolean deleteBook(int book_id) {
        return true;
    }
}
