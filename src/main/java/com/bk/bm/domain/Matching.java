package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Created by choi on 2017. 9. 25. PM 6:29.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matching {

    private int matching_id;
    private int sale_id;
    private int buy_id;
    private Date matched_at;
    private boolean status;


    public Matching(int sale_id, int buy_id) {
        this.sale_id = sale_id;
        this.buy_id = buy_id;
    }

}
