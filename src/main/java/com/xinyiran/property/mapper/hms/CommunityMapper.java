package com.xinyiran.property.mapper.hms;

import com.xinyiran.property.entity.hms.Community;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    Community findInfoByhouseId(Long houseId);

    Community findInfo();

    Community findById(Long communityId);
}
