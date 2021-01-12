package com.dao.log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.log.LogInfo;

/**
 *
 * @ClassName:LogInfoDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看日志信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface  LogInfoDao{

	
    public List<LogInfo> selectAllByParams(Map<String,Object> map);
    
    
	public List<LogInfo> selectByParams(Map<String,Object> params) throws Exception;
	
    public LogInfo selectById(String id) throws Exception;
    
    public void save(LogInfo LogInfo) throws Exception;
    
    public int deleteById(String[] id) throws Exception;

    public int deleteByDate(Map<String,Object> map) throws Exception;
   
}
