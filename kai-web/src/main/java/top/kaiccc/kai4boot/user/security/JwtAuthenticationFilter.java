package top.kaiccc.kai4boot.user.security;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 认证 过滤器
 * @author kaiccc
 * @date 2018-12-22 16:03
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String tokenHeader;
    private final JwtUtil jwtUtil;
    private final UserSecurityServiceImpl securityService;

    public JwtAuthenticationFilter(@Value("${jwt.header}") String tokenHeader,
                                   JwtUtil jwtUtil,
                                   UserSecurityServiceImpl securityService) {
        this.tokenHeader = tokenHeader;
        this.jwtUtil = jwtUtil;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authTokenHeader = request.getHeader(this.tokenHeader);

        String username = null;
        if (StrUtil.isNotBlank(authTokenHeader)) {
            username = jwtUtil.parseJwtUsername(authTokenHeader);
        }

        if (StrUtil.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.securityService.loadUserByUsername(username);
            // 可以加 强校验
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
