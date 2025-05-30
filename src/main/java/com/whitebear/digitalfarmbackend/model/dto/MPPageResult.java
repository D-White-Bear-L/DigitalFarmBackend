package com.whitebear.digitalfarmbackend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MPPageResult<T> {
    private List<T> list;
    private long total;
    private int pageNum;
    private int pageSize;

    public MPPageResult(List<T> list, long total, int pageNum, int pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
