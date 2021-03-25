package com.controller.other;

import java.util.UUID;

/**
 * 主键生成工具：UUID
 * 
 * @Copyright (C),TPOE
 * @author lf
 * @Date:2015年2月6日
 */
public class PrimaryKeyUtil {

	/**
	 * 得到主鍵方法
	 * 
	 * @author lf
	 * @Date 2015年2月6日
	 * @return
	 */
	public static String getPrimaryKey() {
		return UUID.randomUUID().toString();
	}
}
