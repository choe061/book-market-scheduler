package com.bk.bm.service;

import com.bk.bm.domain.HttpResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 5. PM 6:44.
 */
@Transactional
public interface BookService<T> {

    T createBook(T book);

    @Transactional(readOnly = true)
    ArrayList<T> getAllBooks(int uid);

    @Transactional(readOnly = true)
    T getBook(int buy_id);

    HttpResponse updateBook(T book);

    HttpResponse deleteBook(int buy_id);

}
