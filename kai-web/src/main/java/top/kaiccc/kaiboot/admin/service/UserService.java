package top.kaiccc.kaiboot.admin.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kaiccc.kaiboot.admin.entity.User;
import top.kaiccc.kaiboot.admin.repository.UserRepository;
import top.kaiccc.kaiboot.common.exception.RestException;
import top.kaiccc.kaiboot.user.security.JwtUtil;
import top.kaiccc.kaiboot.user.security.UserSecurityServiceImpl;

/**
 * @author kaiccc
 * @date 2018-11-08 16:34
 */
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSecurityServiceImpl securityService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    /**
     * 登录
     * @param username
     * @param password
     *
     */
    public String login(String username, String password){

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (DisabledException e) {
            log.error("用户认证失败", e);
            throw new RestException(StrUtil.format("用户认证失败: {}", e), e);
        } catch (BadCredentialsException e){
            log.error("用户认证失败", e);
            throw new RestException("用户认证失败: 账号或密码错误", e);
        } catch (UsernameNotFoundException e){
            log.error("用户认证失败", e);
            throw new RestException("用户认证失败: 用户不存在", e);
        }

        // jwt 返回
        User user = this.findUserByUsername(username);
        return jwtUtil.createJWT(user);
    }

    @Transactional(rollbackFor=Exception.class)
    public void save(User user){
        long nowTime = System.currentTimeMillis();

        user.setUsername(user.getUsername().toLowerCase());
        if (StrUtil.isEmpty(user.getId())){
            user.setCreateTime(nowTime);

            if (isExistingUser(user.getUsername())){
                throw new RestException(StrUtil.format("{} 用户名称已存在，请更换用户名！", user.getUsername()));
            }
            if (StrUtil.isBlank(user.getPassword())){
                throw new RestException("请填写密码!");
            }
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (StrUtil.isNotBlank(user.getPassword())){
            user.setPassword(encoder.encode(user.getPassword()));
        }
        user.setUpdateTime(nowTime);
        log.info(user.toString());

        userRepository.save(user);
    }

    /**
     * 是否 是已存在用户
     * @param name
     * @return
     */
    private boolean isExistingUser(String name){
        User user = findUserByUsername(name.toLowerCase());
        return ObjectUtil.isNotNull(user) && StrUtil.isNotBlank(user.getId());
    }

    public User findUserByUsername(String name){
        return userRepository.findUserByUsername(name.toLowerCase());
    }

    @Autowired
    public UserService(UserRepository userRepository,
                       UserSecurityServiceImpl securityService,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
}
