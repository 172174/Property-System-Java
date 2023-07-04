package com.xinyiran.property.entity.rms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Repair {
    private Long id;
    private Long ownerId;
    private String ownerName;
    private String phoneNum;
    private Long houseId;
    private String houseNum;
    private Integer type;
    private String content;
    private String picture;
    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")
    private LocalDateTime updateTime;
    private String maintenanceStaff;
    private Integer delState;
}
