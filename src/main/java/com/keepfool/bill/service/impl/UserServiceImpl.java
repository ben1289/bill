package com.keepfool.bill.service.impl;

import com.keepfool.bill.bean.User;
import com.keepfool.bill.mapper.UserMapper;
import com.keepfool.bill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 注册账号
     * @param user
     * @return
     */
    @Override
    public int register(User user) {
        if (userMapper.userCount(user) > 0)
            return -1;
        userMapper.register(user);
        return user.getUserId();
    }

    /**
     * 获取指定用户
     * @param userId
     * @return
     */
    @Override
    public User getUser(int userId) {
        User user = userMapper.getUser(userId);
        if (user.getUserState() == 1)
            return null;
        return user;
    }

    /**
     * 注销用户
     * @param userId
     * @return
     */
    @Override
    public int logout(int userId) {
        return userMapper.logout(userId);
    }
}
