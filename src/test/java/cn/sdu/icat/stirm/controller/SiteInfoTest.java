package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.dal.mapper.SiteInfoMapper;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.model.RealEntity;
import cn.sdu.icat.stirm.model.Site;
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
public class SiteInfoTest {

    @Autowired
    ObjectService objectService;

    @Autowired
    SiteInfoMapper siteInfoMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void SiteTest() throws Exception {
        String name="苏轼";
        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);
        List<RealEntity> realEntities = objectService.getEntitiesByPrefix(name);
        String objectId = realEntities.get(0).getObjectId();

        RealEntity entityByIdFromEs = objectService.findEntityByIdFromEs(objectId);
        List<Event> events=entityByIdFromEs.getEvents();
        events.remove(events.size()-1);
        int year = Integer.parseInt(entityByIdFromEs.getEvents().get(0).getTs().substring(0, 4));
        List<Site> sites = new ArrayList<>();
        for (Event event : entityByIdFromEs.getEvents()) {
            sites.add(siteInfoMapper.selectOne(year, event.getSite()));
        }

        List<Point> points = new ArrayList<>();
        for (Site site : sites) {
            String tmp = site.getSiteCentre();
            String first = tmp.split(",")[0];
            String x = first.substring(1);
            String second = tmp.split(",")[1];
            String y = second.substring(0, second.length() - 1);
            Point point=new Point(Double.parseDouble(x),Double.parseDouble(y));
            points.add(point);
        }

        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + year + ".jpg");

        //复制到temp
        Mat temp = new Mat();
        src.copyTo(temp);
        Map<String, Point> map = new HashMap<>();

        for (int i = 0; i < points.size() - 1; i++) {
            Imgproc.arrowedLine(temp, points.get(i), points.get(i + 1), new Scalar(255, 255, 255), 2, 8, 0, 0.1);
            // Imgproc.circle(temp, points.get(i), 2, new Scalar(255, 255, 255), 10, 8, 0);
            // Imgproc.putText(temp, "test", points.get(i), 1, 2, new Scalar(255, 255, 255), 10);

        }
        //Imgproc.arrowedLine(temp, new Point(2517.0, 994.0), new Point(2711, 906), new Scalar(255, 255, 255));
        System.out.println(Imgcodecs.imwrite(FilePath.DESKTOP.getPath() + "testyear.jpg", temp));



    }





}