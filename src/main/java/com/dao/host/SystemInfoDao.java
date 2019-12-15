package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.SystemInfo;

/**
 *
 * @ClassName:SystemInfoDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看系统信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface SystemInfoDao {

	
    public List<SystemInfo> selectAllByParams(Map<String,Object> map) throws Exception ;
    
    public List<SystemInfo> selectByAccountId(String accountId) throws Exception ;
    
	public List<SystemInfo> selectByParams(Map<String,Object> params);
	
    public SystemInfo selectById(String id) throws Exception;
    
    public void save(SystemInfo SystemInfo) throws Exception;

    public int deleteById(String[] id) throws Exception;
    
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
    
    public int deleteByAccHname(Map<String,Object> map) throws Exception;
    

}
