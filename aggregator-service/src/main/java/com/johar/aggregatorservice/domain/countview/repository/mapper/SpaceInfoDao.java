package com.johar.aggregatorservice.domain.countview.repository.mapper;

import com.johar.aggregatorservice.domain.countview.repository.po.SpaceInfoPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SpaceInfoDao
 * @Description: 必须要创建Repository，才会自动创建表
 * @Author: Johar
 * @Date: 2020-09-07 23:27
 * @Since: 1.0.0
 */
@Repository
public interface SpaceInfoDao extends JpaRepository<SpaceInfoPo, Long> {
}
