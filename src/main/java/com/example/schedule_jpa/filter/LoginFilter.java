package com.example.schedule_jpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] PASS_LIST = {"/users/signup", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse response1 = (HttpServletResponse) response;
        log.info("로그인 필터 로직 실행");

        if (!isPassList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("sessionKey") == null){
                throw new RuntimeException("로그인이 필요합니다.");
            }

            //로그인이 성공한다면 ?? 그니까 세션이있다면 db에서 해당 사용자의 정보꺼내와서 줌
        }


    }

    private boolean isPassList(String requestURI) {
        return PatternMatchUtils.simpleMatch(PASS_LIST, requestURI);
    }
}
