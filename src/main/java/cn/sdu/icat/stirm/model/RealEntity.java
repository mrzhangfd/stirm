package cn.sdu.icat.stirm.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 对应Hbase中的实体类
 *
 * @author J
 * Created on 2020/6/8 15:20.
 */
@Data
public class RealEntity {
    /**
     * 对象id
     */
    private String objectId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 原始信息
     */
    private String rawInfo;

    /**
     * 属性键值对
     */
    private Map<String, String> params = new HashMap<>();

    /**
     * 事件集合
     */
    private ArrayList<Event> events = new ArrayList<>();

    public RealEntity(String objectId) {
    }

    public RealEntity() {
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getRawInfo() {
        return rawInfo;
    }

    public void setRawInfo(String rawInfo) {
        this.rawInfo = rawInfo;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}
