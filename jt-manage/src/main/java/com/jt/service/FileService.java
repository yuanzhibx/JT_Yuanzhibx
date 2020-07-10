package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
public interface FileService {

    /**
     * 实现文件的上传操作
     *
     * @param uploadFile 文件
     * @return ImageVO 对象
     */
    ImageVO uploadFile(MultipartFile uploadFile);

}
