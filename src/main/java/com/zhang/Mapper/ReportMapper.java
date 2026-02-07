package com.zhang.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    /**
     * 年龄统计
     * @return
     */
    List<Map<String, Integer>> ageReport();

    /**
     * 性别统计
     * @return
     */
    List<Map<String, Integer>> genderReport();

    /**
     * 事件统计
     * @return
     */
    List<Map<String, Integer>> eventReport();
}
