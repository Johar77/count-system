package com.johar.countingservice.domain.countview.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: VideoInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 23:40
 * @Since: 1.0.0
 */
@Data
@Builder
public class VideoInfo {
    private long id;
    private String name;
    private SpaceInfo spaceInfo;
}
