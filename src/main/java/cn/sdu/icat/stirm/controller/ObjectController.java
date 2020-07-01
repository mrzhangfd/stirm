package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.checker.ContourChecker;
import cn.sdu.icat.stirm.model.RealEntity;
import cn.sdu.icat.stirm.service.ObjectService;
import cn.sdu.icat.stirm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信息对象
 *
 * @author icatzfd
 * Created on 2020/6/8 14:57.
 */
@Controller
@RestController
public class ObjectController {

    @Autowired
    ObjectService objectService;

    @GetMapping("/getobjectlist")
    public @ResponseBody
    ResponseResult<List<RealEntity>> getObjectList(@RequestParam("prefix") String prefix) throws Exception {
        //1、参数校验
        ContourChecker.check4GetObjectList(prefix);

        //2、获取对象信息
        List<RealEntity> realEntities = objectService.getEntitiesByPrefix(prefix);
        return ResponseResult.success("success", realEntities);
    }
}
