package com.admin.contract.dto;

import lombok.Data;

@Data
public class Contract {
    private Integer id;

    private String wzName;

    private String skuName;

    private String measurement;

    private String supplier;

    private String contractCode;

    private Integer number;

    private Integer overNamber;

    private String price;

    private String amount;

    private Integer priceType;

    private String createTime;

    private String updateTime;

    private Integer status;

    private String reserve1;

    private String reserve2;

    private String reserve3;

    private String reserve4;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 页面大小
     */
    private Integer pageSize;

}