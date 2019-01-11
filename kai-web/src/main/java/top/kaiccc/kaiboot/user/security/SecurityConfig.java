package top.kaiccc.kaiboot.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author kaiccc
 * @date 2018-12-21 15:09
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityServiceImpl securityService;
    private final JwtAuthenticationFilter authenticationFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public SecurityConfig(UserSecurityServiceImpl securityService,
                          JwtAuthenticationFilter authenticationFilter,
                          JwtAuthenticationEntryPoint authenticationEntryPoint) {
        this.securityService = securityService;
        this.authenticationFilter = authenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();              //使用 JWT，关闭csrf防护;

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // 使用token 关闭session

        http.headers().frameOptions().disable();        //springSecurty x-frame-options deny | H2 web 控制终端采用此方式。

        http.authorizeRequests()            //拦截页面
            .antMatchers(                   //静态资源 无授权访问
                    "/",
                    "/static/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v2/**",
                    "/h2web/**",
                    "/sldp",
                    "/tb/**",
                    "/admin//backup",
                    "/admin/user/login/**"
            ).permitAll()
            .anyRequest().authenticated();                                // 除上诉内容，全部页面都要验证

        // 添加JWT filter
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 异常统一处理
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
