package com.johar.aggregatorservice.domain.countview.repository.persistence;

import com.johar.aggregatorservice.domain.countview.repository.facade.CountViewRepositoryInterface;
import com.johar.aggregatorservice.domain.countview.repository.mapper.VideoStatsDao;
import com.johar.aggregatorservice.domain.countview.repository.po.VideoStatsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: CountViewRepositoryImpl
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 23:58
 * @Since: 1.0.0
 */
@Repository
public class CountViewRepositoryImpl implements CountViewRepositoryInterface {
    @Autowired
    private VideoStatsDao videoStatsDao;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void save(VideoStatsPo videoStatsPo) {
        videoStatsDao.save(videoStatsPo);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void save(List<VideoStatsPo> videoStatsPoList) {
        videoStatsDao.saveAll(videoStatsPoList);
    }
}
