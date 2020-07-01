package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * 轮廓上的点
 * @author icatzfd
 * Created on 2020/6/11 11:30.
 */
@Data
public class ContourPoint {

    /**
     * 点的自增id
     */
    private Integer pointId;

    /**
     * 轮廓时间
     */
    private Integer contourYear;

    /**
     * 轮廓名
     */
    private String contourName;

    /**
     * 点的横坐标
     */
    private Integer contourPointX;

    /**
     * 点的纵坐标
     */
    private Integer contourPointY;

}
