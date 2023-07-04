package com.xinyiran.property.service.rms.Impl;

import com.xinyiran.property.entity.rms.Repair;
import com.xinyiran.property.mapper.rms.RepairMapper;
import com.xinyiran.property.service.rms.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;
    /**
     * 获得列表
     * @param repair
     * @return
     */
    @Override
    public List<Repair> getPage(Repair repair) {
        return repairMapper.selectPage(repair);
    }

    /**
     * 获取详情
     * @param repairId
     * @return
     */
    @Override
    public Repair getDetail(Long repairId) {
        return repairMapper.selectDetail(repairId);
    }

    /**
     * 用户查询报修情况
     * @param houseId
     * @param state
     * @return
     */
    @Override
    public List<Repair> getPageByUser(Long houseId, Integer state) {
        return repairMapper.findByHouseId(houseId,state);
    }

    @Override
    public Boolean update(Repair repair) {
        repairMapper.update(repair);
        return true;
    }

    @Override
    public Boolean finish(Long id) {
        repairMapper.finish(id);
        return true;
    }

    /**
     * 创建报修订单
     * @param repair
     * @return
     */
    @Override
    public Boolean creatRepairs(Repair repair) {
        int is = repairMapper.insert(repair);
        return is > 0;
    }
}
