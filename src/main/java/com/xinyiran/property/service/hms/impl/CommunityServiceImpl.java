package com.xinyiran.property.service.hms.impl;

import com.xinyiran.property.entity.hms.Community;
import com.xinyiran.property.mapper.hms.CommunityMapper;
import com.xinyiran.property.service.hms.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    /**
     * 通过房屋id查询小区
     * @param houseId
     * @return
     */
    @Override
    public Community getInfoByHouseId(Long houseId) {
        return communityMapper.findInfoByhouseId(houseId);
    }

    /**
     * 查询小区信息
     * @return
     */
    @Override
    public Community getInfo() {
        return communityMapper.findInfo();
    }

    @Override
    public Community getNameById(Long communityId) {
        Community community = communityMapper.findById(communityId);
        return community;
    }
}
