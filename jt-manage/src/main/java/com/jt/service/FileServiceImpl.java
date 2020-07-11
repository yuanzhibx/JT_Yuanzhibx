package com.jt.service;

import com.jt.util.ImageTypeUtil;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通过指定配置文件, 进行属性的注入
 *
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {

    @Value("${image.localDir}")
    private String localDir;

    @Autowired
    private ImageTypeUtil imageTypeUtil;

    /**
     * 实现文件的上传操作
     * 1. 通过校验上传的文件是否为 .png .jpg .gif 结尾校验上传的文件是否为图片
     * 2. 分目录存储保证检索的速度更快 (.hash | 时间)
     * 3. 通过 uuid 重定义文件名称防止文件重名
     *
     * @param uploadFile 文件
     * @return ImageVO 对象
     */
    @Override
    public ImageVO uploadFile(MultipartFile uploadFile) {
        //1. 校验上传的信息是否为图片
        // 通过静态代码块实现 | 利用spring方式进行优化
        Set<String> typeSet = imageTypeUtil.getTypeSet();

        // 动态获取用户上传的图片类型, 校验图片类型是否有效
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
        File imageFile = new File(dirPath + realFileName);
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
