package com.jishou.medical.dto;

/**
 * 分页查询参数，供科室、医生、患者列表查询复用
 */
public class PageQuery {

    private Integer pageNum = 1;
    private Integer pageSize = 10;

    /** 页码兜底：小于 1 时按第一页处理 */
    public long normalizedPageNum() {
        return pageNum == null || pageNum < 1 ? 1L : pageNum;
    }

    /** 每页条数兜底：默认 10 条，最大 100 条 */
    public long normalizedPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 10L;
        }
        return Math.min(pageSize, 100);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
