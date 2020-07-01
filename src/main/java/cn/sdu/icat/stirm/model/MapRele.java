package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * 地图之间的关联
 *
 * @author icatzfd
 * Created on 2020/6/18 10:07.
 */
@Data
public class MapRele {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 地图时间
     */
    private Integer mapYear;

    /**
     * 区划名
     */
    private String contourName;

    /**
     * 前序区划时间
     */
    private Integer preContourYear;

    /**
     * 前序区划
     */
    private String preContour;

    /**
     * 后序区划时间
     */
    private Integer nextContourYear;

    /**
     * 后序区划
     */
    private String nextContour;
}
