package com.xinyiran.property.entity.tms;

import lombok.Data;

@Data
public class Like {
    private Long id;
    private Long userId;
    private Long topicId;
    private Integer delState;
}

