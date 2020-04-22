package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.Contour;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 轮廓mapper
 *
 * @author icatzfd
 * Created on 2019/11/7 9:28.
 */
@Mapper
@Component
public interface ContourMapper {

    /**
     * 根据轮廓时间查询轮廓
     *
     * @param contourYear 轮廓时间
     * @return 轮廓列表
     */
    List<Contour> selectByYear(@Param("contourYear") Integer contourYear);

    /**
     * 根据轮廓时间和轮廓名查询轮廓
     *
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名
     * @return 轮廓列表
     */
    List<Contour> selectByYearAndName(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName);
}
