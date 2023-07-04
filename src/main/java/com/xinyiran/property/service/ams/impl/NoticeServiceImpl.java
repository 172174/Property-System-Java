package com.xinyiran.property.service.ams.impl;

import com.xinyiran.property.entity.ams.Notice;
import com.xinyiran.property.entity.hms.Community;
import com.xinyiran.property.mapper.ams.NoticeMapper;
import com.xinyiran.property.service.ams.NoticeService;
import com.xinyiran.property.service.hms.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private CommunityService communityService;

    /**
     * 查找公告列表
     * @param notice
     * @return
     */
    @Override
    public List<Notice> getPage(Notice notice) {
        return noticeMapper.findPage(notice);
    }

    /**
     * 查找最新公告
     * @return
     */
    @Override
    public Notice getLatestNotice() {
        return noticeMapper.findLatestNotice();
    }

    /**
     * 获取所有公告
     * @return
     */
    @Override
    public List<Notice> getAll() {
        return noticeMapper.findAll();
    }

    @Override
    public Notice getDetails(Long id) {
        Notice notice = noticeMapper.findDetails(id);
        return notice;
    }

    @Override
    public Boolean send(Notice notice) {
        Community community = communityService.getNameById(notice.getCommunityId());
        notice.setCommunityName(community.getName());
        Long is = noticeMapper.add(notice);
        if(is == 1)
            return true;

        return false;
    }

    @Override
    public Boolean delete(Long id) {
        noticeMapper.delete(id);
        return true;
    }
}
