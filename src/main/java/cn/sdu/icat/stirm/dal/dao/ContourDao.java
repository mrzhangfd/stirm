package cn.sdu.icat.stirm.dal.dao;

import cn.sdu.icat.stirm.dal.mapper.ContourMapper;
import cn.sdu.icat.stirm.model.Contour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮廓DAO
 *
 * @author icatzfd
 * Created on 2019/11/20 15:34.
 */
@Repository
public class ContourDao {

    @Autowired
    private ContourMapper contourMapper;

    public List<Contour> selectById(Integer year) {
        return contourMapper.selectByYear(year);
    }

    public List<Contour> selectByYearAndName(Integer contourYear, String contourName) {
        return contourMapper.selectByYearAndName(contourYear, contourName);
    }
}
