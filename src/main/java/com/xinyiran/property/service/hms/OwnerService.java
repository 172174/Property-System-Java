package com.xinyiran.property.service.hms;

import com.xinyiran.property.entity.dto.BindDto;
import com.xinyiran.property.entity.hms.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {

    /**
     * 获取业主详情
     * @param ownerId
     * @return
     */
    Owner getDetails(Long ownerId);

    /**
     * 获取业主列表，分页
     * @return
     */
    List<Owner> getPage(Owner owner);

    /**
     * 新增业主
     * @param owner
     * @return
     */
    Boolean addOwner(Owner owner);

    /**
     * 修改业主信息
     * @param owner
     * @return
     */
    Boolean editOwner(Owner owner);

    /**
     * 获得家庭成员
     * @param houseId
     * @return
     */
    List<Owner> getMembersByHouseId(Long houseId);


    Owner getOwnerByName(String ownerName);

    Boolean bind(Long userId, Long id);

    Boolean untie(Long userId, Long ownerId);
}
