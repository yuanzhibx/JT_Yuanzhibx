package jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import jt.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yuanzhibx
 * @Date 2020-07-25
 */
@Service
public class DubboCartServiceImpl implements DubboCartService {

    @Autowired
    private CartMapper cartMapper;

}
