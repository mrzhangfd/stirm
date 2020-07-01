package cn.sdu.icat.stirm.dal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 地图路径Mapper
 *
 * @author icatzfd
 * Created on 2020/6/16 21:10.
 */
@Mapper
@Component
public interface MapPathMapper {

    /**
     * 插入地图路径
     * @param mapYear 地图时间
     * @param mapPath 地图路径
     */
    void insert(@Param("mapYear") Integer mapYear, @Param("mapPath") String mapPath);
}
