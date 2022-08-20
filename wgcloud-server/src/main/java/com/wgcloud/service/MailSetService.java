package com.wgcloud.service;

import com.wgcloud.entity.MailSet;
import com.wgcloud.mapper.MailSetMapper;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DiskIoStateService.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DiskIoStateService.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Service
public class MailSetService {


    public void save(MailSet MailSet) throws Exception {
        MailSet.setId(UUIDUtil.getUUID());
        MailSet.setCreateTime(DateUtil.getNowTime());
        MailSet.setFromMailName(MailSet.getFromMailName().trim());
        MailSet.setFromPwd(MailSet.getFromPwd().trim());
        MailSet.setToMail(MailSet.getToMail().trim());
        MailSet.setSmtpHost(MailSet.getSmtpHost().trim());
        mailSetMapper.save(MailSet);
    }


    public int deleteById(String[] id) throws Exception {
        return mailSetMapper.deleteById(id);
    }

    public List<MailSet> selectAllByParams(Map<String, Object> params) throws Exception {
        return mailSetMapper.selectAllByParams(params);
    }

    public int updateById(MailSet MailSet) throws Exception {
        return mailSetMapper.updateById(MailSet);
    }


    @Autowired
    private MailSetMapper mailSetMapper;


}
