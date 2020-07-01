package cn.sdu.icat.stirm.service.impl;


import cn.sdu.icat.stirm.dal.dao.HbaseDao;
import cn.sdu.icat.stirm.service.RawinfoService;
import cn.sdu.icat.stirm.util.HbaseModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by J on  17-10-27.
 */

@Service("RawinfoService")
public class RawInfoServiceImpl implements RawinfoService {

    @Autowired
    HbaseDao hbaseDao;


    @Override
    public String findRealName(String objectId) {
        return hbaseDao.getDataFromQualifier(HbaseModelUtil.BASIC_TABLE,objectId,HbaseModelUtil.BASIC_RAW,HbaseModelUtil.COLUMN1);
    }

    @Override
    public String findRawText(String objectId) {

        return hbaseDao.getDataFromQualifier(HbaseModelUtil.BASIC_TABLE,objectId,HbaseModelUtil.BASIC_RAW,HbaseModelUtil.RAW_TEXT);
    }

    @Override
    public void addRealName(String realName, String objectId) {
        hbaseDao.insertData(HbaseModelUtil.BASICTABLE,objectId, HbaseModelUtil.CF1,HbaseModelUtil.COLUMN1,realName,null);

    }

    @Override
    public void insertRealName(String realName, String objectId) {
        hbaseDao.insertData(HbaseModelUtil.BASIC_TABLE,objectId,HbaseModelUtil.BASIC_RAW,HbaseModelUtil.COLUMN1,realName,null);
    }

    @Override
    public void addRawText(String rawText, String objectId) {
        hbaseDao.insertData(HbaseModelUtil.BASIC_TABLE,objectId, HbaseModelUtil.BASIC_RAW,HbaseModelUtil.RAW_TEXT,rawText,null);

    }

    @Override
    public String getRawinfo(String objectId) {
        return hbaseDao.getDataFromQualifier(HbaseModelUtil.BASIC_TABLE,objectId,HbaseModelUtil.BASIC_RAW,HbaseModelUtil.COLUMN2);
    }

    @Override
    public void addRawinfo(String objectId, String raw) {
        hbaseDao.insertData(HbaseModelUtil.BASIC_TABLE,objectId, HbaseModelUtil.BASIC_RAW,HbaseModelUtil.COLUMN2,raw,null);
    }

}
