package com.xinyiran.property.service.tms.impl;

import com.xinyiran.property.entity.tms.Comment;
import com.xinyiran.property.entity.tms.Topic;
import com.xinyiran.property.mapper.tms.TopicMapper;
import com.xinyiran.property.service.tms.TopicService;
import com.xinyiran.property.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TopicServiceImpl implements TopicService {


    @Autowired
    private TopicMapper topicMapper;




    /**
     * 获取主题列表
     * @param type
     * @return
     */
    @Override
    public List<Topic> getList(Integer type) {
        if(type == null){
            return null;
        }
        List<Topic> topics;
        if(type == 1){//1热门 2最新
            topics = topicMapper.findPopularTopic();
            return topics;
        }
        return topicMapper.findRecentTopic();

    }

    /**
     * 获取详情
     * @param topicId
     * @return
     */
    @Override
    public Topic getDetails(Long topicId) {
        if(topicId == null){
            return null;
        }
        Topic topic = topicMapper.findDetails(topicId);
        List<Comment> comments = topicMapper.findComment(topicId);
        topic.setComments(comments);
        return topic;
    }

    /**
     * 发帖
     * @param topic
     * @return
     */
    @Override
    public boolean insertTopic(Topic topic) {
        int is = topicMapper.insetTopic(topic);
        return is != 0;
    }

    @Transactional
    @Override
    public boolean like(Long userId, Long topicId) {
        if(userId == null || topicId == null){
            return false;
        }
        int i = topicMapper.insertLike(userId,topicId);
        int i1 = topicMapper.addLikes(topicId);
        if(i != 1 && i1 != 1){
            return false;
        }
        return true;
    }

    @Override
    public List<Topic> getPage(Topic topic) {
        return topicMapper.findListByPage(topic);
    }

    @Transactional
    @Override
    public boolean comment(Comment comment) {
        if(Objects.isNull(comment) || Objects.isNull(comment.getTopicId()) || Objects.isNull(comment.getUserId()) || StringUtils.isEmpty(comment.getContent())){
            return false;
        }
        int i = topicMapper.insertComment(comment);
        int i1 = topicMapper.addCommentCount(comment.getTopicId());
        if(i != 1 && i1 != 1){
            return false;
        }
        return true;
    }
}
