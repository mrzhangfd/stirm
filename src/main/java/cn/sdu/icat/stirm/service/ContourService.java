package cn.sdu.icat.stirm.service;

import cn.sdu.icat.stirm.model.Contour;

import java.util.List;

/**
 * 用户类接口
 *
 * @author icatzfd
 * Created on 2019/11/2 15:46.
 */
public interface ContourService {


    /**
     * @return
     */
    List<Contour> getContourInfos();

    /**
     * 获取轮廓信息
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名
     * @return 轮廓信息
     */
    List<Contour> getContourInfo(Integer contourYear, String contourName);
}
