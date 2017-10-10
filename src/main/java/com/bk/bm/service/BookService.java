package com.bk.bm.service;

import com.bk.bm.domain.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 5. PM 6:44.
 */
@Transactional
public interface BookService<T> {

    T createBook(int uid, T book);

    @Transactional(readOnly = true)
    ArrayList<T> getAllBooks(int uid);

    @Transactional(readOnly = true)
    T getBook(int book_id);

    boolean updateBook(T book);

    boolean deleteBook(int book_id);

}
