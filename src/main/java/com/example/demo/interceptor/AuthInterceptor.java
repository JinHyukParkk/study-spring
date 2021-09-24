package com.example.demo.interceptor;

import com.example.demo.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        log.info("request url : {}", url);

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        // Auth 권한을 가진 요청에 대해서는
        if (hasAnnotation) {
            // 권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);

            if (query.equals("name=steve")) {
                return true;
            }

            return false;
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class claszz) {
        // resource javascript, html
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethodAnnotation(claszz) != null || handlerMethod.getBeanType().getAnnotation(claszz) != null) {
            // Auth annotation 이 있을 때는 true
            return true;
        }

        return false;
    }
}
