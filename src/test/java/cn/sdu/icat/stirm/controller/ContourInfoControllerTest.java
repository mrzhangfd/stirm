package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.dal.mapper.ContourInfoMapper;
import cn.sdu.icat.stirm.dal.mapper.ContourPointMapper;
import cn.sdu.icat.stirm.dal.mapper.MapPathMapper;
import cn.sdu.icat.stirm.dal.mapper.MapReleMapper;
import cn.sdu.icat.stirm.model.ContourInfo;
import cn.sdu.icat.stirm.service.ContourService;
import cn.sdu.icat.stirm.service.MapPathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author icatzfd
 * Created on 2020/6/10 10:50.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ContourInfoControllerTest {

    @Autowired
    ContourService contourService;

    @Autowired
    ContourInfoMapper contourInfoMapper;

    @Autowired
    MapPathService mapPathService;

    @Autowired
    MapReleMapper mapReleMapper;
    private Object List;
    private java.lang.Object Object;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testList() throws IOException, ClassNotFoundException, SQLException {

        List<ContourInfo> list=contourService.getContourInfos();
        ContourInfo contourInfo =contourService.getOneContour(18,"羊");

        System.out.println(contourInfo);
        //System.out.println(contourInfo.getContourPoints());
       // System.out.println(contour.getContourPoints());
//        ObjectInputStream in  = new ObjectInputStream(new ByteArrayInputStream(contour.getContourPoints()));
//        List<Object> lists = new ArrayList<Object>();
//        lists=(List<Object>in.readObject());
//        in.close();
//        System.out.println(list);
    }

    @Test
    public void testMap(){

        System.out.println(contourService.getOneContourAllInfo(591,"隋"));

    }
}