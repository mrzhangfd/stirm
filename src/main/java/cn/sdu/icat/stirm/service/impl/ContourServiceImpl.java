package cn.sdu.icat.stirm.service.impl;

import cn.sdu.icat.stirm.dal.mapper.*;
import cn.sdu.icat.stirm.model.ContourEvolu;
import cn.sdu.icat.stirm.model.ContourInfo;
import cn.sdu.icat.stirm.model.ContourPoint;
import cn.sdu.icat.stirm.model.MapRele;
import cn.sdu.icat.stirm.model.PO.ProMapNamePO;
import cn.sdu.icat.stirm.model.VO.ContourInfoVO;
import cn.sdu.icat.stirm.service.ContourService;
import cn.sdu.icat.stirm.util.FilePath;
import cn.sdu.icat.stirm.util.ListUtil;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

/**
 * 轮廓实现类
 *
 * @author icatzfd
 * Created on 2019/11/2 15:46.
 */
@Service("contourService")
public class ContourServiceImpl implements ContourService {

    @Autowired
    private ContourInfoMapper contourInfoMapper;

    @Autowired
    private ContourPointMapper contourPointMapper;

    @Autowired
    private MapReleMapper mapReleMapper;

    @Autowired
    private ProMapFileNameMapper proMapFileNameMapper;

    @Autowired
    private ThreadPoolService threadPoolService;

    @Autowired
    private ContourEvoluMappper contourEvoluMappper;


    @Override
    public List<ContourInfo> getContourInfos() {
        return contourInfoMapper.selectAll();
    }

    @Override
    public Map<Integer, List<String>> getContourInfoGroup() {
        List<ContourInfo> contourInfos = contourInfoMapper.selectAll();
        Map<Integer, List<String>> listMap = new HashMap<>();
        for (ContourInfo contourInfo : contourInfos) {
            if (!listMap.containsKey(contourInfo.getContourYear())) {
                listMap.put(contourInfo.getContourYear(), new ArrayList<>(Arrays.asList(contourInfo.getContourName())));
            } else {
                List<String> list = listMap.get(contourInfo.getContourYear());
                list.add(contourInfo.getContourName());
                listMap.put(contourInfo.getContourYear(), list);
            }
        }
        return listMap;
    }

    @Override
    public List<ContourInfo> getContourInfo(Integer contourYear, String contourName) {
        return contourInfoMapper.selectByYearAndName(contourYear, contourName);
    }

    @Override
    public ContourInfo getOneContour(Integer contourYear, String contourName) {
        return contourInfoMapper.selectOneByYearAndName(contourYear, contourName);
    }

    @Override
    public List<ContourPoint> getContourPoints(Integer contourYear, String contourName) {
        return contourPointMapper.selectByYearAndName(contourYear, contourName);

    }

    @Override
    public List<Integer> getContourYear() {
        return contourInfoMapper.selectContourYear();
    }

    @Override
    public List<ContourInfoVO> getOneContourAllInfo(Integer contourYear, String contourName) {
        List<ContourInfoVO> contourInfoVOS = new ArrayList<>();
        ContourInfoVO contourInfoVO = new ContourInfoVO();
        ContourInfo contourInfo = new ContourInfo();
        contourInfo = contourInfoMapper.selectOneByYearAndName(contourYear, contourName);
        MapRele mapRele = mapReleMapper.selectOne(contourYear, contourName);
        if (mapRele != null) {
            List<String> preContourList = new ArrayList<>();
            List<String> nextContourList = new ArrayList<>();
            if (mapRele.getPreContour() != null) {
                String preContourStr = mapRele.getPreContour();
                String[] preContourStrArr = preContourStr.split("\\+");
                preContourList = new ArrayList<>(Arrays.asList(preContourStrArr));
            }
            if (mapRele.getNextContour() != null) {
                String nextContourStr = mapRele.getNextContour();
                String[] nextContourStrArr = nextContourStr.split("\\+");
                nextContourList = new ArrayList<>(Arrays.asList(nextContourStrArr));
            }

            contourInfoVO.setContourName(contourInfo.getContourName());
            contourInfoVO.setContourYear(contourInfo.getContourYear());
            contourInfoVO.setContourArea(contourInfo.getContourArea());
            contourInfoVO.setContourPerimeter(contourInfo.getContourPerimeter());

            contourInfoVO.setPreContourYear(mapRele.getPreContourYear());
            contourInfoVO.setPreContour(preContourList);
            contourInfoVO.setNextContourYear(mapRele.getNextContourYear());
            contourInfoVO.setNextContour(nextContourList);
        } else {
            contourInfoVO.setContourName(contourInfo.getContourName());
            contourInfoVO.setContourYear(contourInfo.getContourYear());
            contourInfoVO.setContourArea(contourInfo.getContourArea());
            contourInfoVO.setContourPerimeter(contourInfo.getContourPerimeter());
            contourInfoVO.setPreContourYear(0);
            contourInfoVO.setPreContour(new ArrayList<String>());
            contourInfoVO.setNextContourYear(0);
            contourInfoVO.setNextContour(new ArrayList<String>());
        }

        contourInfoVOS.add(contourInfoVO);
        return contourInfoVOS;
    }

    @Override
    public List<ContourInfoVO> getContoursAllInfo(Integer contourYear, List<String> contourNames) {
        System.out.println(contourNames);
        List<ContourInfoVO> contourInfoVOS = new ArrayList<>();
        for (String contourName : contourNames) {
            ContourInfoVO contourInfoVO = new ContourInfoVO();
            ContourInfo contourInfo = new ContourInfo();
            contourInfo = contourInfoMapper.selectOneByYearAndName(contourYear, contourName);
            if (contourInfo != null) {
                MapRele mapRele = mapReleMapper.selectOne(contourYear, contourName);
                if (mapRele != null) {
                    List<String> preContourList = new ArrayList<>();
                    List<String> nextContourList = new ArrayList<>();
                    if (mapRele.getPreContour() != null) {
                        String preContourStr = mapRele.getPreContour();
                        String[] preContourStrArr = preContourStr.split("\\+");
                        preContourList = new ArrayList<>(Arrays.asList(preContourStrArr));
                    }
                    if (mapRele.getNextContour() != null) {
                        String nextContourStr = mapRele.getNextContour();
                        String[] nextContourStrArr = nextContourStr.split("\\+");
                        nextContourList = new ArrayList<>(Arrays.asList(nextContourStrArr));
                    }
                    contourInfoVO.setContourName(contourInfo.getContourName());
                    contourInfoVO.setContourYear(contourInfo.getContourYear());
                    contourInfoVO.setContourArea(contourInfo.getContourArea());
                    contourInfoVO.setContourPerimeter(contourInfo.getContourPerimeter());

                    contourInfoVO.setPreContourYear(mapRele.getPreContourYear());
                    contourInfoVO.setPreContour(preContourList);
                    contourInfoVO.setNextContourYear(mapRele.getNextContourYear());
                    contourInfoVO.setNextContour(nextContourList);
                } else {
                    contourInfoVO.setContourName(contourInfo.getContourName());
                    contourInfoVO.setContourYear(contourInfo.getContourYear());
                    contourInfoVO.setContourArea(contourInfo.getContourArea());
                    contourInfoVO.setContourPerimeter(contourInfo.getContourPerimeter());
                    contourInfoVO.setPreContourYear(0);
                    contourInfoVO.setPreContour(new ArrayList<String>());
                    contourInfoVO.setNextContourYear(0);
                    contourInfoVO.setNextContour(new ArrayList<String>());
                }
            } else {
                contourInfoVO.setContourName(contourName);
                contourInfoVO.setContourYear(contourYear);
                contourInfoVO.setContourArea(null);
                contourInfoVO.setContourPerimeter(null);
                contourInfoVO.setPreContourYear(0);
                contourInfoVO.setPreContour(new ArrayList<String>());
                contourInfoVO.setNextContourYear(0);
                contourInfoVO.setNextContour(new ArrayList<String>());
            }

            contourInfoVOS.add(contourInfoVO);
        }
        return contourInfoVOS;
    }

    @Override
    public String processMapContour(Integer contourYear, String contourName) {
        //手写线程池，七个参数。同时指定工作队列大小。
        //如果使用jdk创建线程池，工作队列默认最大值。容易造成oom
        ExecutorService threadPool = new ThreadPoolExecutor(
                8,
                12,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        //先查询是否存在已经处理过的图片，若存在，直接返回路径，若不存在，写入数据库
        ProMapNamePO proMapNamePO = proMapFileNameMapper.selectOne(contourYear, contourName);
        if (proMapNamePO != null) {
            return FilePath.LOCALHOST_MAP_PATH.getPath() + proMapNamePO.getProMapName();
        }

        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);
        List<Point> points = new ArrayList<>();

        //获取对应的轮廓
        List<ContourPoint> contourPoints = contourPointMapper.selectByYearAndName(contourYear, contourName);
        for (ContourPoint contourPoint : contourPoints) {
            Point point = new Point();
            point.x = contourPoint.getContourPointX();
            point.y = contourPoint.getContourPointY();
            points.add(point);
        }
        MatOfPoint matOfPoint = new MatOfPoint();
        matOfPoint.fromList(points);

        List<MatOfPoint> matOfPoints = new ArrayList<>();
        matOfPoints.add(matOfPoint);

        List<List<MatOfPoint>> lists = ListUtil.averageAssign(matOfPoints, 8);
        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + contourYear + ".jpg");

        //灰度化
        //Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
        //二值化
        //Imgproc.threshold(src, src, 120, 255, Imgproc.THRESH_BINARY);
        //Mat temp = new Mat(src.size(), CvType.CV_8S, new Scalar(0, 255, 0));
        //temp.copyTo();

        Mat temp = new Mat();
        src.copyTo(temp);
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //画出轮廓
                    Imgproc.drawContours(temp, lists.get(finalI), 0, new Scalar(255, 255, 255), 5);
                }
            });
        }
        //画出轮廓
        //  Imgproc.drawContours(temp, matOfPoints, 0, new Scalar(255, 255, 255), 5);

        //使用时间戳
        String proMapName = System.currentTimeMillis() + ".jpg";
        System.out.println(Imgcodecs.imwrite(FilePath.PRO_MAP_FILE_PATH.getPath() + proMapName, temp));

        //proMapFileNameMapper.insert(contourYear, contourName, proMapName);

        String path = FilePath.LOCALHOST_MAP_PATH.getPath() + proMapName;

        return path;
    }

    @Override
    public String processMapContourPatch(Integer contourYear, String contourNameStr) {

        //先查询是否存在已经处理过的图片，若存在，直接返回路径，若不存在，写入数据库
        ProMapNamePO proMapNamePO = proMapFileNameMapper.selectOne(contourYear, contourNameStr);
        if (proMapNamePO != null) {
            return FilePath.LOCALHOST_MAP_PATH.getPath() + proMapNamePO.getProMapName();
        }

        String[] contourNamesArr = contourNameStr.split("\\+");
        List<String> contourNames = new ArrayList<>(Arrays.asList(contourNamesArr));
        //加载opencv的dll文件
        String opencvPath = FilePath.OPENCV_FILE_PATH.getPath();
        System.load(opencvPath);

        //读入图片
        Mat src = Imgcodecs.imread(FilePath.MAP_FILE_PATH.getPath() + contourYear + ".jpg");

        //复制到temp
        Mat temp = new Mat();
        src.copyTo(temp);

        List<MatOfPoint> matOfPoints = new ArrayList<>();

        //错误的初始化位置
        //List<Point> points = new ArrayList<>();

        for (String contourName : contourNames) {
            //获取对应的轮廓
            List<ContourPoint> contourPoints = contourPointMapper.selectByYearAndName(contourYear, contourName);
            //上面points初始化位置 不对。
            List<Point> points = new ArrayList<>();
            for (ContourPoint contourPoint : contourPoints) {
                Point point = new Point();
                point.x = contourPoint.getContourPointX();
                point.y = contourPoint.getContourPointY();
                points.add(point);
            }
            MatOfPoint matOfPoint = new MatOfPoint();
            matOfPoint.fromList(points);
            matOfPoints.add(matOfPoint);

        }

        //画轮廓
        Imgproc.drawContours(temp, matOfPoints, -1, new Scalar(255, 255, 255), 5);

        //使用时间戳
        String proMapName = System.currentTimeMillis() + ".jpg";
        System.out.println(Imgcodecs.imwrite(FilePath.PRO_MAP_FILE_PATH.getPath() + proMapName, temp));

        proMapFileNameMapper.insert(contourYear, contourNameStr, proMapName);

        String path = FilePath.LOCALHOST_MAP_PATH.getPath() + proMapName;

        return path;
    }

    @Override
    public List<String> getOneContourRele(Integer contourYear, String contourName) {
        MapRele mapRele = mapReleMapper.selectOne(contourYear, contourName);
        if (mapRele == null) {
            return new ArrayList<String>();
        }

        //获取前序时间
        int preContourYear = mapRele.getPreContourYear();

        //前序名字
        String preContour = mapRele.getPreContour();
        //获取后序时间
        int nextContourYear = mapRele.getNextContourYear();
        //后序名字
        String nextContour = mapRele.getNextContour();

        //前序地图路径
        String preMapPath = this.processMapContourPatch(preContourYear, preContour);

        //当前地图路径
        String curMapPath = this.processMapContour(contourYear, contourName);

        //后序地图路径
        String nextMapPath = this.processMapContourPatch(nextContourYear, nextContour);

        //将前序、当前、后序地图的路径封装到list
        List<String> mapPath = new ArrayList<>();
        mapPath.add(preMapPath);
        mapPath.add(curMapPath);
        mapPath.add(nextMapPath);

        return mapPath;
    }

    @Override
    public List<ContourEvolu> getOneContourEvolu(Integer contourYear, String contourName) {
        List<ContourEvolu> contourEvolus = new ArrayList<>();
        contourEvolus.add(contourEvoluMappper.selectOne(contourYear, contourName));
        return contourEvolus;
    }


}
