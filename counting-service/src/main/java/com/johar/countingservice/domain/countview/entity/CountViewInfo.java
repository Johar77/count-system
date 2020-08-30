package com.johar.countingservice.domain.countview.entity;

import com.johar.countingservice.domain.countview.entity.valueobject.CountPerMinute;
import com.johar.countingservice.domain.videoinfo.entity.VideoInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: CountViewInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 15:03
 * @Since: 1.0.0
 */
@Data
public class CountViewInfo {
    private final VideoInfo videoInfo;

    private List<CountPerMinute> currentMinuteCountInfo;

    public CountViewInfo(VideoInfo videoInfo){
        this.videoInfo = videoInfo;
        this.currentMinuteCountInfo = new ArrayList<>();
    }

    public CountViewInfo(VideoInfo videoInfo, CountPerMinute countInfo){
        this.videoInfo = videoInfo;
        this.currentMinuteCountInfo = new ArrayList<>();
        this.currentMinuteCountInfo.add(countInfo);
    }

    public void update(CountPerMinute countInfo){

    }
}
