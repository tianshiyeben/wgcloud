package com.service.host;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.host.DeskStateDao;
import com.entity.host.DeskState;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:DeskStateService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: DeskStateService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class DeskStateService{

	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<DeskState> list = deskStateDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(DeskState DeskState) throws Exception {
		DeskState.setId(UUIDUtil.getUUID());
		DeskState.setCreateTime(DateUtil.getNowTime());
		DeskState.setDateStr(DateUtil.getDateTimeString(DeskState.getCreateTime()));
		deskStateDao.save(DeskState);
	}
	
	public int deleteById(String[] id) throws Exception {
		return deskStateDao.deleteById(id);
	}

	public DeskState selectById(String id)  throws Exception{
		return deskStateDao.selectById(id);
	}

	public List<DeskState> selectAllByParams(Map<String, Object> params)throws Exception {
		return deskStateDao.selectAllByParams(params);
	}
	
	public int deleteByAccHname(Map<String,Object> params) throws Exception{
		return deskStateDao.deleteByAccHname(params);
	}
	
	
	@Autowired
	private DeskStateDao deskStateDao;



}
