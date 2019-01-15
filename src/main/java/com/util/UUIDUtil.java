package com.util;

import java.util.Random;
import java.util.UUID;


/**
 *
 * @ClassName:UUIDUtil.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: UUIDUtil.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class UUIDUtil {
	
	public static String getUUID(){
		return String.valueOf(UUID.randomUUID()).replace("-", "");
	}
	
	/**
	 * 随机6位数字
	 * @return
	 */
	public static String getRandomSix(){
		return ""+new Random().nextInt(999999);
	}
	
}
