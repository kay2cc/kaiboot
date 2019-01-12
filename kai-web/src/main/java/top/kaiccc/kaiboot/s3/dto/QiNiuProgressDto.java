package top.kaiccc.kaiboot.s3.dto;

import java.util.List;

public class QiNiuProgressDto {

    /**
     * size : 5519632573        文件大小
     * offset : 4664066048      已完成
     * modify_time : 1547216879000  最后一片完成时间
     * contexts : []            已完成集合
     */

    private long size;
    private long offset;
    private long modify_time;
    private List<String> contexts;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getModify_time() {
        return modify_time;
    }

    public void setModify_time(long modify_time) {
        this.modify_time = modify_time;
    }

    public List<String> getContexts() {
        return contexts;
    }

    public void setContexts(List<String> contexts) {
        this.contexts = contexts;
    }
}
