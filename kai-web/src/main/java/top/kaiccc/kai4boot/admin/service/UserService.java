package top.kaiccc.kai4boot.admin.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kaiccc.kai4boot.admin.entity.User;
import top.kaiccc.kai4boot.admin.repository.UserRepository;
import top.kaiccc.kai4boot.common.exception.RestException;

/**
 * @author kaiccc
 * @date 2018-11-08 16:34
 */
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    /**
     * 登录
     * @param username
     * @param password
     *
     */
    public void login(String username, String password){

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
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
