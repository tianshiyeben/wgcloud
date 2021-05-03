package com.wgcloud.entity;

import java.io.Serializable;


public class BaseEntity implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 8698319936744959815L;


    private String id;

    private Integer page;

    private Integer pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPage() {
        if (page == null) {
            page = 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 20;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
