package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.CpuState;

/**
 *
 * @ClassName:CpuStateDao.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: CpuStateDao.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface  CpuStateDao{

	
    public List<CpuState> selectAllByParams(Map<String,Object> map) throws Exception ;
    
	public List<CpuState> selectByParams(Map<String,Object> params) throws Exception;
	
    public CpuState selectById(String id) throws Exception;
    
    public int selectByParamsCount(Map<String,Object> map);
    
    public void save(CpuState CpuState) throws Exception;
    
    public void insertList(List<CpuState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
       
    public int deleteById(String[] id) throws Exception;

}
