package com.jt.service;

/**
 * @author Yuanzhibx
 * @Date 2020-07-21
 */
public interface UserService {

    /**
     * 检测数据库中是否有数据
     * 有 true  没有 false
     *
     * @param param 需要校验的数据
     * @param type  校验的类型 1 username、2 phone、3 email
     * @return
     */
    boolean checkUser(String param, Integer type);

}
