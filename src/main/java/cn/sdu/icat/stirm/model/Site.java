package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * 地点类
 *
 * @author icatzfd
 * Created on 2021/2/27 22:03.
 */
@Data
public class Site {

    private Integer siteYear;

    private String siteName;

    private String siteContour;

    private float siteSlope;

    private float siteLength;

    private String siteCentre;
}
