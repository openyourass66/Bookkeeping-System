package com.zhang.Service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 年龄统计
     */
    List<Map<String,Integer>> ageReport();

    /**
     * 性别统计
     */
    List<Map<String, Integer>> genderReport();

    /**
     * 消费事件统计
     */
    List<Map<String, Integer>> eventReport();
}
