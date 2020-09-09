package com.johar.countingservice.domain.countview.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: SpaceInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-08 23:44
 * @Since: 1.0.0
 */
@Data
@Builder
public class SpaceInfo {
    private long id;
    private String name;
}
