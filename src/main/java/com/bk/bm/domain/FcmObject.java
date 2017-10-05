package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 26. PM 1:06.
 */

public class FcmObject {

    private String to;
    private Message message;

    public FcmObject(String to, Message message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
