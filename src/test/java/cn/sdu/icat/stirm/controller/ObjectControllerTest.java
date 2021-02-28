package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.service.ObjectService;
import cn.sdu.icat.stirm.util.FilePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


/**
 * @author icatzfd
 * Created on 2020/6/8 15:35.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectControllerTest {

    @Autowired
    ObjectService objectService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void ObjectTest() throws Exception {
       // List<RealEntity> entities = objectService.getEntitiesByPrefix("曹操");
        //System.out.println(entities);
        System.out.println(objectService.findEntityByIdFromEs("苏轼MQRIM839").getEvents().get(0).getTs().substring(0,4));

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
        Map<String, Point> map=new HashMap<>();
        Point pt1=new Point(2573.5, 1115.5);
        Point pt2=new Point(2651.5, 1076.5);
        Point pt3=new Point(2683.5, 875.0);
        List<Point> points=new ArrayList<>(Arrays.asList(pt1,pt2,pt3));
        for(int i=0;i<points.size()-1;i++){
            Imgproc.arrowedLine(temp, points.get(i), points.get(i+1), new Scalar(255, 255, 255));
           /* Imgproc.circle(temp,points.get(i),2,new Scalar(255, 255, 255),10,8,0);
            Imgproc.putText(temp,"test",points.get(i),1,2,new Scalar(255, 255, 255),10);*/
        }
        //Imgproc.arrowedLine(temp, new Point(2517.0, 994.0), new Point(2711, 906), new Scalar(255, 255, 255));
        System.out.println(Imgcodecs.imwrite(FilePath.TEST_IMAGE.getPath() + "testArrow2.jpg", temp));

    }



}