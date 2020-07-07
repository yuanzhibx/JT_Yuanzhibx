package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.vo.Item;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
public interface ItemMapper extends BaseMapper<Item>{

    /**
     * 分页查询
     * @param start 每页起始位置
     * @param rows 每页数据总数
     * @return
     */
    @Select("SELECT * FROM tb_item ORDER BY updated DESC LIMIT #{start}, #{rows}")
    List<Item> selectItemByPage(Integer start, Integer rows);

}
