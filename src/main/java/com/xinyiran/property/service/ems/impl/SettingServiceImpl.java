package com.xinyiran.property.service.ems.impl;

import com.xinyiran.property.entity.ems.Setting;
import com.xinyiran.property.mapper.ems.SettingMapper;
import com.xinyiran.property.service.ems.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingMapper settingMapper;
    /**
     * 查询费用项列表，分页
     * @return
     */
    @Override
    public List<Setting> getListPage() {
        Setting setting = new Setting();
        return settingMapper.selectList(setting);
    }

    /**
     * 查询费用项
     * @param setting
     * @return
     */
    @Override
    public List<Setting> getList(Setting setting) {
        return settingMapper.selectList(setting);
    }

    /**
     * 查询详情
     * @param feesId
     * @return
     */
    @Override
    public Setting getDetail(Long feesId) {
        return settingMapper.selectDetail(feesId);
    }
}
