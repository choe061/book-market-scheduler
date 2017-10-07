package com.bk.bm.domain;

/**
 * Created by choi on 2017. 9. 25. PM 6:29.
 */

public class Matching {

    private int id;
    private int sale_id;
    private int buy_id;

    private int sale_uid;
    private int buy_uid;

    public Matching() {
    }

    public Matching(int sale_id, int buy_id, int sale_uid, int buy_uid) {
        this.sale_id = sale_id;
        this.buy_id = buy_id;
        this.sale_uid = sale_uid;
        this.buy_uid = buy_uid;
    }

    public Matching(int id, int sale_id, int buy_id, int sale_uid, int buy_uid) {
        this.id = id;
        this.sale_id = sale_id;
        this.buy_id = buy_id;
        this.sale_uid = sale_uid;
        this.buy_uid = buy_uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getBuy_id() {
        return buy_id;
    }

    public void setBuy_id(int buy_id) {
        this.buy_id = buy_id;
    }

    public int getSale_uid() {
        return sale_uid;
    }

    public void setSale_uid(int sale_uid) {
        this.sale_uid = sale_uid;
    }

    public int getBuy_uid() {
        return buy_uid;
    }

    public void setBuy_uid(int buy_uid) {
        this.buy_uid = buy_uid;
    }
}
