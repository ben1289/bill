package com.keepfool.bill.mapper;

import com.keepfool.bill.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int userCount(User user);
    int register(User user);
    User getUser(int userId);
    int logout(int userId);
}
