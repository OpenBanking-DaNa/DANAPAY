package com.dana.danapay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ResourceHandlerRegistry에 addResourceHandlers메서드를 이용해 어느 경로로 들어왔을때 매핑이 되어줄 것인지를 정의한다.
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static"); // 이후 addResourceLocations 메서드를 이용하여 실제 파일이 있는 경로를 지정해 준다.


        // 이미지 불러올 수 있도록 세팅
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/upload/");
    }


}
