package com.bk.bm.service;

import com.bk.bm.domain.Buy;
import com.bk.bm.exception.DuplicateBookException;
import com.bk.bm.repository.BuyMapper;
import com.bk.bm.util.BookValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:01.
 */
@Service("buyService")
public class BuyServiceImpl implements BookService<Buy> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BuyMapper buyMapper;

    @Autowired
    public BuyServiceImpl(BuyMapper buyMapper) {
        this.buyMapper = buyMapper;
    }

    @Override
    public Buy createBook(int uid, Buy book) {
        if (duplicateBook(uid, book.getIsbn10(), book.getIsbn13()) != BookValidator.NOT_EXIST_BOOK) {
            throw new DuplicateBookException();
        }
        int buy_id = buyMapper.insertBuyBook(uid, book);
        buyMapper.insertBuyAreas(uid, buy_id, book.getArea());
        buyMapper.insertBuyImages(uid, buy_id, book.getImages());
        return this.getBook(buy_id);
    }

    private int duplicateBook(int uid, String isbn10, String isbn13) {
        return buyMapper.duplicateBook(uid, isbn10, isbn13);
    }

    @Override
    public ArrayList<Buy> getAllBooks(int uid) {
        return buyMapper.getAllBuy(uid);
    }

    @Override
    public Buy getBook(int book_id) {
        Buy buy = buyMapper.getBuy(book_id);
        buy.setArea(buyMapper.getBuyAreas(book_id));
        buy.setImages(buyMapper.getBuyImages(book_id));
        return buy;
    }

    @Override
    public boolean updateBook(Buy book) {
        try {
            buyMapper.deleteBuyAreas(book.getBuy_id());
            buyMapper.deleteBuyImages(book.getBuy_id());

            buyMapper.updateBuy(book);
            buyMapper.insertBuyAreas(book.getBuy_uid(), book.getBuy_id(), book.getArea());
            buyMapper.insertBuyImages(book.getBuy_uid(), book.getBuy_id(), book.getImages());
        } catch (DataAccessException e) {
            logger.debug("updateBook() DataAccessException - "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBook(int book_id) {
        try {
            buyMapper.deleteBuy(book_id);
            buyMapper.deleteBuyAreas(book_id);
            buyMapper.deleteBuyImages(book_id);
        } catch (DataAccessException e) {
            logger.debug("deleteBook() DataAccessException - "+e.getMessage());
            return false;
        }
        return true;
    }
}
