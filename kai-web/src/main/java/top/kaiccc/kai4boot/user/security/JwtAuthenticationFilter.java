package top.kaiccc.kai4boot.user.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       /* String header = request.getHeader(jwTautil.getJwtHeader());

        if (header == null || !header.startsWith(jwTautil.getTokenHead())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);


        SecurityContextHolder.getContext().setAuthentication(authentication);*/
       log.info("doFilterInternal!!!!!!!!!!!");
       chain.doFilter(request, response);

    }

}
