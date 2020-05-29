package com.admin.contract.service;

import com.admin.contract.dto.Contract;

import java.util.List;

public interface ContractService {
    int insertSelective(Contract record);
    List<Contract> selectList(Contract record);
}
