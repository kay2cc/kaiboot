package top.kaiccc.kai4boot.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户
 * @author kaiccc
 * @date 2018-11-08 14:56
 */
@Entity
@Table(name = "kai_admin_user")
@Data
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "name", length = 32)
    private String name;

    /**
     * 密码
     */
    @Column(name = "password", length = 32)
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick", length = 32)
    private String nick;

    /**
     * 邮箱
     */
    @Column(name = "email", length = 128)
    private String email;

    /**
     * 电话号码
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * 性别 0未填写|1男|2女
     */
    @Column(name = "gender", length = 2)
    private int gender;

    /**
     * 最后访问时间
     */
    @Column(name = "visit_time", length = 13)
    private long visitTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time", length = 13)
    private long createTime;

    /**
     * 修改时间
     */
    @Column(name = "updat_time", length = 13)
    private long updateTime;


}
