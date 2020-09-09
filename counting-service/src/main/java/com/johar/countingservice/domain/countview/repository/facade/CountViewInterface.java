package com.johar.countingservice.domain.countview.repository.facade;

import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.entity.EventType;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: CountViewInterface
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-09 23:27
 * @Since: 1.0.0
 */
public interface CountViewInterface {

    CountViewInfo findCountViewInfo(Long videoId, EventType eventType, @NotNull Date startTime, @NotNull Date endTime);
}
