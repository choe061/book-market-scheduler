package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 25. PM 10:50.
 */

public class User {

    private int uid;
    private String fcm_token;

    public User(int uid, String fcm_token) {
        this.uid = uid;
        this.fcm_token = fcm_token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
