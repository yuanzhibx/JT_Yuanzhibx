package com.jt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yuanzhibx
 * @Date 2020-07-11
 */
@Component
@PropertySource("classpath:/properties/image.properties")
public class ImageTypeUtil {

    //可以利用spring容器动态为属性赋值.
    @Value("${image.imageTypes}")
    private String imageTypes;
    private Set<String> typeSet = new HashSet<String>();

    //初始化集合信息
    @PostConstruct    //当对象交给容器管理之后,执行该方法
    public void init() {

        String[] typeArray = imageTypes.split(",");
        for (String type : typeArray) {
            typeSet.add(type);
        }
        //循环遍历完成之后,.typeSet集合类型中有值的.
        System.out.println("set集合初始化完成!!!!"+typeSet);
    }

    public Set<String> getTypeSet(){
        return typeSet;
    }

//    private static Set<String> typeSet = new HashSet<>();
//
//    /**
//     * 一般图片类型为常见的几种, 变化不大, 暂时可写死
//     */
//    static {
//        typeSet.add(".jpg");
//        typeSet.add(".png");
//        typeSet.add(".gif");
//        typeSet.add(".svg");
//        typeSet.add(".bmp");
//    }
//
//    public Set<String> getTypeSet() {
//        return typeSet;
//    }

}
