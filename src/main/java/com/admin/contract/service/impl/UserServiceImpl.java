package com.admin.contract.service.impl;

import com.admin.contract.dao.UserMapper;
import com.admin.contract.dto.User;
import com.admin.contract.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

   public User login(String userName, String password){
        return userMapper.login(userName, password);
    }

    @Override
    public int register(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> select(String phone) {
        return  userMapper.selectByPrimaryKey(phone);
    }

    @Override
    public User personInfo(Long id) {
        return userMapper.personInfo(id);
    }
}
