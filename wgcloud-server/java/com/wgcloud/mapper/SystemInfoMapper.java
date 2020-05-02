package com.wgcloud.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.SystemInfo;

/**
 *
 * @ClassName:SystemInfoDao.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 查看系统信息
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface SystemInfoMapper {

	
    public List<SystemInfo> selectAllByParams(Map<String, Object> map) throws Exception ;
    
    public List<SystemInfo> selectByAccountId(String accountId) throws Exception ;
    
	public List<SystemInfo> selectByParams(Map<String, Object> params);

    public void insertList(List<SystemInfo> recordList) throws Exception;

    public void updateList(List<SystemInfo> recordList) throws Exception;
	
    public SystemInfo selectById(String id) throws Exception;

    public int updateById(SystemInfo SystemInfo) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;
    
    public void save(SystemInfo SystemInfo) throws Exception;

    public int deleteById(String[] id) throws Exception;
    
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;
    
    public int deleteByAccHname(Map<String, Object> map) throws Exception;
    

}
