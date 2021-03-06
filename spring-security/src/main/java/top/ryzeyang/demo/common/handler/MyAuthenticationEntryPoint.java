package top.ryzeyang.demo.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.ryzeyang.demo.common.api.CommonResult;
import top.ryzeyang.demo.common.api.ResultEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Ken
 * @date 2020/11/10 下午 04:15
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
//         认证失败
        writer.write(new ObjectMapper().writeValueAsString(new CommonResult<>(ResultEnum.AUTHENTICATION_ERROR, e.getMessage())));
        writer.flush();
        writer.close();
    }
}
