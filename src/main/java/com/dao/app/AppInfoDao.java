package com.dao.app;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.app.AppInfo;

/**
 *
 * @ClassName:AppInfoDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: AppInfoDao.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface  AppInfoDao{

    public List<AppInfo> selectAllByParams(Map<String,Object> map) throws Exception;
    
	public List<AppInfo> selectByParams(Map<String,Object> params) throws Exception;
	
    public AppInfo selectById(String id) throws Exception;
    
    public List<AppInfo> selectByAccountId(String accountId) throws Exception;
    
    public void save(AppInfo AppInfo) throws Exception;
    
    
    public int deleteById(String[] id) throws Exception;
    
    public AppInfo updateById(AppInfo AppInfo) throws Exception;
}
