package com.mossflower.antifraud.config;

import com.mossflower.antifraud.common.JsonObjectMapper;
import com.mossflower.antifraud.interceptor.JwtInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author z's'b
 * 拦截器级别
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private JsonObjectMapper jsonObjectMapper;

    /**
     * 跨域访问  maxAge 单位秒
     */
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(60 * 60 * 24);
    }

    /**
     * jwt
     * 注意 在添加或要排除的请求时 不要加上你配置文件里的前缀 如：/api/users/login 不要加上/api 正解 /users/login
     */
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns("/students/login", "/admins/login", "/wx/**");
    }

    /**
     * 序列化
     */
    @Override
    public void extendMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(jsonObjectMapper);
        converters.add(0, converter);
    }
}
