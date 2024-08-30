package com.licole.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回
 * @param <T>
 */
@Data
public final class PageResult<T> implements Serializable {

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 总条数
     */
    private Long total = 1L;

    /**
     * 总页数
     */
    private Long pages = 10L;

    public PageResult() {
    }

    public PageResult(List<T> list, Long total, Long pages) {
        this.list = list;
        this.total = total;
        this.pages = pages;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

}
