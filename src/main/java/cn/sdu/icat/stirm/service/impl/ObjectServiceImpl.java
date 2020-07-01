package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.dao.HbaseDao;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.model.HbaseModel;
import cn.sdu.icat.stirm.model.RealEntity;
import cn.sdu.icat.stirm.service.ObjectService;
import cn.sdu.icat.stirm.util.HbaseModelUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 信息对象业务实现类
 *
 * @author icatzfd
 * Created on 2020/6/8 15:13.
 */
@Service("objectService")
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    HbaseDao hbaseDao;

    @Override
    public List<RealEntity> getEntitiesByPrefix(String prefix) {
        List<Result> rets = hbaseDao.getDataWithSameBegining(HbaseModelUtil.BASIC_TABLE, prefix);
        Iterator<Result> iterator = rets.iterator();
        HashMap<String, RealEntity> entityHashMap = new HashMap<>();
        while (iterator.hasNext()) {
            Result ret = iterator.next();
            for (KeyValue kv : ret.list()) {
                HbaseModel hbaseModel = HbaseModelUtil.kvToHbaseModel(kv);
                if (entityHashMap.containsKey(hbaseModel.getRow())) {
                    RealEntity entity = entityHashMap.get(hbaseModel.getRow());
                    RealEntity realEntity = packageModel(entity, hbaseModel);
                    entityHashMap.remove(hbaseModel.getRow());
                    entityHashMap.put(hbaseModel.getRow(), realEntity);

                } else {
                    RealEntity entity = new RealEntity();
                    entity.setObjectId(hbaseModel.getRow());
                    RealEntity realEntity = packageModel(entity, hbaseModel);
                    entityHashMap.put(hbaseModel.getRow(), realEntity);
                }
            }
        }

        ArrayList<RealEntity> entities = new ArrayList<>();
        Iterator<String> iterator1 = entityHashMap.keySet().iterator();
        while (iterator1.hasNext()) {
            String s = iterator1.next();
            RealEntity entity = entityHashMap.get(s);
            entities.add(entity);
        }
        return entities;
    }

    public RealEntity packageModel(RealEntity realEntity, HbaseModel hbaseModel) {
        System.out.println(hbaseModel.getFamilyName());
        ArrayList<Event> eventList = realEntity.getEvents();
        if (HbaseModelUtil.BASIC_EVENT.equals(hbaseModel.getFamilyName())) {
            Event event = new Event();
            String eventId = hbaseModel.getValue();
            Result ret = hbaseDao.getDataFromRowkey(HbaseModelUtil.EVENTS_TABLE, eventId);
            for (KeyValue kv : ret.list()) {
                HbaseModel model = HbaseModelUtil.kvToHbaseModel(kv);
                String value = model.getValue();
                switch (model.getQualifier()) {
                    case "ts":
                        event.setTs(value);
                        break;
                    case "site":
                        event.setSite(value);
                        break;
                    case "details":
                        event.setDetails(value);
                        break;
                    default:
                        event.setAffect(value);
                }
                event.setEventId(eventId);
            }
            eventList.add(event);
        }
        if (HbaseModelUtil.BASIC_RAW.equals(hbaseModel.getFamilyName())) {
            if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN2)) {
                realEntity.setRawInfo(hbaseModel.getValue());
            } else if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN1)) {
                realEntity.setRealName(hbaseModel.getValue());
            } else {
                Map<String, String> params = realEntity.getParams();
                params.put(hbaseModel.getQualifier(), hbaseModel.getValue());
                realEntity.setParams(params);
            }

        }
        return realEntity;
    }
}
