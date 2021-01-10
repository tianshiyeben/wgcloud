package com.wgcloud.mapper;

import com.wgcloud.entity.DbInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:DbInfoMapper.java
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbInfoDao.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface DbInfoMapper {

    public List<DbInfo> selectAllByParams(Map<String, Object> map) throws Exception;
    
	public List<DbInfo> selectByParams(Map<String, Object> params) throws Exception;
	
    public DbInfo selectById(String id) throws Exception;
    
    public void save(DbInfo DbInfo) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;
    
    public int updateById(DbInfo DbInfo) throws Exception;
}
