package com.admin.contract.service.impl;

import com.admin.contract.dao.ContractMapper;
import com.admin.contract.dao.UserMapper;
import com.admin.contract.dto.Contract;
import com.admin.contract.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractMapper contractMapper;

    @Override
    public int insertSelective(Contract record) {
        return contractMapper.insertSelective(record);
    }

    @Override
    public List<Contract> selectList(Contract record) {
        return contractMapper.selectList(record);
    }
}
