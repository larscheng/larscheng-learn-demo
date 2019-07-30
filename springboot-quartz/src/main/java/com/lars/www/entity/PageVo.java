package com.lars.www.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhengql
 * @description 通用分页查询类
 * @className PageVo
 * @create 2017年12月12日  14:44
 */
@ApiModel("通用分页查询参数")
public class PageVo {

    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private Integer page = 1;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;

    /**
     * 搜索关键词
     */
    @ApiModelProperty("搜索关键词")
    private String search;

    /**
     * 时间开始区间
     */
    @ApiModelProperty("时间开始区间")
    private String searchBgnTime;

    /**
     * 时间结束区间
     */
    @ApiModelProperty("时间结束区间")
    private String searchEndTime;

    /**
     * 按某字段排序
     */
    @ApiModelProperty("按某字段排序")
    private String sort;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearchBgnTime() {
        return searchBgnTime;
    }

    public void setSearchBgnTime(String searchBgnTime) {
        this.searchBgnTime = searchBgnTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}


