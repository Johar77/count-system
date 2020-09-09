package com.johar.countingservice.domain.countview.repository.po;


import com.johar.countingservice.domain.countview.entity.EventType;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: VideoStatsPo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 00:23
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_video_stats")
@Data
public class VideoStatsPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long timestamp;

    private int count;

    private EventType eventType;

    private long videoInfoId;
}
