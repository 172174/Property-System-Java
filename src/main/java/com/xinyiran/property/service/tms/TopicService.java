package com.xinyiran.property.service.tms;

import com.xinyiran.property.entity.tms.Comment;
import com.xinyiran.property.entity.tms.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {
    List<Topic> getList(Integer type);

    Topic getDetails(Long topicId);

    boolean insertTopic(Topic topic);

    boolean like(Long userId,Long topicId);

    boolean comment(Comment comment);

    List<Topic> getPage(Topic topic);
}
