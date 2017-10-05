package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by choi on 2017. 10. 2. PM 8:53.
 */
@Data
@AllArgsConstructor
public class BuyImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_img_id;
    private String image_url;
    private int buy_id;
}
