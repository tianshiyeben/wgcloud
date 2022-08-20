package com.wgcloud.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.NetIoState;
import com.wgcloud.mapper.NetIoStateMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:NetIoStateService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: NetIoStateService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class NetIoStateService {

    public PageInfo selectByParams(Map<String, Object> params, int currPage, int pageSize) throws Exception {
        PageHelper.startPage(currPage, pageSize);
        List<NetIoState> list = netIoStateMapper.selectByParams(params);
        PageInfo<NetIoState> pageInfo = new PageInfo<NetIoState>(list);
        return pageInfo;
    }

    public void save(NetIoState NetIoState) throws Exception {
        NetIoState.setId(UUIDUtil.getUUID());
        NetIoState.setCreateTime(DateUtil.getNowTime());
        NetIoState.setDateStr(DateUtil.getDateTimeString(NetIoState.getCreateTime()));
        netIoStateMapper.save(NetIoState);
    }

    public void saveRecord(List<NetIoState> recordList) throws Exception {
        if (recordList.size() < 1) {
            return;
        }
        for (NetIoState as : recordList) {
            as.setId(UUIDUtil.getUUID());
            as.setDateStr(DateUtil.getDateTimeString(as.getCreateTime()));
        }
        netIoStateMapper.insertList(recordList);
    }

    public int deleteById(String[] id) throws Exception {
        return netIoStateMapper.deleteById(id);
    }

    public NetIoState selectById(String id) throws Exception {
        return netIoStateMapper.selectById(id);
    }

    public List<NetIoState> selectAllByParams(Map<String, Object> params) throws Exception {
        return netIoStateMapper.selectAllByParams(params);
    }


    @Autowired
    private NetIoStateMapper netIoStateMapper;


}
