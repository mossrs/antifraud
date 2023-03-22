package com.mossflower.antifraud.interceptor;


import com.mossflower.antifraud.common.R;
import com.mossflower.antifraud.common.exception.BusinessException;
import com.mossflower.antifraud.common.exception.SystemException;
import com.mossflower.antifraud.common.msgcode.ExceptionMsgCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author z's'b
 * 异常处理器 将所有的错误都抛到表现层 然后可以精准捕获每一个异常
 * 两种写法 最好精准捕获  自己捕获异常并抛出自己的异常 不要处理统一交到异常处理器处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionInterceptor {

    /**
     * 手动捕获
     * 记录日志
     * 提示用户规范操作
     */
    @ExceptionHandler({ServletException.class, ValidationException.class,
            BindException.class, BusinessException.class})
    public R<Object> catchBusinessException(Exception e) {
        log.error("==========>" + e.getMessage(), e);
        return R.err(ExceptionMsgCode.BUSINESS_ERR.getMsg(), ExceptionMsgCode.BUSINESS_ERR.getCode());
    }

    /**
     * 服务器数据库错误，超时，网络错误等 手动捕获
     * 记录日志
     * 发送消息给开发人员和运维人员
     * 安抚用户重试
     */
    @ExceptionHandler({SystemException.class, SQLException.class, IOException.class, RuntimeException.class})
    public R<Object> catchSystemException(Exception e) {
        log.error("==========>" + e.getMessage(), e);
        return R.err(ExceptionMsgCode.SYSTEM_ERR.getMsg(), ExceptionMsgCode.SYSTEM_ERR.getCode());
    }

    /**
     * 未知其他异常 一般是没有捕获到的异常
     * 记录日志
     * 发送消息给开发和运维人员
     * 安抚用户 响应数据
     */
    @ExceptionHandler(Exception.class)
    public R<Object> catchSqlException(Exception e) {
        log.error("==========>" + e.getMessage(), e);
        return R.err(ExceptionMsgCode.SERVER_ERR.getMsg(), ExceptionMsgCode.SERVER_ERR.getCode());
    }
}