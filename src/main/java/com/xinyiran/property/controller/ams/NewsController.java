package com.xinyiran.property.controller.ams;

import com.github.pagehelper.PageInfo;
import com.xinyiran.property.common.Result;
import com.xinyiran.property.controller.BaseController;
import com.xinyiran.property.entity.ams.News;
import com.xinyiran.property.entity.ams.Notice;
import com.xinyiran.property.service.ams.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ams")
public class NewsController extends BaseController {
    @Autowired
    private NewsService newsService;

    /**
     * 查找新闻列表
     * @param news
     * @return
     */
    @GetMapping("/news/page")
    public Result page(News news){
        startPage();
        List<News> newsList = newsService.getPage(news);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);
        return Result.succ(pageInfo);
    }


    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/news/info/{id}")
    public Result info(@PathVariable("id")Long id){
        News news = newsService.getDetails(id);
        return Result.succ(news);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/news/delete/{id}")
    public Result delete(@PathVariable("id")Long id){
        Boolean delete = newsService.delete(id);
        if(delete)
            return Result.succ(null);
        return Result.succ(201,"删除失败",null);
    }

    /**
     * 添加
     * @param news
     * @return
     */
    @PostMapping("/news/save")
    public Result send(@RequestBody() News news){
        System.out.println(news);
        Boolean is =newsService.send(news);
        if(is)
            return Result.succ(null);
        return Result.succ(201,"发送失败",null);
    }


}
