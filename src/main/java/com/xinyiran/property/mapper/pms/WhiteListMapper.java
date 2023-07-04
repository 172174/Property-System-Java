package com.xinyiran.property.mapper.pms;

import com.xinyiran.property.entity.pms.WhiteList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WhiteListMapper {
    List<WhiteList> selectPage(WhiteList whiteList);

    WhiteList selectDetail(Long whiteListId);
}
