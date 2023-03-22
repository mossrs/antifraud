package com.mossflower.antifraud.common.msgcode;

/**
 * @author z's'b
 */
public enum ExceptionMsgCode implements CommonMsgCode {

    /**
     * 面向用户的业务操作错误，采用各功能模块的消息码
     * 系统程序和服务器的异常使用默认的
     * */
    BUSINESS_ERR("客户端异常，请重试！！！", 40000),
    SYSTEM_ERR("系统故障，请稍后再试！！！", 50000),
    SERVER_ERR("服务器出现未知错误，请稍后再试！！！", 59999);

    private final String msg;
    private final Integer code;

    ExceptionMsgCode(String msg, Integer code) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
