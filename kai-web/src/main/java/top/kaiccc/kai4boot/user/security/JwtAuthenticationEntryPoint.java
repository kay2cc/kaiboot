package top.kaiccc.kai4boot.user.security;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.kaiccc.kai4boot.common.utils.RestResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 异常统一处理
 * @author kaiccc
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.getWriter().write(
                new Gson().toJson(
                        RestResponse.failed(HttpServletResponse.SC_FORBIDDEN,
                                            StrUtil.format("用户认证失败，请登录后请求接口！{}", authException.getMessage()
        ))));
    }
}
