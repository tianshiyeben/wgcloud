package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.IntrusionInfo;

/**
 *
 * @ClassName:IntrusionInfoDao.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description:  查看系统入侵信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface IntrusionInfoDao {

	
    public List<IntrusionInfo> selectAllByParams(Map<String,Object> map) throws Exception ;
    
    
    public List<IntrusionInfo> selectByAccountId(String accountId) throws Exception ;
    
    
	public List<IntrusionInfo> selectByParams(Map<String,Object> params) throws Exception;
	
	
    public IntrusionInfo selectById(String id) throws Exception;
    
    
    public void save(IntrusionInfo IntrusionInfo) throws Exception;
    
    public void insertList(List<IntrusionInfo> recordList) throws Exception;
    
    public int deleteById(String[] id) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
    
    public int deleteByAccHname(Map<String,Object> map) throws Exception;
    

}
