package cn.sdu.icat.stirm;

import cn.sdu.icat.stirm.service.ContourService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyMotherlandApplicationTests {

    @Autowired
    private ContourService contourService;

    @Before
    public void setUp() {
        System.out.println("清空user表");
    }

    @Test
    public void test()  {
        System.out.println(contourService.getContourInfos());
    }

}
