package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //校验用户名是否存在
    int checkUsername(String username);
    //校验密码和用户名时候正确
    User selectLogin(@Param("username")String username,@Param("password")String password);
    //校验Email是否存在
    int checkEmail(String email);
}