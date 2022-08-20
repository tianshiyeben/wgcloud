package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.HostInfo;
import com.wgcloud.mapper.HostInfoMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:HostInfoService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 暂未用
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class HostInfoService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<HostInfo> list = hostInfoMapper.selectByParams(params);
        PageInfo<HostInfo> pageInfo = new PageInfo<HostInfo>(list);
        return pageInfo;
    }

    public void save(HostInfo HostInfo) throws Exception {
        HostInfo.setId(UUIDUtil.getUUID());
        HostInfo.setCreateTime(DateUtil.getNowTime());
        hostInfoMapper.save(HostInfo);
    }

    @Transactional
    public int deleteById(String[] id) throws Exception {
        return hostInfoMapper.deleteById(id);
    }

    @Transactional
    public int deleteByIp(String[] ip) throws Exception {
        return hostInfoMapper.deleteByIp(ip);
    }

    public void updateById(HostInfo HostInfo)
            throws Exception {
        hostInfoMapper.updateById(HostInfo);
    }

    public HostInfo selectById(String id) throws Exception {
        return hostInfoMapper.selectById(id);
    }

    public List<HostInfo> selectAllByParams(Map<String, Object> params) throws Exception {
        return hostInfoMapper.selectAllByParams(params);
    }


    @Autowired
    private HostInfoMapper hostInfoMapper;


}
