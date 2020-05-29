package com.admin.contract.dao;

import com.admin.contract.dto.snponId;

public interface snponIdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(snponId record);

    int insertSelective(snponId record);

    snponId selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(snponId record);

    int updateByPrimaryKey(snponId record);
}