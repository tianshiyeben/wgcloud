package com.service.log;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Page;
import com.dao.log.LogInfoDao;
import com.entity.log.LogInfo;
import com.github.pagehelper.PageHelper;
import com.util.DateUtil;
import com.util.UUIDUtil;

/**
 *
 * @ClassName:LogInfoService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: LogInfoService.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class LogInfoService{

	private static final Logger logger = LoggerFactory.getLogger(LogInfoService.class);
	
	public Page selectByParams(Map<String, Object> params, int currPage,int pageSize) throws Exception {
		PageHelper.startPage(currPage, pageSize);
		List<LogInfo> list = logInfoDao.selectByParams(params);
		return new Page(list, pageSize,Integer.valueOf(((com.github.pagehelper.Page) list).getTotal()+""),currPage);
	}

	public void save(String account,String hostname,String infoContent,String state){
		LogInfo logInfo = new LogInfo();
		logInfo.setAccount(account);
		logInfo.setHostname(hostname);
		logInfo.setInfoContent(infoContent);
		logInfo.setState(state);
		logInfo.setId(UUIDUtil.getUUID());
		logInfo.setCreateTime(DateUtil.getNowTime());
		try {
			 logInfoDao.save(logInfo);
		} catch (Exception e) {
			logger.error("保存日志信息异常：",e);
		}
	}
	
	public int deleteById(String[] id) throws Exception {
		return logInfoDao.deleteById(id);
	}

	public LogInfo selectById(String id)  throws Exception{
		return logInfoDao.selectById(id);
	}

	public List<LogInfo> selectAllByParams(Map<String, Object> params)throws Exception {
		return logInfoDao.selectAllByParams(params);
	}
	
	@Autowired
	private LogInfoDao logInfoDao;



}
