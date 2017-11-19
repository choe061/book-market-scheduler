package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private static final long serialVersionUID = 1L;

    private int history_id;
    private int seller_uid;
    private int buyer_uid;
    private String isbn10;
    private String isbn13;
    private String title;
    private int price;
    private String comment;
    private Date matched_at;

    public History(int seller_uid, int buyer_uid, String isbn10, String isbn13, String title, int price, String comment, Date matched_at) {
        this.seller_uid = seller_uid;
        this.buyer_uid = buyer_uid;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.price = price;
        this.comment = comment;
        this.matched_at = matched_at;
    }
}
