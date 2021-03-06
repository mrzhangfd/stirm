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

    MAP_FILE_PATH("G:" + File.separator + "UsedMap" + File.separator),

    /**
     * 处理好的图片路径
     */
    PRO_MAP_FILE_PATH("G:" + File.separator + "UsedProcessedMap" + File.separator);

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
