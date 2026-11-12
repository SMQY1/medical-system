package com.jishou.medical.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * 分页返回结构，供列表接口统一使用
 */
public class PageResult<T> implements Serializable {

    /** 当前页数据 */
    private List<T> records;
    /** 总记录数 */
    private long total;
    /** 当前页码 */
    private long pageNum;
    /** 每页条数 */
    private long pageSize;
    /** 总页数 */
    private long pages;

    public PageResult() {
    }

    public PageResult(List<T> records, long total, long pageNum, long pageSize, long pages) {
        this.records = records;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
    }

    public static <T> PageResult<T> of(IPage<T> page) {
        return new PageResult<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize(), page.getPages());
    }

    /** 分页数据需要转换为 VO 时使用 */
    public static <E, T> PageResult<T> of(IPage<E> page, Function<E, T> converter) {
        List<T> list = page.getRecords().stream().map(converter).toList();
        return new PageResult<>(list, page.getTotal(), page.getCurrent(), page.getSize(), page.getPages());
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }
}
