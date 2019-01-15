package com.util.staticvar;



/**
 *
 * @ClassName:HostKeys.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: HostKeys.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class HostKeys {
	
	
	public static int CHART_DATA_SIZE = 72;//图表默认显示数据数量
	
	//处理远程请求成功
	public static final String RESULT_SUCCESS = "success";
	
	//处理远程请求，帐号过期
	public static final String RESULT_EXP = "exp";
	
	//处理远程请求，帐号不存在
	public static final String RESULT_NO_EXISTS = "error";
	
	//处理远程请求，出错
	public static final String RESULT_ERROR = "error";
	
	//当超过41个柱子时候，动态计算echart图表宽度
	public static final int BAR_SIZE = 36;
	
	//echart柱图长度计算参数宽度
	public static final int BAR_WIDTH = 33;
	
	//内存报警百分比
	public static final double MEM_WARN_VAL = 99;
	
	//进程监控内存报警百分比
	public static final double APP_MEM_WARN_VAL = 99;
	
	//进程监控CPU报警百分比
	public static final double APP_CPU_WARN_VAL = 99;
}
