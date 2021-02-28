package cn.sdu.icat.stirm.dal.mapper;

import cn.sdu.icat.stirm.model.ContourEvolu;
import cn.sdu.icat.stirm.model.Site;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 地点信息Mapper
 *
 * @author icatzfd
 * Created on 2021/2/27 22:05.
 */
@Mapper
@Component
public interface SiteInfoMapper {


    Site selectOne(@Param("siteYear") Integer siteYear, @Param("siteName") String siteName);

}
