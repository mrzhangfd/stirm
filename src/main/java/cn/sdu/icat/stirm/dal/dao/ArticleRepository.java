package cn.sdu.icat.stirm.dal.dao;

import cn.sdu.icat.stirm.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author icatzfd
 * Created on 2020/8/31 14:53.
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Article queryArticleByNum(String num);


    /**
     * 分页查询。因为默认只查询10条数据。
     * @param title
     * @param content
     * @param pageable
     * @return
     * 可以对内容分词，然后检索。分词之间是 and的关系，也就是 必须同时包括内容的每个分词
     * 百度搜索用的 ：每个分词之间应该是or的关系
     */
    List<Article> queryArticleByTitleOrContent (String title, String content, Pageable pageable);
}
