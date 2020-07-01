package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.model.RealEntity;
import cn.sdu.icat.stirm.service.ObjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


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
    public void ObjectTest(){
        List<RealEntity> entities = objectService.getEntitiesByPrefix("曹操");
        System.out.println(entities);
    }

}