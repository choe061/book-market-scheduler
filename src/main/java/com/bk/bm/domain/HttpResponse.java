package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 25. PM 4:01.
 */

@Data
@AllArgsConstructor
public class HttpResponse {

    private boolean result;

    private Object message;

}
