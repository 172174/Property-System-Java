package com.xinyiran.property.mapper.ams;

import com.xinyiran.property.entity.ams.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> findAll();


    List<Notice> findPage(Notice notice);

    Notice findLatestNotice();

    Notice findDetails(Long id);

    Long add(Notice notice);

    void delete(Long id);
}
