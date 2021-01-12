package com.service.host;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.DiskIoStateDao;
import com.entity.host.DiskIoState;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:DiskIoStateService.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: DiskIoStateService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class DiskIoStateService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<DiskIoState> list = diskIoStateDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(DiskIoState DiskIoState) throws Exception {
		DiskIoState.setId(UUIDUtil.getUUID());
		DiskIoState.setCreateTime(DateUtil.getNowTime());
		DiskIoState.setDateStr(DateUtil.getDateTimeString(DiskIoState.getCreateTime()));
		diskIoStateDao.save(DiskIoState);
	}
	
	public void saveRecord(List<DiskIoState> recordList) throws Exception {
		 for(DiskIoState as : recordList){
			 as.setId(UUIDUtil.getUUID());
			 as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
		 }
		 diskIoStateDao.insertList(recordList);
	}
	
	public int deleteById(String[] id) throws Exception {
		return diskIoStateDao.deleteById(id);
	}

	public DiskIoState selectById(String id)  throws Exception{
		return diskIoStateDao.selectById(id);
	}

	public List<DiskIoState> selectAllByParams(Map<String, Object> params)throws Exception {
		return diskIoStateDao.selectAllByParams(params);
	}
	
	
	@Autowired
	private DiskIoStateDao diskIoStateDao;



}
