package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.ContourPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 轮廓点集Mapper
 *
 * @author icatzfd
 * Created on 2020/6/12 9:00.
 */
@Mapper
@Component
public interface ContourPointMapper {


    /**
     * 根据轮廓时间、名字查询轮廓点的列表
     *
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名字
     * @return 轮廓点的列表
     */
    List<ContourPoint> selectByYearAndName(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName);
}
