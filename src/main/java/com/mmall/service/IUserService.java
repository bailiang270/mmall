package com.mmall.service;

import com.mmall.common.ServiceResponse;
import com.mmall.pojo.User;

/**
 * Created by win on 2018/5/15.
 */
public interface IUserService {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ServiceResponse<User> login (String username, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    ServiceResponse<User> register(User user);
}
