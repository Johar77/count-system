package com.johar.countingservice.interfaces.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * @ClassName: TimeRangeDto
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 23:17
 * @Since: 1.0.0
 */
@Data
@Valid
public class TimeRangeDto {
    @NotNull
    @Past
    private String startTime;

    @NotNull
    private String endTime;
}
