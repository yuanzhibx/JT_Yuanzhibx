package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * http://localhost:8091/file.jsp
     * 参数 File=FileImage
     * 字符串
     *
     * MultipartFile 接口: 负责实现文件接收
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {
        //1. 定义文件目录信息
        String dirPath = "/Users/yanbingxu/Desktop/images";
        File fileDir = new File(dirPath);
        //2. 校验文件目录是否存在
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        //3. 获取文件信息 (请求参数中获取)
        String fileName = fileImage.getOriginalFilename();
        //4. 实现文件上传
        File file = new File(dirPath + "/" + fileName);
        //5. 利用 API 实现文件的输出
        fileImage.transferTo(file);
        return "上传成功";
    }

    /**
     * 实现文件的上传
     * url 地址: /pic/upload
     *
     * @param uploadFile
     * @return ImageVO 对象
     */
    @RequestMapping("/pic/upload")
    public ImageVO uploadFile(MultipartFile uploadFile) {
        return fileService.uploadFile(uploadFile);
    }
}
