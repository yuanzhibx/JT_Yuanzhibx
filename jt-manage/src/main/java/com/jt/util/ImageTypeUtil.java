package com.jt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yuanzhibx
 * @Date 2020-07-11
 */
@Component
public class ImageTypeUtil {

    private static Set<String> typeSet = new HashSet<>();

    /**
     * 一般图片类型为常见的几种, 变化不大, 暂时可写死
     */
    static {
        typeSet.add(".jpg");
        typeSet.add(".png");
        typeSet.add(".gif");
        typeSet.add(".svg");
        typeSet.add(".bmp");
    }

    public Set<String> getTypeSet() {
        return typeSet;
    }

}
