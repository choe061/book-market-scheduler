package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by choi on 2017. 10. 2. PM 8:53.
 */

@Data
@AllArgsConstructor
public class BuyArea implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_area_id;
    private String area;
    private int buy_id;
}
