package cn.sdu.icat.stirm.model.PO;

import lombok.Data;

/**
 * 处理过的地图名称
 *
 * @author icatzfd
 * Created on 2020/6/30 22:21.
 */
@Data
public class ProMapNamePO {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 区划时间
     */
    private Integer contourYear;

    /**
     * 区划名
     */
    private String contourName;

    /**
     * 对应的地图名
     */
    private String proMapName;
}
