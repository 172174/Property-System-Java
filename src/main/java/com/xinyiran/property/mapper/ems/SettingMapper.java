package com.xinyiran.property.mapper.ems;

import com.xinyiran.property.entity.ems.Setting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettingMapper {
    List<Setting> selectList(Setting setting);

    Setting selectById(Long id);

    Setting selectDetail(Long feesId);
}

