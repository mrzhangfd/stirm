package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.model.Event;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author icatzfd
 * Created on 2019/11/18 10:16.
 */
@RestController
@RequestMapping("/eventcontroller")
public class EventController {

    private MongoTemplate mongoTemplate;

    @RequestMapping("/getevetns")
    @ResponseBody
    public List<Event> getEvents(){

        return null;
    }
}
