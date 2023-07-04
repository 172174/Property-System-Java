package com.xinyiran.property.entity.dto;

import lombok.Data;

@Data
public class UserOwnerDto {
    private Long userId;
    private String username;
    private String avatar;
    private Long ownerId;
    private String ownerName;
    private String phoneNum;
    private String idCard;
    private Integer homeowner;
    private Long houseId;
    private String houseNum;
}
