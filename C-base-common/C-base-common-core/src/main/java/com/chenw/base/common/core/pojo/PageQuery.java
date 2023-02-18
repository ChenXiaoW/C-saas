package com.chenw.base.common.core.pojo;

/**
 * @ClassName: PageQuery
 * @Description: PageQuery
 * @Author ChenXiaoW
 * @Date 2023/02/15 - 22:18
 */
public class PageQuery {

    /**
     * 页面 默认1
     */
    private Integer current = 1;

    /**
     * 每页大小 默认10
     */
    private Integer size = 10;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
