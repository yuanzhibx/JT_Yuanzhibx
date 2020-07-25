package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Cart;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
public interface CartMapper extends BaseMapper<Cart> {

    @Update("UPDATE  tb_cart SET num=#{num}, updated = #{date} WHERE id = #{id}")
    void updateCarNum(Integer id, int num, Date date);

}
