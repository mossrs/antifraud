package com.mossflower.antifraud.admin.service;

import com.mossflower.antifraud.entity.FraudTag;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:09
 */
public interface AdminFraudTagService {

    /**
     * 返回所有的欺诈标签
     *
     * @return 所有的欺诈标签列表
     */
    List<String> getFraudTagList();


    /**
     * 根据欺诈标签名称查询欺诈标签
     *
     * @param fraudTag 欺诈标签实体
     * @return 1 成功 2 已存在 0 失败
     */
    Integer addFraudTag(FraudTag fraudTag);
}
