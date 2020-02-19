package com.wgcloud.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.AppState;

/**
 *
 * @ClassName:AppStateDao.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppStateDao.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface AppStateMapper {

    public List<AppState> selectAllByParams(Map<String, Object> map) throws Exception;
    
	public  List<AppState> selectByParams(Map<String, Object> params) throws Exception;
	
    public AppState selectById(String id) throws Exception;
    
    public int selectByParamsCount(Map<String, Object> map);
    
    public void save(AppState AppState) throws Exception;
    
    public void insertList(List<AppState> recordList) throws Exception;
    
    public int deleteByAppInfoId(String appInfoId) throws Exception;
   
    public int deleteByDate(Map<String, Object> map) throws Exception;
  
    public int deleteById(String[] id) throws Exception;
    

}
