package top.kaiccc.kai4boot.admin.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kaiccc.kai4boot.admin.entity.User;
import top.kaiccc.kai4boot.common.exception.RestException;
import top.kaiccc.kai4boot.admin.repository.UserRepository;

/**
 * @author kaiccc
 * @date 2018-11-08 16:34
 */
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(rollbackFor=Exception.class)
    public void save(User user){
        long nowTime = System.currentTimeMillis();

        user.setUsername(user.getUsername().toLowerCase());
        if (StrUtil.isEmpty(user.getId())){
            user.setCreateTime(nowTime);

            if (isExistingUser(user.getUsername())){
                throw new RestException(StrUtil.format("{} 用户名称已存在，请更换用户名！", user.getUsername()));
            }
        }
        user.setUpdateTime(nowTime);
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
