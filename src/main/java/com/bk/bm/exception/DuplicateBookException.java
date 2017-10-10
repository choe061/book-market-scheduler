package com.bk.bm.exception;

import com.bk.bm.util.BookValidator;

/**
 * Created by choi on 2017. 10. 10. AM 10:06.
 */
public class DuplicateBookException extends ServiceException {

    public DuplicateBookException() {
        super(BookValidator.BOOK_DUPLICATE_MESSAGE, BookValidator.BOOK_DUPLICATE_CODE);
    }

}
