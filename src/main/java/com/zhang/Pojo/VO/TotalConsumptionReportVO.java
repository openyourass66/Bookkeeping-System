package com.zhang.Pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalConsumptionReportVO {
    private List<LocalDate> dateList;
    private  List<Double> amountList;
}
