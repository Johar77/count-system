package com.johar.aggregatorservice.domain.countview.repository.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SpaceInfoPo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-09-07 00:24
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_space_info")
@Data
public class SpaceInfoPo {
    @Id
    @GeneratedValue
    private long id;

    private String name;
}
