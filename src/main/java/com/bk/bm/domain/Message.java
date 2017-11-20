package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 9. 26. PM 1:36.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String title;
    private String message;

}
