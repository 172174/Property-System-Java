package com.xinyiran.property.service.ams.impl;

import com.xinyiran.property.entity.ams.News;
import com.xinyiran.property.mapper.ams.NewsMapper;
import com.xinyiran.property.service.ams.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查找新闻列表,后台
     * @param news
     * @return
     */
    @Override
    public List<News> getPage(News news) {
        return newsMapper.findByPage(news);
    }


    /**
     * 查找最近公告列表，app
     * @param news
     * @return
     */
    @Override
    public List<News> getRecentPage(News news) {
        return newsMapper.findRecentNews(news);
    }

    /**
     * 查找公告列表，app
     * @param news
     * @return
     */
    @Override
    public List<News> getNews(News news) {
        return newsMapper.findNews(news);
    }

    @Override
    public Boolean delete(Long id) {
        Long delete = newsMapper.delete(id);
        if(delete == 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean send(News news) {
        Long i = newsMapper.save(news);
        if(i == 1)
            return true;
        return false;
    }

    /**
     * 获得新闻详情
     * @param newsId
     * @return
     */
    @Override
    public News getDetails(Long newsId) {
        return newsMapper.findById(newsId);
    }
}
