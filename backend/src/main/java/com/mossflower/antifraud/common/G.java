package com.mossflower.antifraud.common;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/7/8 9:22
 * G工具类 组合ThreadLocal 利用线程副本机制来实现线程安全 一个线程维护自己的一个ThreadLocalMap key是ThreadLocal value是 T
 * set get remove 时会绑定到当前线程上，他的key是弱引用 因此最好手动remove避免oom(内存溢出)
 * 因为是维护自己的ThreadLocalMap 所以可以存在多个ThreadLocal 或者 一个ThreadLocal里面存放复杂的对象 来确保数据够用
 * ThreadLocal的线程隔离机制 保证了线程安全 且多并发多个线程调用G 也可以保证每个G都是独立的线程
 */
public class G {

    private static final ThreadLocal<DecodedJWT> THREAD_LOCAL_DECODED_JWT = new ThreadLocal<>();

    public static DecodedJWT getDecodeJwt() {
        return THREAD_LOCAL_DECODED_JWT.get();
    }

    public static void setDecodeJwt(DecodedJWT jwt) {
        THREAD_LOCAL_DECODED_JWT.set(jwt);
    }

    public static void removeDecodeJwt() {
        if (THREAD_LOCAL_DECODED_JWT.get() != null) {
            THREAD_LOCAL_DECODED_JWT.remove();
        }
    }

}
