package com.johar.countingservice.domain.videoinfo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: SpaceInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 14:57
 * @Since: 1.0.0
 */
@Data
public class SpaceInfo {
    private int id;
    private String name;
    private Date createTime;
    private Date updateTime;
}
