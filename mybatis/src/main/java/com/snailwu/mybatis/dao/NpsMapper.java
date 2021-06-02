package com.snailwu.mybatis.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author WuQinglong
 * @date 2021/5/26 2:31 下午
 */
public interface NpsMapper {

    void npsTable(
            @Param("term") Integer term,
            @Param("city") String city,
            @Param("classForm") String classForm,
            @Param("department") String department,
            @Param("showType") String showType
    );

    void npsDiff(
            @Param("term") Integer term,
            @Param("diffTerm") Integer diffTerm,
            @Param("city") String city,
            @Param("classForm") String classForm,
            @Param("department") String department,
            @Param("showType") String showType
    );

}
