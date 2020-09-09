package com.johar.countingservice.interfaces.facade;

import com.johar.commonlib.api.BaseResponse;
import com.johar.commonlib.api.CommonResponse;
import com.johar.commonlib.api.ResultCode;
import com.johar.countingservice.application.service.CountViewApplicationService;
import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.infrastructure.util.DateUtil;
import com.johar.countingservice.interfaces.assembler.CountViewInfoAssembler;
import com.johar.countingservice.interfaces.dto.TimeRangeDto;
import com.johar.countingservice.interfaces.dto.ViewCountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


/**
 * @ClassName: QueryViewApi
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 13:17
 * @Since: 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/view/query")
public class QueryViewApi {

    @Autowired
    private CountViewApplicationService countViewApplicationService;

    @PostMapping(path = "/{videoId}/view-event/count")
    public BaseResponse findViewsCount(@PathVariable("videoId") int videoId, @RequestBody @Valid TimeRangeDto timeRangeDto){
        CountViewInfo countViewInfo = countViewApplicationService.findViewsCount((long)videoId, DateUtil.toDate(timeRangeDto.getStartTime()), DateUtil.toDate(timeRangeDto.getEndTime()));
        return new CommonResponse<ViewCountDto>(CountViewInfoAssembler.toDto(countViewInfo));
    }

    @PostMapping(path = "/{videoId}/event/{eventType}/count")
    public BaseResponse findEventCount(@PathVariable("videoId") int videoId, @PathVariable("eventType") EventType eventType, @RequestBody @Valid TimeRangeDto timeRangeDto){
        CountViewInfo countViewInfo = countViewApplicationService.findEventCount((long)videoId, eventType, DateUtil.toDate(timeRangeDto.getStartTime()), DateUtil.toDate(timeRangeDto.getEndTime()));
        return new CommonResponse<ViewCountDto>(CountViewInfoAssembler.toDto(countViewInfo));
    }

    @PostMapping(path = "/{videoId}/event/{eventType}/current-count")
    public BaseResponse findEventCurrentCount(@PathVariable("videoId") int videoId, @PathVariable("eventType") EventType eventType){
        CountViewInfo countViewInfo = countViewApplicationService.findEventCurrentCount((long)videoId, eventType);
        return new CommonResponse<ViewCountDto>(CountViewInfoAssembler.toDto(countViewInfo));
    }
}
