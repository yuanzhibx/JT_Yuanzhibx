package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * pojo基类，完成2个任务，2个日期，实现序列化
 *
 * @author Yuanzhibx
 * @Date 2020-07-06
 */
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{

	private Date created;
	private Date updated;

}
