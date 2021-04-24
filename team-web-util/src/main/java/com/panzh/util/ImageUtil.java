package com.panzh.util;


import com.panzh.myconst.HttpConst;
import net.coobird.thumbnailator.Thumbnails;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageUtil {

    public static Map<String, Object> uploadImag(byte[] inByte) throws Exception {

        //本来想去修改这里的代码，想了想，还是不改了，玩意出问题咋个搞呀。
        Map<String, Object> result = new HashMap<>();
        ClientGlobal.init("tracker.conf");


        //获取一个trackerClient，并且获得连接；
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();

        //获取storageClient的对象；
        StorageClient storageClient = new StorageClient(connection, null);
        //上传图片到fastdfs服务器；
        String[] jpgs = storageClient.upload_file(inByte, 0, inByte.length, "jpg", null);
        String Imagepath = HttpConst.REAL_FASTDFS_HTTP_INDEX;
        for (int i = 0; i < jpgs.length; i++) {
            if (i == 0) {
                Imagepath += jpgs[i];
                Imagepath += "/";
                continue;
            }
            Imagepath += jpgs[i];
        }
        result.put("fileName", Imagepath);
        return result;
    }

    public static Map<String, Object> uploadImag(byte[] inByte, int width, int height, boolean isChangingSize) throws Exception {
        /**
         * 处理图片的大小，自己写的ImageUtil；我们必须要进行图片大小的处理，不然到时候展示在页面中会非常的丑！
         */
        if (isChangingSize) {
            inByte = ImageUtil.changeSize(800, 600, inByte);
        }

        //本来想去修改这里的代码，想了想，还是不改了，玩意出问题咋个搞呀。
        Map<String, Object> result = new HashMap<>();
        ClientGlobal.init("tracker.conf");

        //获取一个trackerClient，并且获得连接；
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();

        //获取storageClient的对象；
        StorageClient storageClient = new StorageClient(connection, null);
        //上传图片到fastdfs服务器；
        String[] jpgs = storageClient.upload_file(inByte, 0, inByte.length, "jpg", null);
        String Imagepath = HttpConst.REAL_FASTDFS_HTTP_INDEX;
        for (int i = 0; i < jpgs.length; i++) {
            if (i == 0) {
                Imagepath += jpgs[i];
                Imagepath += "/";
                continue;
            }
            Imagepath += jpgs[i];
        }
        result.put("fileName", Imagepath);
        return result;
    }


    public static byte[] changeSize(int newWidth, int newHeight, byte[] bytes) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        BufferedImage tag = null;
        Image bi = null;
        try {
            in = new BufferedInputStream(new ByteArrayInputStream(bytes));
            //字节流转图片对象
            bi = ImageIO.read(in);
            //构建图片流
            tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            //输出流
            out = new ByteArrayOutputStream();
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(tag);
            ImageIO.write(tag, "PNG", out);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param file 传入的图片文件
     * @return 返回压缩之后的图片
     * @throws IOException
     */
    public static byte[] transfByTeAndThumbnail(MultipartFile file) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(.1f).toOutputStream(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
