package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.SysLoadState;

/**
 *
 * @ClassName:SysLoadStateDao.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 查看uptime查看系统负载状态
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface SysLoadStateDao {

	
    public List<SysLoadState> selectAllByParams(Map<String,Object> map) throws Exception;
    
    
	public List<SysLoadState> selectByParams(Map<String,Object> params) throws Exception;
	
	
    public SysLoadState selectById(String id) throws Exception;
    
    public void save(SysLoadState SysLoadState) throws Exception;
    
    
    public void insertList(List<SysLoadState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
   
    public int deleteById(String[] id) throws Exception;
    

}
