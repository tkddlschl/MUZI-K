package com.simple.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   //스프링부트의 빈 설정파일
public class WebConfig implements WebMvcConfigurer {
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      
      //정적 리소스 자원 경로 추가1
        registry
                .addResourceHandler( "/img/**")
                .addResourceLocations("classpath:/static/img/");

        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        
        registry
              .addResourceHandler("/js/**")
              .addResourceLocations("classpath:/static/js/");
      
        registry
               .addResourceHandler("/vendor/**")
               .addResourceLocations("classpath:/static/vendor/");
        
        registry
		        .addResourceHandler("/mp3/**")
		        .addResourceLocations("file:/usr/local/tomcat/webapps/muzik/WEB-INF/classes/static/mp3/");
        
        registry
        .addResourceHandler("/display/**")
        .addResourceLocations("file:/usr/local/tomcat/webapps/upload/");
        
        registry
        .addResourceHandler("/display3/**")
        .addResourceLocations("file:/usr/local/tomcat/webapps/upload/user/");
      
        		
        
        
   }
}
