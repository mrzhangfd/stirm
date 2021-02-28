package cn.sdu.icat.stirm.util;

import java.io.File;

/**
 * 文件路径
 *
 * @author icatzfd
 * Created on 2020/6/12 9:39.
 */
public enum FilePath {

    /**
     * 加载opencv所需的dll文件路径
     */
    OPENCV_FILE_PATH("D:" + File.separator + "OpenCV" + File.separator + "opencv_java340" + File.separator+"opencv_java340-x64.dll"),

    /**
     * 原始地图路径
     */
    MAP_FILE_PATH("G:" + File.separator + "UsedMap" + File.separator),

    /**
     * 返回给前端的图片路径前缀
     */
    LOCALHOST_MAP_PATH("http://localhost:8080/image/"),

    /**
     * 处理好的图片路径
     */
    PRO_MAP_FILE_PATH("G:" + File.separator + "UsedProcessedMap" + File.separator),

    /**
     * 测试图片路径
     */
    TEST_IMAGE("G:"+File.separator+"testimage"+File.separator);

    private String path;

    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
