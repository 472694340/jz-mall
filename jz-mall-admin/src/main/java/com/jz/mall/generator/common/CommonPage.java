package com.jz.mall.generator.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 *  分页工具类
 *  pagehelper
 * @param <T>
 *     随后考虑将分页功能增强,比如增加排序功能
 */
@Data
public class CommonPage<T> {
    private Integer pageNum; //当前页码
    private Integer pageSize;//每页容量
    private Integer pages;//总页数
    private Long total;//总数量
    private List<T> data;//传入的集合数据

    /**
     *  静态泛型方法
     * @param <T>
     * @return
     */
    public static <T> CommonPage<T> restPage(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        CommonPage<T> result = new CommonPage<>();
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setPages(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return  result;
    }




}
