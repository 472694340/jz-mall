package com.jz.mall.filter;

import cn.hutool.json.JSONUtil;
import com.jz.mall.common.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义未登录结果返回
 */
public class AccessDeniedHandlerFilter implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        writer.flush();
        writer.close();
    }
}
