package com.xinyiran.property.mapper.hms;

import com.xinyiran.property.entity.hms.Owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OwnerMapper {

    Long insertOwner(Owner owner);

    int updateOwner(Owner owner);


    List<Owner> selectOwnerList();

    List<Owner> selectList(Owner owner);

    Owner selectOwnerDetails(Long ownerId);

    List<Owner> findMember(Long houseId);

    Owner selectByOwnerName(String ownerName);

    Long bind(Long userId, Long ownerId);

    Long untie(Long userId, Long ownerId);
}
