package com.service.app;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Page;
import com.dao.app.AppInfoDao;
import com.dao.app.AppStateDao;
import com.entity.app.AppInfo;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:AppInfoService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: AppInfoService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class AppInfoService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<AppInfo> list = appInfoDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(AppInfo AppInfo) throws Exception {
		AppInfo.setId(UUIDUtil.getUUID());
		AppInfo.setCreateTime(DateUtil.getNowTime());
		if(!StringUtils.isEmpty(AppInfo.getAppPid())){
			AppInfo.setAppPid(AppInfo.getAppPid().trim());
		}
		appInfoDao.save(AppInfo);
	}
	
	@Transactional
	public int deleteById(String[] id) throws Exception {
		for(String AppInfoId : id){
			appStateDao.deleteByAppInfoId(AppInfoId);
		}
		return appInfoDao.deleteById(id);
	}
	
	public AppInfo updateById(AppInfo AppInfo)
			throws Exception {
		return appInfoDao.updateById(AppInfo);
	}

	public AppInfo selectById(String id)  throws Exception{
		return appInfoDao.selectById(id);
	}

	public List<AppInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return appInfoDao.selectAllByParams(params);
	}
	
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private AppStateDao appStateDao;



}
