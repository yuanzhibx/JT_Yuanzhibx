package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
public interface FileService {

    ImageVO uploadFile(MultipartFile uploadFile);

}
