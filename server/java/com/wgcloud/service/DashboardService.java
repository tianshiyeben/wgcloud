package com.wgcloud.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wgcloud.entity.AppState;
import com.wgcloud.entity.CpuState;
import com.wgcloud.entity.MemState;
import com.wgcloud.entity.NetIoState;
import com.wgcloud.entity.SysLoadState;
import com.wgcloud.entity.TcpState;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:DashboardService.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 主面板信息管理
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Service
public class DashboardService {
	

	/**
	 * 获取从今天开始，往前倒数天数的日期集合
	 * @param days
	 * @return
	 */
	public List<String>  getDateList(){
		int days = 7;
		List<String> dateList = new ArrayList<String>();
		String nowTime = DateUtil.getCurrentDateTime();
		String sevenDayBefore = DateUtil.getDateBefore(nowTime, days);
		for(int i = 0; i< days; i++){
			sevenDayBefore = DateUtil.getDateBefore(nowTime, i);
			dateList.add(sevenDayBefore.substring(0, 10));
		}
		return dateList;
	}

	/**
	 * 查看详细信息监控时候，组装日期查询条件
	 * @param params
	 * @param date
	 */
	public void setDateParam(String date,Map<String, Object> params){
		params.put(StaticKeys.SEARCH_START_TIME, date+" 00:00:00");
		params.put(StaticKeys.SEARCH_END_TIME, date+" 23:59:59");
	}
	
}
