package cn.sdu.icat.stirm;

import cn.sdu.icat.stirm.dal.dao.ArticleRepository;
import cn.sdu.icat.stirm.dal.dao.AuthorRepository;
import cn.sdu.icat.stirm.dal.dao.EventRepository;
import cn.sdu.icat.stirm.dal.dao.HbaseDao;
import cn.sdu.icat.stirm.model.Article;
import cn.sdu.icat.stirm.model.Author;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.service.ObjectService;
import cn.sdu.icat.stirm.util.HbaseModelUtil;
import org.apache.hadoop.hbase.client.Result;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StirmApplicationTests {

    @Autowired
    HbaseDao hbaseDao;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ElasticsearchTemplate template;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ObjectService objectService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testHbaseDao() {
        String tableName = "Object";
        String keyBegin = "毛泽东";
        List<Result> list = hbaseDao.findAll(tableName);
        //List<Result> list = hbaseDao.getDataWithSameBegining(tableName, keyBegin);
        System.out.println(list.size());
        System.out.println("========================================");
    }

    @Test
    public void testEventRepository() {
        String id = "bf09ffd4-d216-42e5-88b5-7a6e77d432c3";
        Event event = eventRepository.queryEventByEventId(id);
        System.out.println("query " + event);
        List<Event> events = eventRepository.queryEventsByTs("1999-09-09");
        for (Event tmp : events) {
            System.out.println("tsQuery: " + tmp);
        }
    }

    @Test
    public void testEventRepository1() {
        System.out.println("++++++++++++++++++++");
        String str="09-09";
        String year="";
        List<Event> res=new ArrayList<>();
        for(int i=1;i<2050;i++){
             year=String.format("%0"+4+"d",i);
            List<Event> events=eventRepository.queryEventsByTs(year+"-"+str);
            for(Event event:events){

                if(event.getSite()!="未知"){
                    System.out.println(event.getSite());
                    res.add(event);
                }
            }
        }
        System.out.println(res.size());

    }

    @Test
    public void testFindHBaseByEventId(){
        String eventId="9b4cb5af-7daa-403a-a09d-b1241235b3b4";
        String name="顾毓琇";

    }

    @Test
    public void createIndex() {
        template.createIndex(Article.class);
    }



    @Test
    public void addDocument() {
        //往es中添加文档
        /*Article article = new Article();
        article.setId("222");
        article.setTitle("标题2");
        article.setContent("内容2");*/
        Author author=new Author();
        author.setId("2022");
        author.setName("zzffdd");
        author.setBirthday("95-12-13");
        author.setNum(123);
        authorRepository.save(author);
    }

    @Test
    public void deleteDoc() {
        //根据id ，删除es中文档
        articleRepository.delete("111");
    }

    @Test
    public void updateDoc() {
        //修改文档，将同一个id 的文档再写一遍。即可。
        Article article = new Article();
        article.setId("111");
        article.setTitle("修改标题1");
        article.setContent("修改内容1");
        articleRepository.save(article);
    }

    @Test
    public void findDoc() {
        //根据id 查找

        Article article = articleRepository.queryArticleByNum(null);

        System.out.println(article.getContent());
    }

    @Test
    public void findPage() {
        //分页
        //当前页 每页条数
        Pageable pageable = new PageRequest(0, 15);
    }

    @Test
    public void findByNativeSearchQuery() {
        //使用原生的查询 相当于使用java的es客户端查询。上面的使用spring.data.elasticsearch 查询
        //es客户端查询：使用template 查询。用起来不方便，需要手动拼接json数据。所以实际开发很少用。
        //spring data Elasticsearch 使用 封装好的框架。
        //创建查询对象
        NativeSearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.queryStringQuery("搜索内容").defaultField("content")).
                withPageable(new PageRequest(0, 15)).
                build();
        //自行查询
        List<Article> articles = template.queryForList(query, Article.class);
        articles.forEach(a -> System.out.println(a));
    }


    @Test
    public void findTimeLineByName() {
        //使用人名，查询对象的时间线
       String name="苏轼";
       System.out.println(objectService.getEntitiesByPrefix(name));

    }


}
