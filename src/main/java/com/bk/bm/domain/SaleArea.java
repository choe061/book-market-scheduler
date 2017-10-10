package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 10. 2. PM 8:57.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleArea {

    private int sale_area_id;
    private String area;
    private int sale_id;

}
