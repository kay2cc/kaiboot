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
@Table(name = "kai_taobao_pics")
public class Pics {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;
    /**
     * 图片地址
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件保存位置
     */
    private String filePath;

    /**
     * 主表 id
     */
    private String codeId;

    /**
     * 是否下载完成
     */
    private boolean completed;

    /**
     * 标题
     */
    @Column(length = 2000)
    private String title;
}
