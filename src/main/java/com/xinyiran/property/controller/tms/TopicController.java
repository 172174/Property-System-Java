package com.xinyiran.property.controller.tms;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.tms.Topic;
import com.xinyiran.property.service.tms.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class TopicController extends BaseController {

    @Autowired
    private TopicService topicService;

    /**
     * 查询topic
     * @param topic
     * @return
     */
    @GetMapping("/topic/page")
    public Result page(Topic topic){
        startPage();
        List<Topic> topics = topicService.getPage(topic);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        return Result.succ(pageInfo);
    }

}
