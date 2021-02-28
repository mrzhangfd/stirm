package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * 区划演进
 *
 * @author icatzfd
 * Created on 2021/2/3 16:32.
 */
@Data
public class ContourEvolu {
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
     * 前因
     */
    private String preEvolution;

    /**
     * 后序区划时间
     */
    private Integer nextContourYear;

    /**
     * 后序区划
     */
    private String nextContour;

    /**
     * 后果
     */
    private String nextEvolution;
}
