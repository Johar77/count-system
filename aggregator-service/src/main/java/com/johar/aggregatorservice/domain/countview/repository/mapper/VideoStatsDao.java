package com.johar.aggregatorservice.domain.countview.repository.mapper;

import com.johar.aggregatorservice.domain.countview.entity.EventType;
import com.johar.aggregatorservice.domain.countview.repository.po.VideoStatsPo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: VideoStatsDao
 * @Description: 必须要创建Repository，才会自动创建表
 * @Author: Johar
 * @Date: 2020-09-07 23:27
 * @Since: 1.0.0
 */
@Repository
public interface VideoStatsDao extends JpaRepository<VideoStatsPo, Long> {
    //List<VideoStatsDao> findByVideoInfoIdAndEventTypeAndTimestampBetween(long videoInfoId, EventType eventType, Pageable pageable, Sort sort);


}
