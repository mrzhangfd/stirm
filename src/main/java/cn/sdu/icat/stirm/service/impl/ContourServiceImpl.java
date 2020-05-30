package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.dao.ContourDao;
import cn.sdu.icat.stirm.dal.mapper.ContourMapper;
import cn.sdu.icat.stirm.model.Contour;
import cn.sdu.icat.stirm.service.ContourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户实现类
 *
 * @author icatzfd
 * Created on 2019/11/2 15:46.
 */
@Service
public class ContourServiceImpl implements ContourService {

    @Autowired
    private ContourMapper contourMapper;

    @Autowired
    private ContourDao contourDAO;


    @Override
    public List<Contour> getContourInfos() {
       return contourDAO.selectById(14);
    }

    @Override
    public List<Contour> getContourInfo(Integer contourYear, String contourName) {
        return contourDAO.selectByYearAndName(contourYear,contourName);
    }
}
