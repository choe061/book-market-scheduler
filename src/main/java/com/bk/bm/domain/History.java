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

    private int buy_id;
    private int seller_uid;
    private int buyer_uid;
    private String isbn10;
    private String isbn13;
    private String title;
    private int price;
    private String comment;
    private Date created_at;
    private Date updated_at;
}
