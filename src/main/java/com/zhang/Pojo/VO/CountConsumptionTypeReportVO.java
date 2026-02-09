package com.zhang.Pojo.VO;

import com.zhang.Constant.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountConsumptionTypeReportVO {
    private  List<String> eventTypeList = EventType.eventTypeList;
    private  List<Integer> countList;
}
