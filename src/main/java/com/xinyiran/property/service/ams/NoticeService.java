package com.xinyiran.property.service.ams;

import com.xinyiran.property.entity.ams.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    List<Notice> getPage(Notice notice);

    Notice getLatestNotice();

    List<Notice> getAll();

    Notice getDetails(Long id);

    Boolean send(Notice notice);

    Boolean delete(Long id);
}
