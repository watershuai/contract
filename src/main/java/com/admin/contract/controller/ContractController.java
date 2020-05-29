package com.admin.contract.controller;

import com.admin.contract.dto.Contract;
import com.admin.contract.service.ContractService;
import com.admin.contract.service.UserService;
import com.admin.contract.utils.ResponseBean;
import com.admin.contract.utils.RundomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @ResponseBody
    @PostMapping("/list")
    public ResponseBean<Object> list(@RequestBody(required = false)Contract contract) {
        try {
            List<Contract> contracts=contractService.selectList(contract);
            return new ResponseBean<Object>(200,"查询成功",contracts);
        }catch (Exception e){
            return new ResponseBean<Object>(205,"查询异常");
        }

    }
    @ResponseBody
    @PostMapping("/insert")
    public ResponseBean<Object> insert(@RequestBody(required = false)Contract contract) {
        try {
            contract.setCreateTime(RundomUtils.getNowTime());
            contract.setOverNamber(contract.getNumber());
            if (contract.getNumber() != null && StringUtils.isNotEmpty(contract.getPrice())){
                int price=Integer.parseInt(contract.getPrice());
                contract.setAmount(String.valueOf(price * contract.getNumber()));
            }
            int num=contractService.insertSelective(contract);
            if (num > 0 ){
                return new ResponseBean<Object>(200,"新增成功");
            }else {
                return new ResponseBean<Object>(201,"新增失败");
            }
        }catch (Exception e){
            return new ResponseBean<Object>(205,"新增异常");
        }

    }
}
