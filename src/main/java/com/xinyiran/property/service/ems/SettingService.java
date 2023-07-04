package com.xinyiran.property.service.ems;

import com.xinyiran.property.entity.ems.Setting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettingService {
    List<Setting> getListPage();

    List<Setting> getList(Setting setting);

    Setting getDetail(Long feesId);
}
