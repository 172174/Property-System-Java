package com.xinyiran.property.entity.tms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private String content;
    private Long userId;
    private String nickName;
    private String username;
    private String picture;
    private String avatar;
    private Long topicId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime commentTime;
    private Integer floor;
    private Integer delState;
}
