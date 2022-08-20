package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.CpuState;
import com.wgcloud.mapper.CpuStateMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:CpuStateService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: CpuStateService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class CpuStateService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<CpuState> list = cpuStateMapper.selectByParams(params);
        PageInfo<CpuState> pageInfo = new PageInfo<CpuState>(list);
        return pageInfo;
    }

    public void save(CpuState CpuState) throws Exception {
        CpuState.setId(UUIDUtil.getUUID());
        CpuState.setCreateTime(DateUtil.getNowTime());
        CpuState.setDateStr(DateUtil.getDateTimeString(CpuState.getCreateTime()));
        cpuStateMapper.save(CpuState);
    }

    public void saveRecord(List<CpuState> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        for (CpuState as : recordList) {
            as.setId(UUIDUtil.getUUID());
            as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
        }
        cpuStateMapper.insertList(recordList);
    }

    public int deleteById(String[] id) throws Exception {
        return cpuStateMapper.deleteById(id);
    }

    public CpuState selectById(String id) throws Exception {
        return cpuStateMapper.selectById(id);
    }

    public List<CpuState> selectAllByParams(Map<String, Object> params) throws Exception {
        return cpuStateMapper.selectAllByParams(params);
    }


    @Autowired
    private CpuStateMapper cpuStateMapper;


}
