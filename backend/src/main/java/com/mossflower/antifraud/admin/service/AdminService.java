package com.mossflower.antifraud.admin.service;

/**
 * @author z's'b
 */


public interface AdminService {
    /**
     * 管理员登录
     *
     * @param adminCard 管理员账号
     * @param password  密码
     * @return 用户token
     */
    String login(String adminCard, String password);

    /**
     * 管理员登出
     *
     * @return 登出结果
     */
    Boolean logout();
}
