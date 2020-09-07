package com.johar.aggregatorservice.domain.countview.repository.facade;

import com.johar.aggregatorservice.domain.countview.repository.po.VideoStatsPo;

import java.util.List;

/**
 * @ClassName: CountViewRepositoryInterface
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 23:50
 * @Since: 1.0.0
 */
public interface CountViewRepositoryInterface {

    void save(VideoStatsPo videoStatsPo);

    void save(List<VideoStatsPo> videoStatsPoList);
}
