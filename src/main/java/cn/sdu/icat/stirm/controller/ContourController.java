package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.checker.ContourChecker;
import cn.sdu.icat.stirm.model.Contour;
import cn.sdu.icat.stirm.service.ContourService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮廓控制器类
 *
 * @author icatzfd
 * Created on 2019/11/7 14:38.
 */
@RestController
public class ContourController {

    @Resource
    private ContourService contourService;

    @GetMapping("/listcontourinfo")
    public @ResponseBody
    List<Contour> listContourInfo() {
        return contourService.getContourInfos();
    }

    @GetMapping("/getcontourinfo")
    public @ResponseBody
    List<Contour> getContourInfo(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") String contourName) throws Exception {
        //1、参数校验
        ContourChecker.check4GetContourInfo(contourYear, contourName);
        //2、获取轮廓信息
        return contourService.getContourInfo(contourYear,contourName);
    }

}
