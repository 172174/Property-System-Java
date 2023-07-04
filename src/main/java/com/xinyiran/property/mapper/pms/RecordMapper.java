package com.xinyiran.property.mapper.pms;

import com.xinyiran.property.entity.pms.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> selectPage(Record record);
}
