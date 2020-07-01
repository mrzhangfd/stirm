package cn.sdu.icat.stirm.service;

import cn.sdu.icat.stirm.model.DetailedInfo;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.model.Timenode;

import java.util.List;

/**
 * Created by J on  17-10-27.
 */

public interface TimelineService {

    List<Timenode> findTimelineByObjectId(String objectId);

    Event findEventByEventId(String eventId);

    DetailedInfo findDetailinfoByTimepoint(String objectId, String timePoint) throws Exception;

    void insetTimenode(String objectId,Timenode timenode);

    void insertEvent(String objectId, Event event);

    void delTimenodeByTimepoint(String objectId,String timePoint);

    void delEvent(Event event);

}
