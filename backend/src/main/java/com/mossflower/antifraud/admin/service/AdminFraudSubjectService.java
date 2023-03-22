package com.mossflower.antifraud.admin.service;

import com.mossflower.antifraud.entity.FraudSubject;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/17 9:32
 */
public interface AdminFraudSubjectService {

    /**
     * 增加诈骗主体
     *
     * @param fraudSubject 诈骗题目
     * @return 若已存在返回 2 ，否则返回 1 系统异常返回 0
     */
    Integer save(FraudSubject fraudSubject);

    /**
     * 删除一条
     *
     * @param id 诈骗主体id
     * @return 是否删除成功
     */
    Boolean deleteOne(Long id);

    /**
     * 批量删除
     *
     * @param ids 诈骗主体id数组
     * @return 是否删除成功
     */
    Boolean deleteBatch(List<Long> ids);

    /**
     * 更新一条
     *
     * @param fraudSubject 诈骗主体
     * @return 是否更新成功
     */
    Boolean update(FraudSubject fraudSubject);

    /**
     * 查询所有欺诈主体
     *
     * @return 所有欺诈主体
     */
    List<FraudSubject> getAll();

}
