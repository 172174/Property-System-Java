package com.xinyiran.property.service.pms.impl;

import com.xinyiran.property.entity.pms.Record;
import com.xinyiran.property.mapper.pms.RecordMapper;
import com.xinyiran.property.service.pms.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordService;

    /**
     * 车辆进场记录，分页
     * @param record
     * @return
     */
    @Override
    public List<Record> getPage(Record record) {
        return recordService.selectPage(record);
    }

    /**
     * 车辆进入，添加记录
     * @param numberPlate
     */
    @Override
    public void inRecord(String numberPlate, Integer inType, LocalDateTime inTime) {

    }

    /**
     * 车辆离开，更新记录
     * @param numberPlate
     */
    @Override
    public void outRecord(String numberPlate,Integer outType,LocalDateTime outTime) {

    }
}
