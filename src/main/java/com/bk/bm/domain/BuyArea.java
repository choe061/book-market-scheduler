package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 10. 2. PM 8:53.
 */

@Data
@AllArgsConstructor
public class BuyArea {

    private int buy_area_id;
    private String area;
    private int buy_id;

}
