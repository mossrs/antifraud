package com.mossflower.antifraud.common;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author z's'b
 */
@Data
public class R<T> implements Serializable {
    private String msg;
    private Integer code;
    private T data;

    @NotNull
    public static <T> R<T> ok(String msg, Integer code , T data) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(data);
        return r;
    }

    @NotNull
    public static <T> R<T> ok(String msg, Integer code) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(null);
        return r;
    }

    @NotNull
    public static <T> R<T> ok(String msg) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(1);
        r.setData(null);
        return r;
    }

    @NotNull
    public static <T> R<T> ok(String msg, T data) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(1);
        r.setData(data);
        return r;
    }

    @NotNull
    public static <T> R<T> err(String msg, Integer code, T data) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(data);
        return r;
    }

    @NotNull
    public static <T> R<T> err(String msg, Integer code) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(null);
        return r;
    }

    @NotNull
    public static <T> R<T> err(String msg) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(0);
        r.setData(null);
        return r;
    }

    @NotNull
    public static <T> R<T> err(String msg, T data) {
        R<T> r = new R<>();
        r.setMsg(msg);
        r.setCode(0);
        r.setData(data);
        return r;
    }

}