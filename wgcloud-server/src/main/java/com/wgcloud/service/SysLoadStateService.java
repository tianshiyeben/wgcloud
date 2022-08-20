package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.SysLoadState;
import com.wgcloud.mapper.SysLoadStateMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:SysLoadStateService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: SysLoadStateService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class SysLoadStateService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<SysLoadState> list = sysLoadStateMapper.selectByParams(params);
        PageInfo<SysLoadState> pageInfo = new PageInfo<SysLoadState>(list);
        return pageInfo;
    }

    public void save(SysLoadState SysLoadState) throws Exception {
        SysLoadState.setId(UUIDUtil.getUUID());
        SysLoadState.setCreateTime(DateUtil.getNowTime());
        SysLoadState.setDateStr(DateUtil.getDateTimeString(SysLoadState.getCreateTime()));
        sysLoadStateMapper.save(SysLoadState);
    }

    public void saveRecord(List<SysLoadState> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        for (SysLoadState as : recordList) {
            as.setId(UUIDUtil.getUUID());
            as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
        }
        sysLoadStateMapper.insertList(recordList);
    }

    public int deleteById(String[] id) throws Exception {
        return sysLoadStateMapper.deleteById(id);
    }

    public SysLoadState selectById(String id) throws Exception {
        return sysLoadStateMapper.selectById(id);
    }

    public List<SysLoadState> selectAllByParams(Map<String, Object> params) throws Exception {
        return sysLoadStateMapper.selectAllByParams(params);
    }


    @Autowired
    private SysLoadStateMapper sysLoadStateMapper;


}
