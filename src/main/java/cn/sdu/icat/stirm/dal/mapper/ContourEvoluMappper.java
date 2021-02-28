package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.ContourEvolu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 区划演进Mapper
 *
 * @author icatzfd
 * Created on 2021/2/3 16:48.
 */
@Mapper
@Component
public interface ContourEvoluMappper {

    /**
     *
     * @param mapYear
     * @param contourName
     * @return
     */
    ContourEvolu selectOne(@Param("mapYear") Integer mapYear, @Param("contourName") String contourName);

}
