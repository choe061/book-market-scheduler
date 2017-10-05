package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 26. PM 1:36.
 */

public class Message {

    private String title;
    private String content;
    private String message;

    public Message(String title, String content, String message) {
        this.title = title;
        this.content = content;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
