package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.dal.mapper.ContourPointMapper;
import cn.sdu.icat.stirm.model.ContourPoint;
import cn.sdu.icat.stirm.service.ContourService;
import cn.sdu.icat.stirm.util.FilePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author icatzfd
 * Created on 2020/6/12 9:37.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContourPointTrackTest {

    @Autowired
    ContourPointMapper contourPointMapper;

    @Autowired
    ContourService contourService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDrawContour() {
        //加载opencv的dll文件
        String path = "D:\\OpenCV\\opencv_java340\\";
        System.load(path + "opencv_java340-x64.dll");
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = new ArrayList<>();
        List<Point> points3 = new ArrayList<>();
        List<ContourPoint> contourPoints1 = contourPointMapper.selectByYearAndName(237, "曹魏");
        List<ContourPoint> contourPoints2 = contourPointMapper.selectByYearAndName(237, "孙吴");
        List<ContourPoint> contourPoints3 = contourPointMapper.selectByYearAndName(237, "蜀汉");
        //System.out.println(contourPoints.size());

        for (ContourPoint contourPoint : contourPoints1) {
            Point point = new Point();
            point.x = contourPoint.getContourPointX();
            point.y = contourPoint.getContourPointY();
            points1.add(point);
        }
        MatOfPoint matOfPoint1 = new MatOfPoint();
        matOfPoint1.fromList(points1);

        for (ContourPoint contourPoint : contourPoints2) {
            Point point = new Point();
            point.x = contourPoint.getContourPointX();
            point.y = contourPoint.getContourPointY();
            points2.add(point);
        }
        MatOfPoint matOfPoint2 = new MatOfPoint();
        matOfPoint2.fromList(points2);

        for (ContourPoint contourPoint : contourPoints3) {
            Point point = new Point();
            point.x = contourPoint.getContourPointX();
            point.y = contourPoint.getContourPointY();
            points3.add(point);
        }
        MatOfPoint matOfPoint3 = new MatOfPoint();
        matOfPoint3.fromList(points3);

        List<MatOfPoint> matOfPoints = new ArrayList<>();
        matOfPoints.add(matOfPoint1);
        matOfPoints.add(matOfPoint2);
        matOfPoints.add(matOfPoint3);

        //读入图片
        Mat src = Imgcodecs.imread("C:\\\\Users\\\\icatzfd\\\\Desktop\\\\237.jpg");

        //灰度化
        //Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);

        //二值化
        //Imgproc.threshold(src, src, 120, 255, Imgproc.THRESH_BINARY);

        //Mat temp = new Mat(src.size(), CvType.CV_8S, new Scalar(0, 255, 0));
        int index = -1;
        //temp.copyTo();
        for (int i = 0; i < matOfPoints.size(); i++) {
            Mat temp = new Mat();
            src.copyTo(temp);
            /**
             *
             */
            Imgproc.drawContours(temp, matOfPoints, i, new Scalar(255, 255, 255), 5);
            Imgcodecs.imwrite("C:\\\\Users\\\\icatzfd\\\\Desktop\\\\testimage\\\\" + "test" + i + 3 + ".jpg", temp);
        }
//        Imgproc.drawContours(temp, matOfPoints, -1, new Scalar(0, 255, 0), 2);
//        Imgcodecs.imwrite("C:\\\\Users\\\\icatzfd\\\\Desktop\\\\" + "test226.jpg", temp);


    }

    @Test
    public void testGet() {
        List<Point> points = new ArrayList<>();
        List<ContourPoint> contourPoints = contourPointMapper.selectByYearAndName(18, "羊");
        System.out.println(contourPoints.size());
    }

    @Test
    public void testDraw1() throws UnsupportedEncodingException {
        //加载opencv的dll文件
        String path = "D:\\OpenCV\\opencv_java340\\";
        System.load(path + "opencv_java340-x64.dll");
        //读入图片
        Mat src = Imgcodecs.imread("C:\\\\Users\\\\icatzfd\\\\Desktop\\\\test.jpg");

        //灰度化
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);

        //二值化
        Imgproc.threshold(src, src, 120, 255, Imgproc.THRESH_BINARY);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        // Mat temp = new Mat(src.size(), CvType.CV_8U, new Scalar(0, 255, 0));
        Imgproc.findContours(src, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);
        System.out.println(contours.size());
        Imgproc.drawContours(src, contours, 0, new Scalar(0, 0, 255), 4);
        String name = "蜀山";

        int year = 6666;
        StringBuilder sb = new StringBuilder();
        sb.append("C:\\\\\\\\Users\\\\\\\\icatzfd\\\\\\\\Desktop\\\\\\\\");
        sb.append(name).append(year);
        //File.separator;
        sb.append("33333.jpg");
        String ss = "C:" + File.separator + "Users" + File.separator + "icatzfd" + File.separator + "Desktop" + File.separator + name + year + ".jpg";
        ss = URLDecoder.decode(ss, "UTF-8");
        System.out.println(ss);
        //Imgcodecs.imwrite(sb.toString(), src);
        System.out.println(Imgcodecs.imwrite(ss, src));
    }

    @Test
    public void testPro() throws UnsupportedEncodingException {
        System.out.println(contourService.processMapContour(229, "蜀汉"));
        //加载opencv的dll文件
        String path = "D:\\OpenCV\\opencv_java340\\";
        System.load(path + "opencv_java340-x64.dll");
        //读入图片
        Mat src = Imgcodecs.imread("C:\\\\Users\\\\icatzfd\\\\Desktop\\\\test.jpg");

        //灰度化
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);

        //二值化
        Imgproc.threshold(src, src, 120, 255, Imgproc.THRESH_BINARY);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        // Mat temp = new Mat(src.size(), CvType.CV_8U, new Scalar(0, 255, 0));
        Imgproc.findContours(src, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_NONE);
        System.out.println(contours.size());
        Imgproc.drawContours(src, contours, 0, new Scalar(0, 0, 255), 4);
        String name = "蜀山";

        int year = 6666;
        StringBuilder sb = new StringBuilder();
        sb.append("C:\\\\\\\\Users\\\\\\\\icatzfd\\\\\\\\Desktop\\\\\\\\");
        sb.append(name).append(year);
        //File.separator;
        sb.append("33333.jpg");
        String ss = "C:" + File.separator + "Users" + File.separator + "icatzfd" + File.separator + "Desktop" + File.separator + name + year + ".jpg";
        ss = URLDecoder.decode(ss, "UTF-8");
        System.out.println(ss);
        //Imgcodecs.imwrite(sb.toString(), src);
        System.out.println(Imgcodecs.imwrite(ss, src));
    }

    @Test
    public void testPatch() {
        String contourNameStr = "孙吴+蜀汉+曹魏";
        int contourYear = 229;
        contourService.processMapContourPatch(contourYear, contourNameStr);
    }

    @Test
    public void testArrow() {
        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);

        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + 280 + ".jpg");

        //复制到temp
        Mat temp = new Mat();
        src.copyTo(temp);
        Map<String,Point> map=new HashMap<>();
        Point pt1=new Point(2573.5, 1115.5);
        Point pt2=new Point(2651.5, 1076.5);
        Point pt3=new Point(2683.5, 875.0);
        List<Point> points=new ArrayList<>(Arrays.asList(pt1,pt2,pt3));
        for(int i=0;i<points.size()-1;i++){
            Imgproc.arrowedLine(temp, points.get(i), points.get(i+1), new Scalar(255, 255, 255),5,8,0,0.1);
        }
        //Imgproc.arrowedLine(temp, new Point(2517.0, 994.0), new Point(2711, 906), new Scalar(255, 255, 255));
        System.out.println(Imgcodecs.imwrite(FilePath.DESKTOP.getPath() + "testArrow1.jpg", temp));

    }

}



