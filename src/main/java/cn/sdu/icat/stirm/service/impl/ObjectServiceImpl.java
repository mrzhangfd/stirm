package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.dao.EventRepository;
import cn.sdu.icat.stirm.dal.dao.HbaseDao;
import cn.sdu.icat.stirm.dal.mapper.ProMapFileNameMapper;
import cn.sdu.icat.stirm.dal.mapper.SiteInfoMapper;
import cn.sdu.icat.stirm.model.Event;
import cn.sdu.icat.stirm.model.HbaseModel;
import cn.sdu.icat.stirm.model.PO.ProMapNamePO;
import cn.sdu.icat.stirm.model.RealEntity;
import cn.sdu.icat.stirm.model.Site;
import cn.sdu.icat.stirm.service.ObjectService;
import cn.sdu.icat.stirm.util.FilePath;
import cn.sdu.icat.stirm.util.HbaseModelUtil;
import cn.sdu.icat.stirm.util.ImageUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * 信息对象业务实现类
 *
 * @author icatzfd
 * Created on 2020/6/8 15:13.
 */
@Service("objectService")
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    HbaseDao hbaseDao;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SiteInfoMapper siteInfoMapper;

    @Autowired
    ProMapFileNameMapper proMapFileNameMapper;

    @Override
    public List<RealEntity> getEntitiesByPrefix(String prefix) {
        List<Result> rets = hbaseDao.getDataWithSameBegining(HbaseModelUtil.BASIC_TABLE, prefix);
        Iterator<Result> iterator = rets.iterator();
        HashMap<String, RealEntity> entityHashMap = new HashMap<>();
        while (iterator.hasNext()) {
            Result ret = iterator.next();
            for (KeyValue kv : ret.list()) {
                HbaseModel hbaseModel = HbaseModelUtil.kvToHbaseModel(kv);
                if (entityHashMap.containsKey(hbaseModel.getRow())) {
                    RealEntity entity = entityHashMap.get(hbaseModel.getRow());
                    RealEntity realEntity = packageModel(entity, hbaseModel);
                    entityHashMap.remove(hbaseModel.getRow());
                    entityHashMap.put(hbaseModel.getRow(), realEntity);

                } else {
                    RealEntity entity = new RealEntity();
                    entity.setObjectId(hbaseModel.getRow());
                    RealEntity realEntity = packageModel(entity, hbaseModel);
                    entityHashMap.put(hbaseModel.getRow(), realEntity);
                }
            }
        }

        ArrayList<RealEntity> entities = new ArrayList<>();
        Iterator<String> iterator1 = entityHashMap.keySet().iterator();
        while (iterator1.hasNext()) {
            String s = iterator1.next();
            RealEntity entity = entityHashMap.get(s);
            entities.add(entity);
        }
        return entities;
    }

    @Override
    public RealEntity getObjectTimeLine(String name) throws Exception {
        List<RealEntity> realEntities = this.getEntitiesByPrefix(name);
        String objectId = realEntities.get(0).getObjectId();


        return this.findEntityByIdFromEs(objectId);
    }

    @Override
    public RealEntity findEntityByIdFromEs(String objectId) throws Exception {
        Result result = hbaseDao.getDataFromRowkey(HbaseModelUtil.BASICTABLE, objectId);
        RealEntity realEntity = new RealEntity();
        realEntity.setObjectId(objectId);
        for (KeyValue kv : result.list()) {
            HbaseModel hbaseModel = HbaseModelUtil.kvToHbaseModel(kv);
            realEntity = packModelFromEs(realEntity, hbaseModel);
        }
        return realEntity;
    }

    @Override
    public String processMapObjectTrack(String name) throws Exception {




        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);
        List<RealEntity> realEntities = this.getEntitiesByPrefix(name);
        String objectId = realEntities.get(0).getObjectId();



        RealEntity entityByIdFromEs = this.findEntityByIdFromEs(objectId);
        List<Event> events = entityByIdFromEs.getEvents();
        events.remove(events.size() - 1);
        int year = Integer.parseInt(entityByIdFromEs.getEvents().get(0).getTs().substring(0, 4));
        List<Site> sites = new ArrayList<>();

        //先查询是否存在已经处理过的图片，若存在，直接返回路径，若不存在，写入数据库
        ProMapNamePO proMapNamePO = proMapFileNameMapper.selectOne(year, entityByIdFromEs.getObjectId());
        if (proMapNamePO != null) {
            return FilePath.LOCALHOST_MAP_PATH.getPath() + proMapNamePO.getProMapName();
        }
        for (Event event : entityByIdFromEs.getEvents()) {
            sites.add(siteInfoMapper.selectOne(year, event.getSite()));
        }

        List<Point> points = new ArrayList<>();
        for (Site site : sites) {
            String tmp = site.getSiteCentre();
            String first = tmp.split(",")[0];
            String x = first.substring(1);
            String second = tmp.split(",")[1];
            String y = second.substring(0, second.length() - 1);
            Point point = new Point(Double.parseDouble(x), Double.parseDouble(y));
            points.add(point);
        }

        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + year + ".jpg");

        //复制到temp
        Mat temp = new Mat();
        src.copyTo(temp);

        for (int i = 0; i < points.size() - 1; i++) {
            Imgproc.arrowedLine(temp, points.get(i), points.get(i + 1), new Scalar(255, 255, 255), 2, 8, 0, 0.1);

        }

        BufferedImage bufferedImage = ImageUtil.Mat2BufImg(temp, ".jpg");
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        //设置字体
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        graphics.setFont(font);

        for (int i = 0; i < points.size() - 1; i++) {

            graphics.drawString(sites.get(i).getSiteName(),
                    new Double(points.get(i).x + 20).floatValue(),
                    new Double(points.get(i).y + 5).floatValue());
        }
        //设置坐标

        graphics.dispose();
        temp = ImageUtil.BufImg2Mat(bufferedImage, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);

        //Imgproc.arrowedLine(temp, new Point(2517.0, 994.0), new Point(2711, 906), new Scalar(255, 255, 255));
        System.out.println(Imgcodecs.imwrite(FilePath.TEST_IMAGE.getPath() + entityByIdFromEs.getObjectId() + ".jpg", temp));

        //使用时间戳
        String proMapName = System.currentTimeMillis() + ".jpg";
        System.out.println(Imgcodecs.imwrite(FilePath.PRO_MAP_FILE_PATH.getPath() + proMapName, temp));

        String path = FilePath.LOCALHOST_MAP_PATH.getPath() + proMapName;

        proMapFileNameMapper.insert(year, entityByIdFromEs.getObjectId(), proMapName);

        return path;
    }

    public RealEntity packageModel(RealEntity realEntity, HbaseModel hbaseModel) {
        //System.out.println(hbaseModel.getFamilyName());
        ArrayList<Event> eventList = realEntity.getEvents();
        if (HbaseModelUtil.BASIC_EVENT.equals(hbaseModel.getFamilyName())) {
            Event event = new Event();
            String eventId = hbaseModel.getValue();
            Result ret = hbaseDao.getDataFromRowkey(HbaseModelUtil.EVENTS_TABLE, eventId);
            for (KeyValue kv : ret.list()) {
                HbaseModel model = HbaseModelUtil.kvToHbaseModel(kv);
                String value = model.getValue();
                switch (model.getQualifier()) {
                    case "ts":
                        event.setTs(value);
                        break;
                    case "site":
                        event.setSite(value);
                        break;
                    case "details":
                        event.setDetails(value);
                        break;
                    default:
                        event.setAffect(value);
                }
                event.setEventId(eventId);
            }
            eventList.add(event);
        }
        if (HbaseModelUtil.BASIC_RAW.equals(hbaseModel.getFamilyName())) {
            if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN2)) {
                realEntity.setRawInfo(hbaseModel.getValue());
            } else if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN1)) {
                realEntity.setRealName(hbaseModel.getValue());
            } else {
                Map<String, String> params = realEntity.getParams();
                params.put(hbaseModel.getQualifier(), hbaseModel.getValue());
                realEntity.setParams(params);
            }

        }
        return realEntity;
    }


    public RealEntity packModelFromEs(RealEntity realEntity, HbaseModel hbaseModel) {
        //System.out.println(hbaseModel.getFamilyName());
        ArrayList<Event> eventList = realEntity.getEvents();
        if (HbaseModelUtil.BASIC_EVENT.equals(hbaseModel.getFamilyName())) {

            String eventId = hbaseModel.getValue();
            Event eventByEventId = eventRepository.queryEventByEventId(eventId);
            if (eventByEventId != null) {
                eventList.add(eventByEventId);
            }

        }
        if (HbaseModelUtil.BASIC_RAW.equals(hbaseModel.getFamilyName())) {
            if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN2)) {
                realEntity.setRawInfo(hbaseModel.getValue());
            } else if (hbaseModel.getQualifier().equals(HbaseModelUtil.COLUMN1)) {
                realEntity.setRealName(hbaseModel.getValue());
            } else {
                Map<String, String> params = realEntity.getParams();
                params.put(hbaseModel.getQualifier(), hbaseModel.getValue());
                realEntity.setParams(params);
            }

        }
        realEntity.setEvents(eventList);
        return realEntity;
    }
}
