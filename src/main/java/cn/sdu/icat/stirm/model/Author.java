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
@Document(indexName = "es-article", type = "author")
public class Author {
    @Id
    private String id;
    private String name;
    private String birthday;
    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Author() {

    }
}
