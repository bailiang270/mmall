package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServiceResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by win on 2018/5/15.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServiceResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount==0){
            return ServiceResponse.createByErrorMessage("该用户不存在！");
        }
        //密码转成MD5
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5Password);
        if (user==null){
            return ServiceResponse.createByErrorMessage("密码错误！");
        }
        //密码至空
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess(user,"登录成功！");
    }
    @Override
    public ServiceResponse<User> register(User user){
        int resultCount = userMapper.checkUsername(user.getUsername());
        if(resultCount==0){
            return ServiceResponse.createByErrorMessage("该用户不存在！");
        }
        resultCount = userMapper.checkEmail(user.getEmail());
        if (resultCount==0){
            return  ServiceResponse.createByErrorMessage("email已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        resultCount = userMapper.insert(user);
        if (resultCount==0){
            return ServiceResponse.createByErrorMessage("注册失败！");
        }
        return ServiceResponse.createBySuccessMessage("注册成功！");
    }


}
