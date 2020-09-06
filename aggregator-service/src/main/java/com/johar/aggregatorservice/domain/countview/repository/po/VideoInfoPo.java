package com.johar.aggregatorservice.domain.countview.repository.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: VideoInfoPo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 00:23
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_video_info")
@Data
public class VideoInfoPo {

    @Id
    @GeneratedValue
    private long id;

    private String name;


    private long spaceId;
}
