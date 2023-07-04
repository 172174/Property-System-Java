package com.xinyiran.property.service.pms.impl;

import com.xinyiran.property.entity.pms.WhiteList;
import com.xinyiran.property.mapper.pms.WhiteListMapper;
import com.xinyiran.property.service.pms.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    private WhiteListMapper whiteListMapper;

    /**
     * 车辆白名单列表，分页
     * @param whiteList
     * @return
     */
    @Override
    public List<WhiteList> getPage(WhiteList whiteList) {
        return whiteListMapper.selectPage(whiteList);
    }

    /**
     * 车辆白名单详细
     * @param whiteListId
     * @return
     */
    @Override
    public WhiteList getDetail(Long whiteListId) {
        return whiteListMapper.selectDetail(whiteListId);
    }
}
