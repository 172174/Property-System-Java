package com.xinyiran.property.service.ams;

import com.xinyiran.property.entity.ams.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    List<News> getPage(News news);

    News getDetails(Long newsId);

    List<News> getRecentPage(News news);

    List<News> getNews(News news);

    Boolean delete(Long id);

    Boolean send(News news);
}
