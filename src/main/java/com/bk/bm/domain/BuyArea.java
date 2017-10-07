package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by choi on 2017. 10. 2. PM 8:53.
 */

public class BuyArea implements Serializable {
    private static final long serialVersionUID = 1L;

    private int buy_area_id;
    private String area;
    private int buy_id;

    public BuyArea() {
    }

    public BuyArea(int buy_area_id, String area, int buy_id) {
        this.buy_area_id = buy_area_id;
        this.area = area;
        this.buy_id = buy_id;
    }

    public int getBuy_area_id() {
        return buy_area_id;
    }

    public void setBuy_area_id(int buy_area_id) {
        this.buy_area_id = buy_area_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }
}
