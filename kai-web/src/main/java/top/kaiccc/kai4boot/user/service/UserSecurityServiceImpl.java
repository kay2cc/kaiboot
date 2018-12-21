package top.kaiccc.kai4boot.user.service;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.kaiccc.kai4boot.admin.entity.User;
import top.kaiccc.kai4boot.admin.service.UserService;
import top.kaiccc.kai4boot.user.entity.SecurityUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 安全服务
 * @author kaiccc
 * @date 2018-12-21 15:20
 */
@Slf4j
@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userName : " + username);
        User user = userService.findUserByUsername(username);
        if (ObjectUtil.isNull(user)){
            throw new UsernameNotFoundException(username + " 用户不存在");
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        return from(user, authorityList);
    }

    private SecurityUser from(User user, List<SimpleGrantedAuthority> authorityList){
        return new SecurityUser(user.getUsername(), user.getPassword(),authorityList);
    }

    /**
     * 用户权限 控制
     * @param user
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(User user){
        // TODO 用户权限，应该在数据库中查询到对应ROLE信息
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorityList;
    }
}
