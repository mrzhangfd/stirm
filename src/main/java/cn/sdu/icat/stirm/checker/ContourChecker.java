package cn.sdu.icat.stirm.checker;

import org.springframework.util.StringUtils;

/**
 * 轮廓参数校验
 *
 * @author icatzfd
 * Created on 2019/11/29 19:49.
 */
public class ContourChecker {
    public static void check4GetContourInfo(Integer contourYear, String contourName) throws Exception {
        if (StringUtils.isEmpty(contourName) || null == contourYear) {
            throw new Exception("轮廓时间或轮廓名称参数错误。");
        }
    }

}
