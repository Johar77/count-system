package com.johar.aggregatorservice.domain.countview.repository.po;

import com.johar.aggregatorservice.domain.countview.entity.EventType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @ClassName: VideoStatsPo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 00:23
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_video_stats")
public class VideoStatsPo {

    @Id
    @GeneratedValue
    private long id;

    private Timestamp timestamp;

    private int count;

    private EventType eventType;
}
