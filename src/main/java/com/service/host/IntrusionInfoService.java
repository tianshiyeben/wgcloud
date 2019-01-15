package com.service.host;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.IntrusionInfoDao;
import com.entity.host.IntrusionInfo;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:IntrusionInfoService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: IntrusionInfoService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class IntrusionInfoService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<IntrusionInfo> list = intrusionInfoDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(IntrusionInfo IntrusionInfo) throws Exception {
		IntrusionInfo.setId(UUIDUtil.getUUID());
		IntrusionInfo.setCreateTime(DateUtil.getNowTime());
		intrusionInfoDao.save(IntrusionInfo);
	}
	
	public void saveRecord(List<IntrusionInfo> recordList) throws Exception {
		 Map<String,Object> map= new HashMap<String,Object>();
		 for(IntrusionInfo as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 map.put("hostname", as.getHostname());
			 map.put("account", as.getAccount());
			 intrusionInfoDao.deleteByAccHname(map);
		 }
		 intrusionInfoDao.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return intrusionInfoDao.deleteById(id);
	}

	public IntrusionInfo selectById(String id)  throws Exception{
		return intrusionInfoDao.selectById(id);
	}

	public List<IntrusionInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return intrusionInfoDao.selectAllByParams(params);
	}
	
	public List<IntrusionInfo> selectByAccountId(String accountId) throws Exception {
		return intrusionInfoDao.selectByAccountId(accountId);
	}
	
	public int deleteByAccHname(Map<String,Object> params) throws Exception{
		return intrusionInfoDao.deleteByAccHname(params);
	}
	
	@Autowired
	private IntrusionInfoDao intrusionInfoDao;



}
