package cn.sdu.icat.stirm.controller;

import cn.sdu.icat.stirm.checker.ContourChecker;
import cn.sdu.icat.stirm.model.ContourInfo;
import cn.sdu.icat.stirm.model.VO.ContourInfoVO;
import cn.sdu.icat.stirm.service.ContourService;
import cn.sdu.icat.stirm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 轮廓控制器类
 *
 * @author icatzfd
 * Created on 2019/11/7 14:38.
 */
@RestController
public class ContourController {

    @Autowired
    private ContourService contourService;

    @GetMapping("/listcontourinfo")
    public @ResponseBody
    List<ContourInfo> listContourInfo() {
        return contourService.getContourInfos();
    }

    @GetMapping("/getcontourinfo")
    public @ResponseBody
    ResponseResult<List<ContourInfo>> getContourInfo(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") String contourName) throws Exception {
        //1、参数校验
        ContourChecker.check4GetContourInfo(contourYear, contourName);
        //2、获取轮廓信息
        return ResponseResult.success("success", contourService.getContourInfo(contourYear, contourName));
    }


    @GetMapping("/getcontourinfogroup")
    public @ResponseBody
    ResponseResult<Map<Integer, List<String>>> getContourInfoGroup() {

        //2、获取轮廓信息
        return ResponseResult.success("success", contourService.getContourInfoGroup());
    }

    @GetMapping("/getcontourallinfo")
    public @ResponseBody
    ResponseResult<List<ContourInfoVO>> getContourAllInfo(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") String contourName) throws Exception {
        //1、参数校验
        //ContourChecker.check4GetContourInfo(contourYear, contourName);

        //2、获取轮廓信息
        return ResponseResult.success("success", contourService.getOneContourAllInfo(contourYear, contourName));
    }

    @GetMapping("/getcontoursallinfo")
    public @ResponseBody
    ResponseResult<List<ContourInfoVO>> getContoursAllInfo(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") List<String> contourName) throws Exception {
        //1、参数校验
        //ContourChecker.check4GetContourInfo(contourYear, contourName);

        //2、获取轮廓信息
        return ResponseResult.success("success", contourService.getContoursAllInfo(contourYear, contourName));

    }

    @GetMapping("/getcontourrele")
    public @ResponseBody ResponseResult<List<String>> getContourRele(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") String contourName){
        //1、参数校验
        //ContourChecker.check4GetContourInfo(contourYear, contourName);

        //2、获取轮廓信息
        return ResponseResult.success("success", contourService.getOneContourRele(contourYear, contourName));
    }

    @GetMapping("/getcontourpath")
    public @ResponseBody
    ResponseResult<String> processMapContour(@RequestParam("contourYear") Integer contourYear, @RequestParam("contourName") String contourName) {
        String mapPath = contourService.processMapContour(contourYear, contourName);
        return ResponseResult.success("success", mapPath);

    }

}
