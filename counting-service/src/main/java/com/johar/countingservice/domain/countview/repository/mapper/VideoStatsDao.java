package com.johar.countingservice.domain.countview.repository.mapper;


import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.domain.countview.repository.po.VideoStatsPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: VideoStatsDao
 * @Description: 必须要创建Repository，才会自动创建表
 * @Author: Johar
 * @Date: 2020-09-07 23:27
 * @Since: 1.0.0
 */
@Repository
public interface VideoStatsDao extends JpaRepository<VideoStatsPo, Long> {

    @Query(value = "select count(a.count) from VideoStatsPo a where a.id = ?1 and a.eventType = ?2 and a.timestamp between ?3 and ?4")
    long countVideo(long videoId, EventType eventType, long startTime, long endTime);
}
