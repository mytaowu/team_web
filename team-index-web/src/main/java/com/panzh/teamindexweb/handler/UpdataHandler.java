package com.panzh.teamindexweb.handler;


import com.panzh.util.ImageProcess;
import com.panzh.util.ImageUtil;
import com.panzh.utilbean.ResultVO;
import com.panzh.vo.ImageInfoVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * @author: TMingYi
 * @date: 2020/5/16 10:40
 */
@Controller
@Api("此类主要处理上传文件（图片）的请求")
public class UpdataHandler {

    @RequestMapping("updateImage")
    @ResponseBody
    public ImageInfoVO updateImage(@RequestParam("file") MultipartFile img){
        Map<String, Object> map;
        try {
//          map = ImageUtil.uploadImag( ImageUtil.transfByTeAndThumbnail(img));
            // 更换了图片压缩算法;
            ImageProcess process = new ImageProcess(new ByteArrayInputStream(img.getBytes()), img.getOriginalFilename());
            map = ImageUtil.uploadImag(process.resize());
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageInfoVO(false, ResultVO.NO_DATA);
        }
        return new ImageInfoVO(true, (String) map.get("fileName"));
    }
}
