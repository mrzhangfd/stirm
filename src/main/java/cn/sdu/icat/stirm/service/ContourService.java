package cn.sdu.icat.stirm.service;

import cn.sdu.icat.stirm.model.ContourInfo;
import cn.sdu.icat.stirm.model.ContourPoint;
import cn.sdu.icat.stirm.model.VO.ContourInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author icatzfd
 * Created on 2019/11/2 15:46.
 */
public interface ContourService {


    /**
     * @return
     */
    List<ContourInfo> getContourInfos();


    /**
     * 获取区划信息组
     *
     * @return 年代和区划列表的对应关系
     */
    Map<Integer, List<String>> getContourInfoGroup();

    /**
     * 获取区划信息
     *
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名
     * @return 轮廓信息
     */
    List<ContourInfo> getContourInfo(Integer contourYear, String contourName);

    /**
     * 获取一个区划信息
     *
     * @param contourYear 轮廓时间
     * @param contourName 轮廓名
     * @return 轮廓信息
     */
    ContourInfo getOneContour(Integer contourYear, String contourName);

    /**
     * 获取区划点的列表
     *
     * @param contourYear 区划时间
     * @param contourName 区划名
     * @return
     */
    List<ContourPoint> getContourPoints(Integer contourYear, String contourName);

    /**
     * 获取区划时间列表
     *
     * @return 区划时间列表
     */
    List<Integer> getContourYear();

    /**
     * 获取区划所有信息
     *
     * @param contourYear 区划时间
     * @param contourName 区划名
     * @return 某一条的区划信息列表
     */
    List<ContourInfoVO> getOneContourAllInfo(Integer contourYear, String contourName);

    /**
     * 获取所有区划的所有信息
     *
     * @param contourYear  区划时间
     * @param contourNames 区划列表
     * @return 某一条的区划信息列表
     */
    List<ContourInfoVO> getContoursAllInfo(Integer contourYear, List<String> contourNames);

    /**
     * 处理图片
     *
     * @param contourYear 区划时间
     * @param contourName 区划名字
     * @return 图片路径
     */
    String processMapContour(Integer contourYear, String contourName);
}
