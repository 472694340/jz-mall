package com.jz.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 *   config.addAllowedOrigin("*"); 允许所有域名跨域和允许跨域发送cookie冲突 ?为什么?
 *   和 config.setAllowCredentials(true); 会冲突
 *
 *   当allowCredentials为true时，allowingOrigins不能包含特殊值“ *”，因为无法在“ Access-Control-Allow-Origin”响应标头上设置。
 *   要允许凭据具有一组来源，请明确列出它们或考虑改用“ allowedOriginPatterns”
 */

@Configuration
public class GlobalCorsConfig{

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        //允许所有域名进行跨域调用
        config.addAllowedOriginPattern("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
