package com.xinyiran.property.mapper.ams;

import com.xinyiran.property.entity.ams.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> findByPage(News news);



    News findById(Long newsId);

    List<News> findRecentNews(News news);

    List<News> findNews(News news);

    Long delete(Long id);

    Long save(News news);
}
