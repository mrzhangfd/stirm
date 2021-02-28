package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.dao.EventRepository;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 事件接口实现类
 *
 * @author icatzfd
 * Created on 2021/2/7 21:11.
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> getTodayEvent(int month, int day) {
        String monthStr = String.format("%0" + 2 + "d", month);
        String dayStr = String.format("%0" + 2 + "d", day);
        String str = monthStr + "-" + dayStr;
        String year = "";
        List<Event> res = new ArrayList<>();
        for (int i = 1; i < 2050; i++) {
            year = String.format("%0" + 4 + "d", i);
            List<Event> events = eventRepository.queryEventsByTs(year + "-" + str);
            for(Event event:events){
                if(!" ".equals(event.getSite())){
                    res.add(event);
                }
            }

        }
        Collections.reverse(res);
        return res;
    }
}
