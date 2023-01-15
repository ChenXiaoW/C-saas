package com.chenw.base.common.security.config;

import cn.hutool.json.JSONUtil;
import com.chenw.base.common.core.enums.BaseCodeEnum;
import com.chenw.base.common.core.pojo.Response;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: RestUnauthenticationEntryPoint
 * @Description: RestUnauthenticationEntryPoint
 * @Author ChenXiaoW
 * @Date 2023/01/10 - 22:14
 */
@Component
public class RestUnauthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Response response = new Response(BaseCodeEnum.UNAUTHORIZED.getCode(), BaseCodeEnum.UNAUTHORIZED.getMessage(), null);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(response));
        httpServletResponse.getWriter().flush();
    }
}
