package com.lantu.config;

//import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * @Author itmei
 * @Date 2023/10/10 15:04
 * @description:
 * @Title: MyCorsConfig
 * @Package com.lantu.config
 */
//配置允许跨域的问题
@Configuration
public class MyCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:9999"); // 允许谁来访问
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*"); // 允许传输方法
        configuration.addAllowedHeader("*"); // 允许所有的请求头调用

        //过滤器
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
