package com.johar.countingservice.interfaces.dto;

import com.johar.countingservice.domain.countview.entity.EventType;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: ViewCountDto
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 23:33
 * @Since: 1.0.0
 */
@Data
@Builder
public class ViewCountDto {
    private int videoId;
    private String videoName;
    private String spaceName;
    private EventType eventType;
    private long count;
}
