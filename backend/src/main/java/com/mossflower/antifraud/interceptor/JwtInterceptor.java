package com.mossflower.antifraud.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mossflower.antifraud.common.Constant;
import com.mossflower.antifraud.common.G;
import com.mossflower.antifraud.common.exception.BusinessException;
import com.mossflower.antifraud.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author z's'b
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String OPTIONS = "OPTIONS";

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        if (OPTIONS.equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader(Constant.HEADERS_TOKEN_PARAM_NAME);
        if (token == null) {
            throw new BusinessException("无token");
        }
        try {
            DecodedJWT jwt = JwtUtil.verifyToken(token);
            G.setDecodeJwt(jwt);
            return true;
        } catch (SignatureVerificationException e) {
            throw new BusinessException("token签名错误");
        } catch (TokenExpiredException e) {
            throw new BusinessException("token过期");
        } catch (InvalidClaimException e) {
            throw new BusinessException("token参数错误");
        } catch (AlgorithmMismatchException e) {
            throw new BusinessException("token算法失效");
        } catch (Exception e) {
            throw new BusinessException("token验证失败");
        }
//        try {
//            Claims claims = JwtUtil.parseToken(token);
//            G.setClaims(claims);
//            return true;
//        } catch (SignatureException e) {
//            throw new BusinessException("token签名错误");
//        } catch (ExpiredJwtException e) {
//            throw new BusinessException("token过期");
//        } catch (MalformedJwtException e) {
//            throw new BusinessException("token参数错误");
//        } catch (IllegalArgumentException e) {
//            throw new BusinessException("token算法失效");
//        } catch (Exception e) {
//            throw new BusinessException("token验证失败");
//        }
    }
}
