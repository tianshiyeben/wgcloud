package com.wgcloud.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wgcloud.entity.MailSet;

/**
 *
 * @ClassName:MailSetMapper.java
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: 查看磁盘IO使用情况
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Repository
public interface MailSetMapper {

	
    public List<MailSet> selectAllByParams(Map<String, Object> map) throws Exception;
    
    public void save(MailSet MailSet) throws Exception;

    public int deleteById(String[] id) throws Exception;

    public int updateById(MailSet MailSet) throws Exception;
    

}
