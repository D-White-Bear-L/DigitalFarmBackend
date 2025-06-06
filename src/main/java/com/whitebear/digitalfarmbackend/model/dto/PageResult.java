package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> list; // 分页结果，使用泛型
    private long total;
    private int pageNum;
    private int pageSize;

    public PageResult(List<T> list, long total, int pageNum, int pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
