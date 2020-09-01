package com.johar.countingservice.application.service;

import com.johar.countingservice.domain.countview.entity.CountViewInfo;
import com.johar.countingservice.domain.countview.service.CountViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: CountViewApplicationService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 08:35
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class CountViewApplicationService {

    @Autowired
    private CountViewService countViewService;

    public void addCountViewEvent(@NotNull CountViewInfo countViewInfo){
        countViewService.addCountViewEvent(countViewInfo);
    }
}
