package com.bk.bm.service;

import com.bk.bm.domain.User;
import com.bk.bm.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by choi on 2017. 10. 30. AM 1:18.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUser(int uid) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int uid) {
        return false;
    }
}
