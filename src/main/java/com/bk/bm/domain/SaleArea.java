package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 10. 2. PM 8:57.
 */
@Data
@AllArgsConstructor
public class SaleArea {

    private int sale_area_id;
    private String area;
    private int sale_id;
}
