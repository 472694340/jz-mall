package com.jz.mall.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义未验证结果返回
 */
public class AuthenticationEntryPointFilter implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;uft-8");
        PrintWriter writer = response.getWriter();
        writer.write(e.getMessage());//这里应该自定义一个未验证的异常
        writer.flush();
        writer.close();
    }
}
