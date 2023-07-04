package com.xinyiran.property.service.rms;

import com.xinyiran.property.common.Result;
import com.xinyiran.property.entity.rms.Repair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepairService {
    List<Repair> getPage(Repair repair);

    Repair getDetail(Long repairId);

    Boolean creatRepairs(Repair repair);

    List<Repair> getPageByUser(Long houseId, Integer state);

    Boolean update(Repair repair);

    Boolean finish(Long id);
}
