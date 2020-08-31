package cn.sdu.icat.stirm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author icatzfd
 * Created on 2020/8/31 14:49.
 */
@Data
//对应ES中的一条文档
@Document(indexName = "es-article", type = "article")
public class Article {
    @Id
    private String id;
    private String title;
    private String content;
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Article() {

    }
}
