package cn.sdu.icat.stirm.dal.mapper;


import cn.sdu.icat.stirm.model.MapRele;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * 地图关联Mapper
 *
 * @author icatzfd
 * Created on 2020/6/18 10:32.
 */
@Mapper
@Component
public interface MapReleMapper {

    /**
     * 查询一条数据
     *
     * @param mapYear     地图时间
     * @param contourName 区划名
     * @return 该条地图关联
     */
    MapRele selectOne(@Param("mapYear") Integer mapYear, @Param("contourName") String contourName);
}
