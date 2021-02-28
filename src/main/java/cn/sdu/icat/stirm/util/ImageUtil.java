package cn.sdu.icat.stirm.util;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图像工具类
 *
 * @author icatzfd
 * Created on 2021/2/28 19:14.
 */
public class ImageUtil {

    //https://my.oschina.net/findurl/blog/4678008
    //OPENCV中文显示乱码 JAVA处理
    //解决思路
    //1.MAT转IMAGE
    //2.IMAGE添加水印后转MAT

    /**
     * Mat转换成BufferedImage
     *
     * @param matrix        要转换的Mat
     * @param fileExtension 格式为 ".jpg", ".png", etc
     * @return
     */
    public static BufferedImage Mat2BufImg(Mat matrix, String fileExtension) {
        // convert the matrix into a matrix of bytes appropriate for
        // this file extension
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(fileExtension, matrix, mob);
        // convert the "matrix of bytes" into a byte array
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }

    /**
     * BufferedImage转换成Mat
     *
     * @param original 要转换的BufferedImage
     * @param imgType  bufferedImage的类型 如 BufferedImage.TYPE_3BYTE_BGR
     * @param matType  转换成mat的type 如 CvType.CV_8UC3
     */
    public static Mat BufImg2Mat(BufferedImage original, int imgType, int matType) {
        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }
        //System.loadLibrary("opencv_java412");
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.load("E:\\opencv\\opencv\\build\\java\\x64\\opencv_java412.dll");
        //System.out.println(Core.NATIVE_LIBRARY_NAME);
        // Don't convert if it already has correct type
        if (original.getType() != imgType) {
            // Create a buffered image
            BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), imgType);

            // Draw the image onto the new buffer
            Graphics2D g = image.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(original, 0, 0, null);
            } finally {
                g.dispose();
            }
        }

        byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
        Mat mat = Mat.eye(original.getHeight(), original.getWidth(), matType);
        mat.put(0, 0, pixels);
        return mat;
    }


    //此代码为网上博主的测试代码
    /**
     *https://my.oschina.net/findurl/blog/4678008
     * @param cc 识别类
     * @param image 图片
     * @param sc 颜色
     * @param flip 是否反转
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Map<String,Object> getFace(CascadeClassifier cc, Mat image, Scalar sc, boolean flip) throws UnsupportedEncodingException {
        Map<String,Object> resultMap=new HashMap<String,Object>();

        MatOfRect face = new MatOfRect();
        if(flip) {
            Core.flip(image, image, 1);
        }
        cc.detectMultiScale(image, face);

        Rect[] rects = face.toArray();
        System.out.println("匹配到 " + rects.length + " 个人脸");

        // 4 为每张识别到的人脸画一个圈
        for (int i = 0; i < rects.length; i++) {
            Imgproc.rectangle(image, new Point(rects[i].x, rects[i].y),
                    new Point(rects[i].x + rects[i].width, rects[i].y + rects[i].height), sc);

            String age=null;//analyseAge(image,rects[i]);
            String gender=null;//analyseGender(image,rects[i]);

            //
            Font font = new Font("微软雅黑", Font.PLAIN, 12);
            BufferedImage bufImg =Mat2BufImg(image,".png");
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(bufImg, 0, 0, bufImg.getWidth(),bufImg.getHeight(), null);
            g.setFont(font);              //设置字体

            //设置水印的坐标
            g.drawString("性别:"+gender+" 年龄:"+age, rects[i].x, rects[i].y);
            g.dispose();

            image=ImageUtil.BufImg2Mat(bufImg, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);// CvType.CV_8UC3

            /*
             * Imgproc.putText(image, new String(("性别:" + gender + "年龄:" +
             * age).getBytes("UTF-8")), new Point(rects[i].x, rects[i].y),
             * Imgproc.FONT_HERSHEY_PLAIN, 0.8, sc, 1, Imgproc.LINE_AA, false);
             */

        }

        if(flip) {
            Core.flip(image, image, 1);
        }

        boolean check=rects.length<1?false:true;
        resultMap.put("check", check);
        resultMap.put("Mat", image);
        return resultMap;
    }
}
