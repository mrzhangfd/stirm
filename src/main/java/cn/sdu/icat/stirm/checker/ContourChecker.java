package cn.sdu.icat.stirm.checker;

import cn.sdu.icat.stirm.util.FilePath;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.List;

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

    public static void check4GetObjectList(String prefix) throws Exception {
        if (StringUtils.isEmpty(prefix)) {
            throw new Exception("前缀名为空，异常");
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);

        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + 280 + ".jpg");

        //复制到temp
        Mat temp = new Mat();
        src.copyTo(temp);
        Map<String, Point> map = new HashMap<>();
        Point pt1 = new Point(2573.5, 1115.5);
        Point pt2 = new Point(2651.5, 1076.5);
        Point pt3 = new Point(2683.5, 875.0);
        List<Point> points = new ArrayList<>(Arrays.asList(pt1, pt2, pt3));
        for(int j=0;j<10;j++){
            for (int i = 0; i < points.size() - 1; i++) {
                /**
                 * img: 需要绘制箭头的图像
                 *
                 *  pt1, pt2：绘制箭头线段的起点和终点坐标
                 *
                 *  color: 绘制箭头线段的颜色
                 *
                 * thickness: 箭头线段的线宽(线的粗细)
                 *
                 * line_type: 绘制线的类型参考定义LineTypes
                 *
                 * shitf: 坐标系中的小数位数
                 *
                 * tipLength: 箭头笔尖的长度(相对于线段长度的比例)，默认0.1，比例越大箭头越长
                 * ————————————————
                 */
                Imgproc.arrowedLine(temp, points.get(i), points.get(i + 1), new Scalar(255, 255, 255), 2, 8, 0, 0.1);
           /* Imgproc.circle(temp,points.get(i),2,new Scalar(255, 255, 255),10,8,0);
            Imgproc.putText(temp,"test",points.get(i),1,2,new Scalar(255, 255, 255),10);*/
                //Imgproc.drawMarker(temp, points.get(i + 1), new Scalar(12, 34, 56), 6, 20, 1, 4);

            }
        }

        /*BufferedImage bufferedImage=ImageUtil.Mat2BufImg(temp,".jpg");
        Graphics2D g=bufferedImage.createGraphics();
        g.drawImage(bufferedImage,0,0,bufferedImage.getWidth(),bufferedImage.getHeight(),null);
        //设置字体
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        g.setFont(font);

        //设置坐标
        g.drawString("你好",new Double(pt1.x).floatValue(),new Double(pt1.y).floatValue());
        g.dispose();
        temp= ImageUtil.BufImg2Mat(bufferedImage,BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);*/

        System.out.println(Imgcodecs.imwrite(FilePath.TEST_IMAGE.getPath() + "testArrow10.jpg", temp));
    }


}
