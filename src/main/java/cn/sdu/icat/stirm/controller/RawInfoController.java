package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.service.RawinfoService;
import cn.sdu.icat.stirm.service.TimelineService;
import cn.sdu.icat.stirm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * 对象原始信息的控制器
 *
 * @author icatzfd
 * Created on 2020/6/11 22:19.
 */
@Controller
public class RawInfoController {

    @Autowired
    RawinfoService rawinfoService;

    @Autowired
    TimelineService timelineService;

    @PostMapping(value = "/newentity.do")
    public @ResponseBody
    String saveNewWithReal(String name, String description) {
        String objectId = name + CommonUtil.genRandomNum();
        String realName = name + " " + description;
        rawinfoService.insertRealName(realName, objectId);
//        timelineService.insetTimenode(objectId, new Timenode("0000-00-00", new DetailedInfo("", realName, "")));
        UUID uuid=UUID.randomUUID();
        String  eventId = uuid.toString();
//        String eventId = str.replace("-", "");
        timelineService.insertEvent(objectId,new Event(eventId,objectId,"2050-01-01","",realName,""));
        return "OK";
    }
}
