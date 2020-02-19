package com.wgcloud.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.CpuState;

/**
 *
 * @ClassName:CpuStateDao.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: CpuStateDao.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface CpuStateMapper {

	
    public List<CpuState> selectAllByParams(Map<String, Object> map) throws Exception ;
    
	public List<CpuState> selectByParams(Map<String, Object> params) throws Exception;
	
    public CpuState selectById(String id) throws Exception;
    
    public int selectByParamsCount(Map<String, Object> map);
    
    public void save(CpuState CpuState) throws Exception;
    
    public void insertList(List<CpuState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;
       
    public int deleteById(String[] id) throws Exception;

}
