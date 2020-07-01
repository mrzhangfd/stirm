package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.ContourInfo;
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
public interface ContourInfoMapper {

    /**
     * 根据轮廓时间查询轮廓
     *
     * @param contourYear 轮廓时间
     * @return 轮廓列表
     */
    List<ContourInfo> selectByYear(@Param("contourYear") Integer contourYear);

    /**
     * 根据轮廓时间和轮廓名查询轮廓
     *
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名
     * @return 轮廓列表
     */
    List<ContourInfo> selectByYearAndName(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName);

    /**
     * 根据年代和名称 查询轮廓
     *
     * @param contourYear 年代
     * @param contourName 名称
     * @return 轮廓
     */
    ContourInfo selectOneByYearAndName(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName);

    /**
     * 查询所有轮廓信息
     *
     * @return 轮廓信息列表
     */
    List<ContourInfo> selectAll();

    /**
     * 查询所有轮廓的年份（去重）
     * @return 去重的轮廓年份列表
     */
    List<Integer> selectContourYear();

}
