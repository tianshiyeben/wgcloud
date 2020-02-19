package com.wgcloud.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.DeskState;

/**
 *
 * @ClassName:DeskStateDao.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 查看磁盘大小使用信息
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface DeskStateMapper {

	
    public List<DeskState> selectAllByParams(Map<String, Object> map) throws Exception;
    
	public List<DeskState> selectByParams(Map<String, Object> params) throws Exception;
	
    public DeskState selectById(String id) throws Exception;
    
    public void save(DeskState DeskState) throws Exception;

    public void insertList(List<DeskState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String, Object> map) throws Exception;

    public int deleteById(String[] id) throws Exception;
    
    public int deleteByAccHname(Map<String, Object> map) throws Exception;
    

}
