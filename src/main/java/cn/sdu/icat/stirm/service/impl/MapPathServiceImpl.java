package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.mapper.MapPathMapper;
import cn.sdu.icat.stirm.model.ContourInfo;
import cn.sdu.icat.stirm.model.MapPath;
import cn.sdu.icat.stirm.service.ContourService;
import cn.sdu.icat.stirm.service.MapPathService;
import cn.sdu.icat.stirm.util.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icatzfd
 * Created on 2020/6/16 20:48.
 */
@Service("mapPathService")
public class MapPathServiceImpl implements MapPathService {

    @Autowired
    ContourService contourService;

    @Autowired
    MapPathMapper mapPathMapper;

    @Override
    public void addMapPath() {
        String path = FilePath.MAP_FILE_PATH.getPath();
        List<Integer> mapYears = contourService.getContourYear();
        List<MapPath> mapPaths = new ArrayList<>();
        for (Integer year : mapYears) {
            MapPath mapPath = new MapPath();
            mapPath.setMapPath(path + year + ".jpg");
            mapPath.setMapYear(year);
            mapPaths.add(mapPath);
        }
        for (MapPath mapPath : mapPaths) {
            mapPathMapper.insert(mapPath.getMapYear(), mapPath.getMapPath() );
        }
    }
}
