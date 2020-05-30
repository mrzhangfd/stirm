package cn.sdu.icat.stirm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应Mongdb中的event表
 *
 * @author icatzfd
 * Created on 2020/5/30 21:18.
 */

@Document(collection = "event")
@Data
public class Doc {
    @Id
    String _id;
    String date;
    List<String> pName = new ArrayList<>();
    List<String> sName = new ArrayList<>();
    String details;
    String event;

    public Doc(String _id, String date, List<String> pName, List<String> sName, String details) {
        this._id = _id;
        this.date = date;
        this.pName = pName;
        this.sName = sName;
        this.details = details;
    }

    public Doc() {
    }
}

