package com.xinyiran.property.entity.tms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Topic {
    private Long id;
    private String title;
    private String content;
    private String picture;
    private Long userId;
    private String avatar;
    private String nickName;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime createTime;
    private Integer views;
    private Integer likes;
    private Integer commentCount;
    private Integer delState;
    List<Comment> comments;
}
