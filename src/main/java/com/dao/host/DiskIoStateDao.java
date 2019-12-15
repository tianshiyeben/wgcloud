package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.DiskIoState;

/**
 *
 * @ClassName:DiskIoStateDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看磁盘IO使用情况
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface DiskIoStateDao{

	
    public List<DiskIoState> selectAllByParams(Map<String,Object> map) throws Exception;
    
	public List<DiskIoState> selectByParams(Map<String,Object> params) throws Exception;
	
    public DiskIoState selectById(String id) throws Exception;
    
    public void save(DiskIoState DiskIoState) throws Exception;

    public void insertList(List<DiskIoState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
       
    public int deleteById(String[] id) throws Exception;
    

}
