package com.johar.countingservice.interfaces.facade;

import com.johar.commonlib.api.BaseResponse;
import com.johar.commonlib.api.CommonResponse;
import com.johar.countingservice.domain.countview.entity.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/{videoId}/view-event")
    public BaseResponse countViewEvent(@PathVariable("videoId") int videoId){
        log.info("view event: {}-{}", EventType.VIEW, videoId);
        return BaseResponse.Ok();
    }

    @PostMapping("/{videoId}/{eventType}/event")
    public BaseResponse countViewEvent(@PathVariable("videoId") int videoId, @PathVariable("eventType") EventType eventType){
        log.info("view event: {}-{}", eventType, videoId);
        return BaseResponse.Ok();
    }
}
