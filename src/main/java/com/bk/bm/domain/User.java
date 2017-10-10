package com.bk.bm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by choi on 2017. 9. 25. PM 10:50.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int uid;
    private String fcm_token;

}
