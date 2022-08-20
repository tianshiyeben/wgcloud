package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.AppInfo;
import com.wgcloud.mapper.AppInfoMapper;
import com.wgcloud.mapper.AppStateMapper;
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
 * @ClassName:AppInfoService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: AppInfoService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class AppInfoService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<AppInfo> list = appInfoMapper.selectByParams(params);
        PageInfo<AppInfo> pageInfo = new PageInfo<AppInfo>(list);
        return pageInfo;
    }

    public void save(AppInfo AppInfo) throws Exception {
        AppInfo.setId(UUIDUtil.getUUID());
        AppInfo.setCreateTime(DateUtil.getNowTime());
        if (!StringUtils.isEmpty(AppInfo.getAppPid())) {
            AppInfo.setAppPid(AppInfo.getAppPid().trim());
        }
        appInfoMapper.save(AppInfo);
    }

    public int deleteByHostName(Map<String, Object> map) throws Exception {
        return appInfoMapper.deleteByHostName(map);
    }

    @Transactional
    public void saveRecord(List<AppInfo> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        for (AppInfo as : recordList) {
            as.setId(UUIDUtil.getUUID());
        }
        appInfoMapper.insertList(recordList);
    }

    public int countByParams(Map<String, Object> params) throws Exception {
        return appInfoMapper.countByParams(params);
    }

    @Transactional
    public int deleteById(String[] id) throws Exception {
        for (String AppInfoId : id) {
            appStateMapper.deleteByAppInfoId(AppInfoId);
        }
        return appInfoMapper.deleteById(id);
    }

    @Transactional
    public void updateRecord(List<AppInfo> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        appInfoMapper.updateList(recordList);
    }

    public void updateById(AppInfo AppInfo)
            throws Exception {
        appInfoMapper.updateById(AppInfo);
    }

    public AppInfo selectById(String id) throws Exception {
        return appInfoMapper.selectById(id);
    }

    public List<AppInfo> selectAllByParams(Map<String, Object> params) throws Exception {
        return appInfoMapper.selectAllByParams(params);
    }


    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private AppStateMapper appStateMapper;


}
