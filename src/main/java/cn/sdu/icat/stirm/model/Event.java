package cn.sdu.icat.stirm.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 事件类
 * 对应mongodb中的event集合（表）
 *
 * @author icatzfd
 * Created on 2019/11/18 14:27.
 */
@Data
@Document(collation = "event")
public class Event {

    private String data;

    private List<String> pName;

    private List<String> sName;

    private String eventDetails;

}
