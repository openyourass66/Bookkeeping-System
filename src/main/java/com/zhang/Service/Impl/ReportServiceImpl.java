package com.zhang.Service.Impl;

import com.zhang.Mapper.ReportMapper;
import com.zhang.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper ReportMapper;
    /**
     * 年龄统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> ageReport() {
        return ReportMapper.ageReport();
    }
    /**
     * 性别统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> genderReport() {
        return ReportMapper.genderReport();
    }
    /**
     * 事件统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> eventReport() {
        return ReportMapper.eventReport();
    }
}
