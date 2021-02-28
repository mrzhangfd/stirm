package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.service.EventService;
import cn.sdu.icat.stirm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author icatzfd
 * Created on 2019/11/18 10:16.
 */
@RestController
@RequestMapping("/event")
public class EventController {

    private MongoTemplate mongoTemplate;

    @Autowired
    private EventService eventService;

//    @RequestMapping("/gettoday")
//    @ResponseBody
//    public List<Event> getEvents(){
//
//        return null;
//    }

    //史上今日
    @RequestMapping("/gettoday")
    public @ResponseBody
    ResponseResult<List<Event>> getTodayEvents(
            @RequestParam("month") Integer month, @RequestParam("day") Integer day){

        return ResponseResult.success("success", eventService.getTodayEvent(month,day));
    }
}
