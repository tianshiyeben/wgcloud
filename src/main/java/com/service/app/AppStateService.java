package com.service.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.app.AppStateDao;
import com.entity.app.AppState;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:AppStateService.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: AppStateService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class AppStateService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<AppState> list = appStateDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(AppState AppState) throws Exception {
		AppState.setId(UUIDUtil.getUUID());
		AppState.setCreateTime(DateUtil.getNowTime());
		appStateDao.save(AppState);
	}
	
	public void saveRecord(List<AppState> recordList) throws Exception {
		 for(AppState as : recordList){
			 as.setId(UUIDUtil.getUUID());
		 }
		 appStateDao.insertList(recordList);
	}
	
	public int deleteByAppInfoId(String appInfoId) throws Exception {
		return appStateDao.deleteByAppInfoId(appInfoId);
	}
	
	
	public int deleteById(String[] id) throws Exception {
		return appStateDao.deleteById(id);
	}

	public AppState selectById(String id)  throws Exception{
		return appStateDao.selectById(id);
	}

	public List<AppState> selectAllByParams(Map<String, Object> params)throws Exception {
		return appStateDao.selectAllByParams(params);
	}
	
	
	@Autowired
	private AppStateDao appStateDao;



}
