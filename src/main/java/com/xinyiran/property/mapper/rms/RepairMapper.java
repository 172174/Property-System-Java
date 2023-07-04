package com.xinyiran.property.mapper.rms;

import com.xinyiran.property.entity.rms.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairMapper {
    /**
     * 查找列表
     * @param repair
     * @return
     */
    List<Repair> selectPage(Repair repair);

    /**
     * 查找详情
     * @param repairId
     * @return
     */
    Repair selectDetail(Long repairId);

    int insert(Repair repair);

    List<Repair> findByHouseId(Long houseId, Integer state);

    void update(Repair repair);

    void finish(Long id);
}
