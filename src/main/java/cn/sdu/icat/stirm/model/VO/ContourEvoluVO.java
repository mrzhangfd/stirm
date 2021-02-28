package cn.sdu.icat.stirm.model.VO;

import lombok.Data;

import java.util.List;

/**
 * 区划演进VO 暂时不用
 *
 * @author icatzfd
 * Created on 2021/2/3 16:29.
 */
@Data
public class ContourEvoluVO {
    /**
     * 区划时间
     */
    private Integer contourYear;

    /**
     * 区划名
     */
    private String contourName;


    /**
     * 区划面积
     */
    private Float contourArea;

    /**
     * 区划周长
     */
    private Float contourPerimeter;


    /**
     * 前序时间
     */
    private Integer preContourYear;

    /**
     * 前序区划
     */
    private List<String> preContour;

    /**
     * 后序时间
     */
    private Integer nextContourYear;

    /**
     * 后序区划
     */
    private List<String> nextContour;
}
