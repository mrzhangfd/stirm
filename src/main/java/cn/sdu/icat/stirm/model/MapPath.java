package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * 地图资源路径
 *
 * @author icatzfd
 * Created on 2020/6/16 19:40.
 */
@Data
public class MapPath {

    /**
     * 轮廓时间
     */
    private Integer mapYear;

    /**
     * 地图路径
     */
    private String mapPath;

}
