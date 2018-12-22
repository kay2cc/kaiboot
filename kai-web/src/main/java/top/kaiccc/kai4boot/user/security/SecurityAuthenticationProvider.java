package top.kaiccc.kai4boot.user.security;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  自定义身份认证验证组件
 * @author kaiccc
 * @date 2018-12-22 15:14
 */
@Slf4j
public class SecurityAuthenticationProvider {
    private UserSecurityServiceImpl securityService;

    SecurityAuthenticationProvider(UserSecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 认证逻辑
        UserDetails userDetails = securityService.loadUserByUsername(name);
        if(ObjectUtil.isNotNull(userDetails)){
            log.info(userDetails.getPassword(), password);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if(passwordEncoder.matches(password, userDetails.getPassword())){

                // 生成令牌
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
                return auth;
            }else {
                throw new BadCredentialsException("密码错误");
            }
        }else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     * @param authentication
     * @return
     */
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
