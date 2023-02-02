package com.chenw.base.common.security.config;

import cn.hutool.core.util.StrUtil;
import com.chenw.base.common.core.constant.TokenConstants;
import com.chenw.base.common.core.exception.auth.AccessdeniedException;
import com.chenw.base.common.core.exception.auth.UnauthorizedException;
import com.chenw.base.common.security.utils.JwtUtil;
import com.chenw.user.api.feign.RemoteUserSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: JwtAuthenticationTokenFilter
 * @Description: JwtAuthenticationTokenFilter
 * @Author ChenXiaoW
 * @Date 2023/01/10 - 22:26
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RemoteUserSecurityService remoteUserSecurityService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        log.debug("token constant：{}",token);

        if (StrUtil.isEmpty(token)){
            throw new UnauthorizedException();
        }
        if (!token.startsWith(TokenConstants.PREFIX)){
            throw new AccessdeniedException();
        }

        String realToken = token.replace(TokenConstants.PREFIX, "");

        boolean tokenExpired = JwtUtil.isTokenExpired(realToken);
        if (tokenExpired){
            throw new AccessdeniedException("认证已过期，请重新认证");
        }


    }

}
