package com.johar.aggregatorservice.domain.countview.repository.po;

import lombok.Data;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, name = "name")
    private String name;
}
