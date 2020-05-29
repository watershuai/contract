package com.admin.contract.dao;

import com.admin.contract.dto.purchaseOrder;

public interface purchaseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(purchaseOrder record);

    int insertSelective(purchaseOrder record);

    purchaseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(purchaseOrder record);

    int updateByPrimaryKey(purchaseOrder record);
}