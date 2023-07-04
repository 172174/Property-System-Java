package com.xinyiran.property.mapper.tms;

import com.xinyiran.property.entity.tms.Comment;
import com.xinyiran.property.entity.tms.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {
    List<Topic> findPopularTopic();

    List<Topic> findRecentTopic();

    Topic findDetails(Long topicId);

    List<Comment> findComment(Long topicId);

    int insetTopic(Topic topic);

    int insertLike(Long userId, Long topicId);

    int insertComment(Comment comment);

    int addLikes(Long topicId);

    int addCommentCount(Long topicId);

    List<Topic> findListByPage(Topic topic);
}
