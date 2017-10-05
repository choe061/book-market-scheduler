package com.bk.bm.domain;

/**
 * Created by choi on 2017. 9. 25. PM 6:29.
 */

public class Matching {

    private int id;
    private int s_id;
    private int b_id;

    private int s_uid;
    private int b_uid;

    public Matching(int id, int s_id, int b_id, int s_uid, int b_uid) {
        this.id = id;
        this.s_id = s_id;
        this.b_id = b_id;
        this.s_uid = s_uid;
        this.b_uid = b_uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getS_uid() {
        return s_uid;
    }

    public void setS_uid(int s_uid) {
        this.s_uid = s_uid;
    }

    public int getB_uid() {
        return b_uid;
    }

    public void setB_uid(int b_uid) {
        this.b_uid = b_uid;
    }
}
