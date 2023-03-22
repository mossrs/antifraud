package com.mossflower.antifraud.admin.service;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/29 11:56
 */
public interface AdminFraudTypeService {

    /**
     * 返回诈骗类型列表
     *
     * @return 诈骗类型列表
     */
    List<String> getFraudTypeList();
}
