package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.PO.ProMapNamePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author icatzfd
 * Created on 2020/6/30 22:24.
 * 处理过的图片名
 */
@Mapper
@Component
public interface ProMapFileNameMapper {
    /**
     * 查询处理过的图片名
     *
     * @param contourYear 区划时间
     * @param contourName 区划名
     * @return Po对象
     */
    ProMapNamePO selectOne(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName);

    /**
     * 插入处理过的图片名
     *
     * @param contourYear 区划时间
     * @param contourName 区划名
     * @param proMapName  图片名
     */
    void insert(@Param("contourYear") Integer contourYear, @Param("contourName") String contourName, @Param("proMapName") String proMapName);
}
