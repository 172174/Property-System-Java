package com.xinyiran.property.entity.ams;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class News {
    private Long id;
    private String title;
    private String content;
    private String picture;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime updateTime;
    private Integer views;
    private Integer type;
    private String source;
    private Integer delState;
}
