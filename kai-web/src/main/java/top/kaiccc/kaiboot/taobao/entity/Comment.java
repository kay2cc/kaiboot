package top.kaiccc.kaiboot.taobao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author kaiccc
 * @date 2019-01-07 10:48
 */
@Data
@Entity
@Table(name = "kai_taobao_comment")
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    /**
     * 评论人
     */
    private String commenterNick;

    /**
     * 评论
     */
    private String content;

    /**
     * 主表 id
     */
    private String codeId;

}
