package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by choi on 2017. 10. 2. PM 8:53.
 */

public class BuyImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_img_id;
    private String image_url;
    private int buy_id;

    public BuyImage() {
    }

    public BuyImage(int buy_img_id, String image_url, int buy_id) {
        this.buy_img_id = buy_img_id;
        this.image_url = image_url;
        this.buy_id = buy_id;
    }

    public int getBuy_img_id() {
        return buy_img_id;
    }

    public void setBuy_img_id(int buy_img_id) {
        this.buy_img_id = buy_img_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }
}
