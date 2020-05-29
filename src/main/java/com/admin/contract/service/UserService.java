package com.admin.contract.service;

import com.admin.contract.dto.User;

import java.util.List;

public interface UserService {
    public User login(String userName, String password);
    int register(User user);
    public List<User> select(String phone);
    public User personInfo(Long id);
}
