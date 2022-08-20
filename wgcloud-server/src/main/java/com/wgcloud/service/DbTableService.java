package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.DbTable;
import com.wgcloud.mapper.DbTableMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbTableService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class DbTableService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<DbTable> list = dbTableMapper.selectByParams(params);
        PageInfo<DbTable> pageInfo = new PageInfo<DbTable>(list);
        return pageInfo;
    }

    public void save(DbTable DbTable) throws Exception {
        DbTable.setId(UUIDUtil.getUUID());
        DbTable.setCreateTime(DateUtil.getNowTime());
        dbTableMapper.save(DbTable);
    }

    public int countByParams(Map<String, Object> params) throws Exception {
        return dbTableMapper.countByParams(params);
    }

    public Long sumByParams(Map<String, Object> params) throws Exception {
        return dbTableMapper.sumByParams(params);
    }

    @Transactional
    public int deleteById(String[] id) throws Exception {
        return dbTableMapper.deleteById(id);
    }

    @Transactional
    public int deleteByDbInfoId(String dbInfoId) throws Exception {
        if (StringUtils.isEmpty(dbInfoId)) {
            return 0;
        }
        return dbTableMapper.deleteByDbInfoId(dbInfoId);
    }

    public void updateById(DbTable DbTable)
            throws Exception {
        dbTableMapper.updateById(DbTable);
    }

    @Transactional
    public void updateRecord(List<DbTable> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        dbTableMapper.updateList(recordList);
    }

    public DbTable selectById(String id) throws Exception {
        return dbTableMapper.selectById(id);
    }

    public List<DbTable> selectAllByParams(Map<String, Object> params) throws Exception {
        return dbTableMapper.selectAllByParams(params);
    }


    @Autowired
    private DbTableMapper dbTableMapper;


}
