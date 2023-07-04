package com.xinyiran.property.service.hms.impl;

import com.xinyiran.property.entity.hms.House;
import com.xinyiran.property.entity.hms.Owner;
import com.xinyiran.property.mapper.hms.HouseMapper;
import com.xinyiran.property.mapper.hms.OwnerMapper;
import com.xinyiran.property.service.hms.OwnerService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    private HouseMapper houseMapper;

    /**
     * 查询业主详情
     * @param ownerId
     * @return
     */
    @Override
    public Owner getDetails(Long ownerId) {
        return ownerMapper.selectOwnerDetails(ownerId);
    }

    /**
     * 查询业主列表
     * @return
     */
    @Override
    public List<Owner> getPage(Owner owner) {
        List<Owner> owners = ownerMapper.selectList(owner);
        return owners;
    }

    /**
     * 新增业主
     * @param owner
     * @return
     */
    @Transactional
    @Override
    public Boolean addOwner(Owner owner) {
        Long houseId = owner.getHouseId();
        if(StringUtils.isNull(houseId)){
            return false;
        }
        House house = houseMapper.selectHouseDetails(owner.getHouseId());
        if(StringUtils.isNull(house)){
            return false;
        }
        //居民入户时判断是否入住，若没入住则创建入住时间
        if(house.getCheckInTime() == null)
            houseMapper.updateCheckIntime(house.getId());
        String houseNum = house.getBuildingName()+"-"+house.getUnit()+"-"+house.getHouseNum();
        owner.setHouseNum(houseNum);
        Long is = ownerMapper.insertOwner(owner);
        if(is != 1){
            return false;
        }
        Long iss = houseMapper.binding(houseId,owner.getId());
        return true;
    }

    /**
     * 更新业主
     * @param owner
     * @return
     */
    @Override
    public Boolean editOwner(Owner owner) {
        if(StringUtils.isNull(owner.getHouseId())){
            return false;
        }
        House house = houseMapper.selectHouseDetails(owner.getHouseId());
        if(StringUtils.isNull(house)){
            return false;
        }
        String houseNum = house.getBuildingName()+"-"+house.getUnit()+"-"+house.getHouseNum();
        owner.setHouseNum(houseNum);
        int is = ownerMapper.updateOwner(owner);
        if(StringUtils.isNull(is)){
            return false;
        }
        int i = houseMapper.updateBinding(owner.getHouseId(), owner.getId());
        if(!(i == 1)){
            return false;
        }
        return true;
    }

    /**
     * 获得家庭成员
     * @param houseId
     * @return
     */
    @Override
    public List<Owner> getMembersByHouseId(Long houseId) {
        return ownerMapper.findMember(houseId);
    }

    @Override
    public Owner getOwnerByName(String ownerName) {
        System.out.println(ownerName);
        Owner owner = ownerMapper.selectByOwnerName(ownerName);
        return owner;
    }

    @Override
    public Boolean bind(Long userId, Long id) {
        Long bind = ownerMapper.bind(userId, id);
        if(bind == 1){
            return true;
        }
        return false;
    }

    /**
     * 解绑
     * @param userId
     * @param ownerId
     * @return
     */
    @Override
    public Boolean untie(Long userId, Long ownerId) {
        Long i = ownerMapper.untie(userId,ownerId);
        if(i == 1)
            return true;
        return false;
    }
}
