package com.xinyiran.property.service.hms;

import com.xinyiran.property.entity.hms.Community;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService {
    Community getInfoByHouseId(Long houseId);

    Community getInfo();

    Community getNameById(Long communityId);
}
