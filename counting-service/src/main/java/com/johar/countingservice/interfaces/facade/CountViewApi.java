package com.johar.countingservice.interfaces.facade;

import com.johar.commonlib.api.BaseResponse;
import com.johar.countingservice.application.service.CountViewApplicationService;
import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;
import com.johar.countingservice.interfaces.assembler.CountViewInfoAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: CountViewApi
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 13:17
 * @Since: 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("/view/count")
public class CountViewApi {
    private final int DEFAULT_INCREMENT = 1;

    @Autowired
    private CountViewApplicationService countViewApplicationService;

    @PostMapping("/{videoId}/view-event")
    public BaseResponse countViewEvent(@PathVariable("videoId") int videoId){
        log.info("view event: {}-{}", EventType.VIEW, videoId);
        CountViewInfo dto = CountViewInfoAssembler.toDo(new Date(), videoId, EventType.VIEW, DEFAULT_INCREMENT);
        countViewApplicationService.addCountViewEvent(dto);
        return BaseResponse.Ok();
    }

    @PostMapping("/{videoId}/{eventType}/event")
    public BaseResponse countViewEvent(@PathVariable("videoId") int videoId, @PathVariable("eventType") EventType eventType){
        log.info("view event: {}-{}", eventType, videoId);
        return BaseResponse.Ok();
    }
}
