package com.theodo.albeniz.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class LibraryInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull Object handler){

    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    return true;
  }

  @Override
  public void afterCompletion(
    HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull Object handler,
    Exception ex) {
    long startTime = (long) request.getAttribute("startTime");
    long duration = System.currentTimeMillis() - startTime;
    log.info("Request Path: {}, Duration: {} ms", request.getRequestURI(), duration);
  }
}
