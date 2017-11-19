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
public class SaleImage {

    private int sale_image_id;
    private String image_url;
    private int sale_id;

    public SaleImage(String image_url, int sale_id) {
        this.image_url = image_url;
        this.sale_id = sale_id;
    }
}
