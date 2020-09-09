package com.johar.countingservice.domain.countview.repository.persistence;

import com.johar.commonlib.api.ResultCode;
import com.johar.commonlib.error.ServiceException;
import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.domain.countview.entity.SpaceInfo;
import com.johar.countingservice.domain.countview.entity.VideoInfo;
import com.johar.countingservice.domain.countview.repository.facade.CountViewInterface;
import com.johar.countingservice.domain.countview.repository.mapper.SpaceInfoDao;
import com.johar.countingservice.domain.countview.repository.mapper.VideoInfoDao;
import com.johar.countingservice.domain.countview.repository.mapper.VideoStatsDao;
import com.johar.countingservice.domain.countview.repository.po.SpaceInfoPo;
import com.johar.countingservice.domain.countview.repository.po.VideoInfoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: CountViewImpl
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-09 23:29
 * @Since: 1.0.0
 */
@Repository
public class CountViewImpl implements CountViewInterface {

    @Autowired
    private SpaceInfoDao spaceInfoDao;

    @Autowired
    private VideoInfoDao videoInfoDao;

    @Autowired
    private VideoStatsDao videoStatsDao;

    @Override
    public CountViewInfo findCountViewInfo(Long videoId, EventType eventType, @NotNull Date startTime, @NotNull Date endTime) {
        if (endTime.getTime() <= startTime.getTime()){
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR, "endTime must be greater than startTIme");
        }

        VideoInfoPo videoInfoPo = videoInfoDao.getOne(videoId);
        if (videoInfoPo == null){
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR, "videoId not exist");
        }
        SpaceInfoPo spaceInfoPo = spaceInfoDao.getOne(videoInfoPo.getSpaceInfoId());
        if (spaceInfoPo == null){
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR, "spaceInfoId not exist");
        }

        long count = videoStatsDao.countVideo(videoId, eventType, startTime.getTime(), endTime.getTime());
        SpaceInfo spaceInfo = SpaceInfo.builder()
                .id(videoInfoPo.getSpaceInfoId())
                .name(spaceInfoPo.getName())
                .build();
        VideoInfo videoInfo = VideoInfo.builder()
                .id(videoInfoPo.getId())
                .name(videoInfoPo.getName())
                .spaceInfo(spaceInfo)
                .build();
        CountViewInfo result = CountViewInfo.builder()
                .count(count)
                .eventType(eventType)
                .videoInfo(videoInfo)
                .build();

        return result;
    }
}
