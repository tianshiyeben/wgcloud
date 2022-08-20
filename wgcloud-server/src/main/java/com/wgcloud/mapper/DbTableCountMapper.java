package com.wgcloud.mapper;

import com.wgcloud.entity.DbTableCount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableCountMapper.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbTableCountDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface DbTableCountMapper {

    public List<DbTableCount> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<DbTableCount> selectByParams(Map<String, Object> params) throws Exception;

    public DbTableCount selectById(String id) throws Exception;

    public void save(DbTableCount DbTableCount) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public void insertList(List<DbTableCount> recordList) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public int updateById(DbTableCount DbTableCount) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;
}
