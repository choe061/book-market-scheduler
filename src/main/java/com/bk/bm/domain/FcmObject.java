package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by choi on 2017. 9. 26. PM 1:06.
 */

@Data
@AllArgsConstructor
public class FcmObject {

    private String to;
    private Message message;

}
