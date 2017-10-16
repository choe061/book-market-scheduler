package com.bk.bm.util;

/**
 * Created by choi on 2017. 10. 10. AM 10:11.
 */
public final class BookValidator {

    private BookValidator() {}

    /**
     * response code
     */
    public static final int BOOK_DUPLICATE_CODE = 409;
    public static final String BOOK_DUPLICATE_MESSAGE = "Duplicate Book";

    /**
     * database error
     */
    public static final int NOT_EXIST_BOOK = 0;
}
