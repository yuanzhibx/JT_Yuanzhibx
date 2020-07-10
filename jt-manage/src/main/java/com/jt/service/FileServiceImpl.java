package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
@Service
public class FileServiceImpl implements FileService {

    private String localDir = "/Users/yanbingxu/Desktop/image";

    /**
     * 校验上传的文件为图片?
     *      通过后缀进行校验 .png .jpg .gif
     * 保证检索的速度更快
     *      分目录存储
     *          .hash
     *          时间
     * 防止文件重名
     *      重定义文件名称 uuid
     *
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO uploadFile(MultipartFile uploadFile) {
        //1. 校验上传的信息是否为图片
        //1.1. 初始化图片类型集合
        Set<String> typeSet = new HashSet<>();
        typeSet.add(".jpg");
        typeSet.add(".png");
        typeSet.add(".gif");

        //1.2. 校验图片类型是否有效
        String fileName = uploadFile.getOriginalFilename();
        //将所有字符转化为小写
        fileName = fileName.toLowerCase();
        int index = fileName.lastIndexOf(".");

        String fileType = fileName.substring(index);
        if (!typeSet.contains(fileType)) {
            return ImageVO.fail();
        }

        //2. 准备文件上传的目录结构
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());

        String dirPath = localDir + dateDir;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            //目录不存在则新建目录
            dirFile.mkdirs();
        }

        //3. 重命名文件
        String uuid = UUID.randomUUID().toString();
        String realFileName = uuid + fileType;

        //4. 执行文件上传代码
        File imageFile = new File(dirPath+realFileName);
        try {
            uploadFile.transferTo(imageFile);
            String url = "https://img10.360buyimg.com/n1/jfs/t1/119487/18/3178/206106/5eae8b24Ebfa7aaf8/bb1977b12647f1fd.jpg";
            return ImageVO.success(url);
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail();
        }
    }

}
