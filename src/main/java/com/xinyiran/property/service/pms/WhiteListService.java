package com.xinyiran.property.service.pms;

import com.xinyiran.property.entity.pms.WhiteList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WhiteListService {
    /**
     * 车辆白名单列表，分页
     * @param whiteList
     * @return
     */
    List<WhiteList> getPage(WhiteList whiteList);

    /**
     * 车辆白名单详情
     * @param whiteListId
     * @return
     */
    WhiteList getDetail(Long whiteListId);
}
