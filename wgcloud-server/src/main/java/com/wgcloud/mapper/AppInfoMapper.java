package com.wgcloud.mapper;

import com.wgcloud.entity.AppInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:AppInfoDao.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppInfoDao.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Repository
public interface AppInfoMapper {

    public List<AppInfo> selectAllByParams(Map<String, Object> map) throws Exception;

    public List<AppInfo> selectByParams(Map<String, Object> params) throws Exception;

    public AppInfo selectById(String id) throws Exception;

    public List<AppInfo> selectByAccountId(String accountId) throws Exception;

    public void save(AppInfo AppInfo) throws Exception;

    public void insertList(List<AppInfo> recordList) throws Exception;

    public void updateList(List<AppInfo> recordList) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int deleteByHostName(Map<String, Object> map) throws Exception;

    public int deleteByDate(Map<String, Object> map) throws Exception;

    public int countByParams(Map<String, Object> params) throws Exception;

    public int updateById(AppInfo AppInfo) throws Exception;
}
