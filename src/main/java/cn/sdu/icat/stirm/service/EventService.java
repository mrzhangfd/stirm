package cn.sdu.icat.stirm.service;

import cn.sdu.icat.stirm.model.Event;
import java.util.List;
/**
 * 事件接口
 *
 * @author icatzfd
 * Created on 2021/2/7 21:02.
 */
public interface EventService {
    /**
     * 获取历史上的今天事件集合
     * @param month 1
     * @param day
     * @return
     */
    List<Event> getTodayEvent(int month,int day);
}
