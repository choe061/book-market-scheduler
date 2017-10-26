package com.bk.bm.service;

import com.bk.bm.domain.Sale;
import com.bk.bm.exception.DuplicateBookException;
import com.bk.bm.persistence.SaleMapper;
import com.bk.bm.util.BookValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Service("saleService")
public class SaleServiceImpl implements BookService<Sale> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SaleMapper saleMapper;

    @Autowired
    public SaleServiceImpl(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    public int duplicateBook(int uid, String isbn10, String isbn13) {
        return saleMapper.duplicateBook(uid, isbn10, isbn13);
    }

    @Override
    public Sale createBook(int uid, Sale book) {
        if (duplicateBook(uid, book.getIsbn10(), book.getIsbn13()) != BookValidator.NOT_EXIST_BOOK) {
            throw new DuplicateBookException();
        }
        int sale_id = saleMapper.insertSaleBook(uid, book);
        return this.getBook(sale_id);
    }

    @Override
    public ArrayList<Sale> getAllBooks(int uid) {
        return saleMapper.getAllSale(uid);
    }

    @Override
    public Sale getBook(int book_id) {
        Sale sale = saleMapper.getSale(book_id);
        sale.setArea(saleMapper.getSaleAreas(book_id));
        sale.setImages(saleMapper.getSaleImages(book_id));
        return sale;
    }

    @Override
    public boolean updateBook(Sale book) {
        try {
            saleMapper.updateSale(book);
        } catch (DataAccessException dae) {
            logger.debug("updateBook() DataAccessException - "+dae.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBook(int book_id) {
        try {
            saleMapper.deleteSale(book_id);
            saleMapper.deleteSaleAreas(book_id);
            saleMapper.deleteSaleImages(book_id);
        } catch (DataAccessException dae) {
            logger.debug("deleteBook() DataAccessException - "+dae.getMessage());
            return false;
        }
        return true;
    }
}
