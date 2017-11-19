package com.bk.bm.service;

import com.bk.bm.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by choi on 2017. 10. 30. AM 1:16.
 */
@Transactional
public interface UserService {

    User createUser(User user);

    @Transactional(readOnly = true)
    User getUser(int uid);

    boolean updateUser(User user);

    boolean deleteUser(int uid);

}
