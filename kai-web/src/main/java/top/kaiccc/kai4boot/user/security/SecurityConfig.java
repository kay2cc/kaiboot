package top.kaiccc.kai4boot.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author kaiccc
 * @date 2018-12-21 15:09
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityServiceImpl securityService;

    @Autowired
    public SecurityConfig(UserSecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.securityService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();              //使用 JWT，关闭csrf防护;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // 使用token 关闭session

        http.authorizeRequests()            //拦截页面
            .antMatchers(                   //静态资源 无授权访问
                    HttpMethod.GET,
                    "/",
                    "/static/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v2/**",
                    "/h2web/**"
            ).permitAll()
            .antMatchers("/admin/user/**").permitAll()        //添加用户接口
            .anyRequest().authenticated();                                // 除上诉内容，全部页面都要验证

        http
            .formLogin()                                        //  定义当需要用户登录时候，转到的登录页面。
            .loginPage("/static/403.html");                    // 登录页

            //.successHandler(authenticationSuccessHandler);       //登录成功处理

        // 添加JWT filter
        http.addFilter(new JwtLoginFilter(authenticationManager()))
            .addFilter(new JwtAuthenticationFilter(authenticationManager()));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
