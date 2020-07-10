package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-10
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {

    /**
     * 错误信息
     * 0 错误 1 正确
     */
    private Integer error;

    /**
     * url 地址
     */
    private String url;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 失败
     */
    public static ImageVO fail() {
        return new ImageVO(1, null, null, null);
    }

    /**
     * 成功方法 1
     */
    public static ImageVO success(String url) {
        return new ImageVO(0, url, null, null);
    }

    /**
     * 成功方法 2
     */
    public static ImageVO success(String url, Integer width, Integer height) {
        return new ImageVO(0, url, width, height);
    }

}
