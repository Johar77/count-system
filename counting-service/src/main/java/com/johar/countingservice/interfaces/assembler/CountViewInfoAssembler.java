package com.johar.countingservice.interfaces.assembler;

import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.domain.countview.entity.VideoInfo;
import com.johar.countingservice.interfaces.dto.ViewCountDto;

import java.util.Date;

/**
 * @ClassName: CountViewInfoAssembler
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 08:38
 * @Since: 1.0.0
 */
public class CountViewInfoAssembler {

    public static CountViewInfo toDo(Date countTime, int videoId, EventType eventType, int count){

        VideoInfo videoInfo = VideoInfo.builder()
                .id(videoId)
                .name("测试")
                .build();
        CountViewInfo countViewInfo = CountViewInfo
                                            .builder()
                                            .countTime(countTime)
                                            .eventType(eventType)
                                            .count(count)
                                            .videoInfo(videoInfo)
                                            .build();
        return countViewInfo;
    }

    public static ViewCountDto toDto(CountViewInfo countViewInfo){
        ViewCountDto viewCountDto = ViewCountDto.builder()
                .count(countViewInfo.getCount())
                .eventType(countViewInfo.getEventType())
                .build();

        if (countViewInfo.getVideoInfo() != null){
            viewCountDto.setVideoId((int)countViewInfo.getVideoInfo().getId());
            viewCountDto.setVideoName(countViewInfo.getVideoInfo().getName());

            if (countViewInfo.getVideoInfo().getSpaceInfo() != null){
                viewCountDto.setSpaceName(countViewInfo.getVideoInfo().getSpaceInfo().getName());
            }
        }

        return viewCountDto;
    }
}
