package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 10. 2. PM 8:57.
 */
@Data
@AllArgsConstructor
public class SaleImage {

    private int sale_image_id;
    private String image_url;
    private int sale_id;

}
