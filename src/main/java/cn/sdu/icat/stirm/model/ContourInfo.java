package cn.sdu.icat.stirm.model;

import com.mysql.cj.jdbc.Blob;
import lombok.Data;

/**
 * 轮廓
 *
 * @author icatzfd
 * Created on 2019/11/7 9:28.
 */
@Data
public class ContourInfo {

    /**
     * 轮廓时间
     */
    private Integer contourYear;

    /**
     * 轮廓名
     */
    private String contourName;

    /**
     * 轮廓本身
     */
    private byte[] contourPoints;

    /**
     * 轮廓面积
     */
    private Float contourArea;

    /**
     * 轮廓周长
     */
    private Float contourPerimeter;

    /**
     * 轮廓中心
     */
    private String contourCentre;
}
