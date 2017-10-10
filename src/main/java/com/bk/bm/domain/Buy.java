package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by choi on 2017. 9. 25. PM 4:01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buy implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_id;
    private int buy_uid;
    private String isbn10;
    private String isbn13;
    private String title;
    private int price_min;
    private int price_max;
    private String comment;
    private Date created_at;
    private Date updated_at;

    private ArrayList<BuyArea> area = new ArrayList<>();
    private ArrayList<BuyImage> images = new ArrayList<>();

    public Buy(int buy_uid, String isbn10, String isbn13, String title, int price_min, int price_max, String comment) {
        this.buy_uid = buy_uid;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.price_min = price_min;
        this.price_max = price_max;
        this.comment = comment;
    }

    public Buy(int buy_id, int buy_uid, String isbn10, String isbn13, String title, int price_min, int price_max, String comment, Date created_at, Date updated_at) {
        this.buy_id = buy_id;
        this.buy_uid = buy_uid;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.price_min = price_min;
        this.price_max = price_max;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
