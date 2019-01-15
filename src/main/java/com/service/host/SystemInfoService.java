package com.service.host;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.SystemInfoDao;
import com.entity.host.SystemInfo;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:SystemInfoService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: SystemInfoService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class SystemInfoService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<SystemInfo> list = systemInfoDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(SystemInfo SystemInfo) throws Exception {
		SystemInfo.setId(UUIDUtil.getUUID());
		SystemInfo.setCreateTime(DateUtil.getNowTime());
		systemInfoDao.save(SystemInfo);
	}
	
	public int deleteById(String[] id) throws Exception {
		return systemInfoDao.deleteById(id);
	}

	public SystemInfo selectById(String id)  throws Exception{
		return systemInfoDao.selectById(id);
	}

	public List<SystemInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return systemInfoDao.selectAllByParams(params);
	}
	
	public List<SystemInfo> selectByAccountId(String accountId) throws Exception {
		return systemInfoDao.selectByAccountId(accountId);
	}
	
	public int deleteByAccHname(Map<String,Object> params) throws Exception{
		return systemInfoDao.deleteByAccHname(params);
	}
	
	
	@Autowired
	private SystemInfoDao systemInfoDao;



}
