package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
@Controller
public class IndexController {

	/**
	 * url 地址:
	 * 			/page/item-add
	 * 			/page/item-list
	 * 			/page/item-param-list
	 * 实现网页跳转
	 *
	 * RestFul 风格:
	 * 语法:
	 * 		1. 使用 / 方式分割参数
	 * 		2. 使用 {} 包裹参数
	 * 		3. 参数方法中动态接收参数 (使用 @PathVariable)
	 * 注解: @PathVariable
	 * 		name / value 表示接收参数的名称
	 * 		required
	 * 作用:
	 * 		1. 动态获取 url 路径中的参数
	 * 		2. 以统一的 url 地址, 不同请求类型, 实现不同业务的调用
	 *
	 * RestFul 风格 2:
	 * 思路: 利用通用的url地址,实现不同的业务调用
	 * 	 例子1:  http://www.jt.com/user?id=1    		查询    	GET
	 * 	 例子2:  http://www.jt.com/user?id=1    		删除 		DELETE
	 * 	 例子3:  http://www.jt.com/user?id=1&name=xxx  	更新		PUT
	 * 	 例子4:  http://www.jt.com/user	       			新增 		POST
	 *
	 * 	@RequestMapping(value = "/user", method = RequestMethod.POST)
	 * 	@PostMapping("/user")
	 *
	 * 总结:
	 * 		一般在工作中有2种用法
	 *  	1. 动态获取url路径中的参数
	 * 		2. 以统一的url地址, 不同的请求类型, 实现不同业务的调用
	 * 		一般都会添加请求类型进行标识, 为了安全
	 *
	 * @param moduleName
	 * @return
	 */
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		return moduleName;
	}

}
