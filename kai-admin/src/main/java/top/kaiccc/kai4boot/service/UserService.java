package top.kaiccc.kai4boot.service;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kaiccc.kai4boot.entity.User;
import top.kaiccc.kai4boot.repository.UserRepository;

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
        if (StrUtil.isEmpty(user.getId())){
            user.setId(null);
            user.setCreateTime(nowTime);

        }
        user.setUpdateTime(nowTime);
        userRepository.save(user);
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
