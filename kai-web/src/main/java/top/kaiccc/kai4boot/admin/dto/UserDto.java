package top.kaiccc.kai4boot.admin.dto;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import top.kaiccc.kai4boot.admin.entity.User;

/**
 * 用户 DTO
 * @author kaiccc
 * @date 2018-11-08 14:56
 */
@Data
public class UserDto {

    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 性别 0未填写|1男|2女
     */
    private int gender;

    /**
     * 最后访问时间
     */
    private long visitTime;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 修改时间
     */
    private long updateTime;

    public static User form(UserDto userDto){
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        return user;
    }
}
