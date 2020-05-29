package com.admin.contract.dao;

import com.admin.contract.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 登陆方法
     */
    User login(@Param("userName") String userName, @Param("password") String password);

    User personInfo(Long id);
}