package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 25. PM 4:01.
 */


public class HttpResponse {
    private boolean result;
    private Object message;

    public HttpResponse(boolean result, Object message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
