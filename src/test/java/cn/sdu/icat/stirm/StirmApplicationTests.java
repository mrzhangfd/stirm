package cn.sdu.icat.stirm;

import cn.sdu.icat.stirm.dal.dao.EventRepository;
import cn.sdu.icat.stirm.dal.dao.HbaseDao;
import cn.sdu.icat.stirm.model.Event;
import org.apache.hadoop.hbase.client.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StirmApplicationTests {

    @Autowired
    HbaseDao hbaseDao;

    @Autowired
    EventRepository eventRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testHbaseDao() {
        String tableName = "Object";
        String keyBegin = "毛泽东";
        //List<Result> list = hbaseDao.findAll(tableName);
        List<Result> list = hbaseDao.getDataWithSameBegining(tableName,keyBegin);
        System.out.println(list.size());
        System.out.println("========================================");
    }

    @Test
    public void testEventRepository(){
        String id = "bf09ffd4-d216-42e5-88b5-7a6e77d432c3";
        Event event = eventRepository.queryEventByEventId(id);
        System.out.println("query " + event);
        List<Event> events = eventRepository.queryEventsByTs("1999-09-09");
        for (Event tmp:events){
            System.out.println("tsQuery: "+tmp);
        }
    }

}
