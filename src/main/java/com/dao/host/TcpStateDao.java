package com.dao.host;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.host.TcpState;

/**
 *
 * @ClassName:TcpStateDao.java     
 * @version V1.0 
 * @author: http://www.wgstart.com     
 * @date: 2019年1月14日
 * @Description: 查看TCP连接状态
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Repository
public interface TcpStateDao {

	
    public List<TcpState> selectAllByParams(Map<String,Object> map) throws Exception;
    
    
	public List<TcpState> selectByParams(Map<String,Object> params) throws Exception;
	
	
    public TcpState selectById(String id) throws Exception;
    
    public void save(TcpState TcpState) throws Exception;
    
    
    public void insertList(List<TcpState> recordList) throws Exception;
    
    public int deleteByAccountAndDate(Map<String,Object> map) throws Exception;
   
    public int deleteById(String[] id) throws Exception;
    

}
