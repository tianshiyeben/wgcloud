package com.dao.app;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.app.AppState;

/**
 *
 * @ClassName:AppStateDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: AppStateDao.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface  AppStateDao {

    public List<AppState> selectAllByParams(Map<String,Object> map) throws Exception;
    
	public  List<AppState> selectByParams(Map<String,Object> params) throws Exception;
	
    public AppState selectById(String id) throws Exception;
    
    public int selectByParamsCount(Map<String,Object> map);
    
    public void save(AppState AppState) throws Exception;
    
    public void insertList(List<AppState> recordList) throws Exception;
    
    public int deleteByAppInfoId(String appInfoId) throws Exception;
   
    public int deleteByAppInfoIdsAndDate(Map<String,Object> map) throws Exception;
  
    public int deleteById(String[] id) throws Exception;
    

}
