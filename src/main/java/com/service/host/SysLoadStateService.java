package com.service.host;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.SysLoadStateDao;
import com.entity.host.SysLoadState;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:SysLoadStateService.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: SysLoadStateService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class SysLoadStateService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<SysLoadState> list = sysLoadStateDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(SysLoadState SysLoadState) throws Exception {
		SysLoadState.setId(UUIDUtil.getUUID());
		SysLoadState.setCreateTime(DateUtil.getNowTime());
		SysLoadState.setDateStr(DateUtil.getDateTimeString(SysLoadState.getCreateTime()));
		sysLoadStateDao.save(SysLoadState);
	}
	
	public void saveRecord(List<SysLoadState> recordList) throws Exception {
		 for(SysLoadState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 sysLoadStateDao.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return sysLoadStateDao.deleteById(id);
	}

	public SysLoadState selectById(String id)  throws Exception{
		return sysLoadStateDao.selectById(id);
	}

	public List<SysLoadState> selectAllByParams(Map<String, Object> params)throws Exception {
		return sysLoadStateDao.selectAllByParams(params);
	}
	
	
	@Autowired
	private SysLoadStateDao sysLoadStateDao;



}
