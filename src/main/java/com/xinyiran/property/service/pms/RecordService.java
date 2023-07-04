package com.xinyiran.property.service.pms;

import com.xinyiran.property.entity.pms.Record;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface RecordService {
    /**
     * 车辆进出记录，分页
     * @param record
     * @return
     */
    List<Record> getPage(Record record);

    void inRecord(String numberPlate, Integer inType, LocalDateTime inTime);

    void outRecord(String numberPlate,Integer outType,LocalDateTime outTime);
}
