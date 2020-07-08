package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Yuanzhibx
 * @Date 2020-07-08
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    /**
     * status==200 调用正确  status==201 调用失败
     */
    private Integer status;

    /**
     * 提交服务器相关说明信息
     */
    private String msg;

    /**
     * 服务器返回页面的业务数据  一般都是对象的JSON.
     */
    private Object data;

    /**
     * 失败提交方式
     * @return
     */
    public static SysResult fail() {
        return new SysResult(201, "业务调用失败~", null);
    }

    /**
     * 成功提交方式 1: 只返回状态码信息
     * @return
     */
    public static SysResult success() {
        return new SysResult(200, "业务调用成功~", null);
    }

    /**
     * 成功提交方式 2: 返回服务器数据 data
     * @return
     */
    public static SysResult success(Object data) {
        return new SysResult(200, "业务调用成功~", data);
    }

    /**
     * 成功提交方式 3: 返回服务器信息 msg 及 服务器数据 data
     * @return
     */
    public static SysResult success(String msg, Object data) {
        return new SysResult(200, msg, data);
    }

}
