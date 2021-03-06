package cn.sdu.icat.stirm.dal.dao;

import cn.sdu.icat.stirm.model.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author icatzfd
 * Created on 2020/5/30 20:13.
 */
@Repository
public interface EventRepository extends ElasticsearchRepository<Event,String> {
    Event queryEventByEventId(String eventId);
    void deleteEventByEventId(String eventId);
    List<Event> queryEventsByTs(String ts);
}
