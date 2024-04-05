package com.theodo.albeniz.configs;

import com.theodo.albeniz.Interceptors.LibraryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LibraryInterceptorConfig implements WebMvcConfigurer {

  @Autowired
  LibraryInterceptor libraryInterceptor;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
      .addInterceptor(libraryInterceptor)
      .addPathPatterns("/library/*")
    ;
  }
}
