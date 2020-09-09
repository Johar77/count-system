package com.johar.countingservice.domain.countview.repository.po;

import lombok.Data;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, name = "name")
    private String name;

    private long spaceInfoId;
}
