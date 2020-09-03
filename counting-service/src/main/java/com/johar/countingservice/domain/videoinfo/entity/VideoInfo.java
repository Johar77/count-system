package com.johar.countingservice.domain.videoinfo.entity;

import lombok.*;

/**
 * @ClassName: VideoInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 14:57
 * @Since: 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoInfo {
    private int id;
    private String name;
    private SpaceInfo spaceInfo;
}
