package com.johar.countingservice.domain.countview.entity;

import lombok.*;

import java.util.Date;

/**
 * @ClassName: CountViewInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-01 08:48
 * @Since: 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountViewInfo {
    private VideoInfo videoInfo;
    private Date countTime;
    private EventType eventType;
    private long count;
}
