package com.dao.msg;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.msg.MsgInfo;

/**
 *
 * @ClassName:MsgInfoDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 信息发送记录
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface MsgInfoDao {

	
    public List<MsgInfo> selectAllByParams(Map<String,Object> map) throws Exception ;
    
	public List<MsgInfo> selectByParams(Map<String,Object> params) throws Exception;
	
    public MsgInfo selectById(String id) throws Exception;
    
    public void save(MsgInfo MsgInfo) throws Exception;
    
    public int deleteById(String[] id) throws Exception;
    
    public int deleteByDate(Map<String,Object> map) throws Exception;
   
}
