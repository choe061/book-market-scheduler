package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by choi on 2017. 9. 25. PM 4:01.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    private int sale_id;
    private int sale_uid;
    private String isbn10;
    private String isbn13;
    private String title;
    private int price;
    private String comment;
    private Date created_at;
    private Date updated_at;

    private ArrayList<SaleArea> area;
    private ArrayList<SaleImage> images;
}
