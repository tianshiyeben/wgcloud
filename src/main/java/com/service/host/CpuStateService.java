package com.service.host;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.CpuStateDao;
import com.entity.host.CpuState;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:CpuStateService.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: CpuStateService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class CpuStateService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<CpuState> list = cpuStateDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(CpuState CpuState) throws Exception {
		CpuState.setId(UUIDUtil.getUUID());
		CpuState.setCreateTime(DateUtil.getNowTime());
		CpuState.setDateStr(DateUtil.getDateTimeString(CpuState.getCreateTime()));
		cpuStateDao.save(CpuState);
	}
	
	public void saveRecord(List<CpuState> recordList) throws Exception {
		 for(CpuState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 cpuStateDao.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return cpuStateDao.deleteById(id);
	}

	public CpuState selectById(String id)  throws Exception{
		return cpuStateDao.selectById(id);
	}

	public List<CpuState> selectAllByParams(Map<String, Object> params)throws Exception {
		return cpuStateDao.selectAllByParams(params);
	}
	
	
	@Autowired
	private CpuStateDao cpuStateDao;



}
