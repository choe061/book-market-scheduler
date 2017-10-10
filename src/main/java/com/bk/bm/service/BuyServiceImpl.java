package com.bk.bm.service;

import com.bk.bm.domain.Buy;
import com.bk.bm.exception.DuplicateBookException;
import com.bk.bm.persistence.BuyMapper;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public int duplicateBook(int uid, String isbn10, String isbn13) {
        try {
            int id = buyMapper.duplicateBuyBook(uid, isbn10, isbn13);
            return id;
        } catch (BindingException e) {
            return -1;
        }
    }

    @Override
    public Buy createBook(int uid, Buy book) {
        Buy buy = null;
        try {
            int buy_id = duplicateBook(uid, book.getIsbn10(), book.getIsbn13());
            if (buy_id != -1) {
                throw new DuplicateBookException();
            }
            buy_id = buyMapper.insertBuyBook(book);
            buy = buyMapper.getBuy(buy_id);
        } catch (SQLException e) {
            logger.debug("createBook() SQLException - "+e);
            return buy;
        }
        return buy;
    }

    @Override
    public ArrayList<Buy> getAllBooks(int uid) {
        ArrayList<Buy> books = new ArrayList<>();
        try {
            books = buyMapper.getAllBuy(uid);
        } catch (SQLException e) {
            logger.debug("getAllBooks() SQLException - "+e);
            return books;
        }
        return books;
    }

    @Override
    public Buy getBook(int book_id) {
        Buy buy = null;
        try {
            buy = buyMapper.getBuy(book_id);
            buy.setArea(buyMapper.getBuyAreas(book_id));
            buy.setImages(buyMapper.getBuyImages(book_id));
        } catch (SQLException e) {
            logger.debug("getBook() SQLException - "+e);
            return buy;
        }
        return buy;
    }

    @Override
    public boolean updateBook(Buy book) {
        try {
            buyMapper.updateBuyInfo(book);
        } catch (SQLException e) {
            logger.debug("updateBook() SQLException - "+e);
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
        } catch (SQLException e) {
            logger.debug("deleteBook() SQLException - "+e);
            return false;
        }
        return true;
    }
}
