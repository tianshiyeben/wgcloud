package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.DeskState;

/**
 *
 * @ClassName:DeskStateDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看磁盘大小使用信息
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface  DeskStateDao{

	
    public List<DeskState> selectAllByParams(Map<String,Object> map) throws Exception;
    
	public List<DeskState> selectByParams(Map<String,Object> params) throws Exception;
	
    public DeskState selectById(String id) throws Exception;
    
    public void save(DeskState DeskState) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;
    
    public int deleteByAccHname(Map<String,Object> map) throws Exception;
    

}
