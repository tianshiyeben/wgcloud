package com.wgcloud.mapper;

import com.wgcloud.entity.DbTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableMapper.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbTableDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface DbTableMapper {

    public List<DbTable> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<DbTable> selectByParams(Map<String, Object> params) throws Exception;

    public DbTable selectById(String id) throws Exception;

    public void save(DbTable DbTable) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int deleteByDbInfoId(String dbInfoId) throws Exception;

    public void updateList(List<DbTable> recordList) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public Long sumByParams(Map<String, Object> params) throws Exception;

    public void updateById(DbTable DbTable) throws Exception;
}
