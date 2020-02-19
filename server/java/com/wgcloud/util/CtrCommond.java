package com.wgcloud.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.wgcloud.common.ApplicationContextHelper;
import com.wgcloud.controller.LogInfoController;
import com.wgcloud.entity.HostInfo;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @ClassName:CtrCommond.java     
 * @version v2.3
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: CtrCommond.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
public class CtrCommond {

	private static final Logger logger = LoggerFactory.getLogger(CtrCommond.class);


	private static LogInfoService logInfoService = (LogInfoService) ApplicationContextHelper.getBean(LogInfoService.class);


	/**
	 * 获取服务器链接
	 * @param hostname
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConn(HostInfo hostInfo){
        try {
        	Connection conn = new Connection(hostInfo.getIp(),Integer.valueOf(hostInfo.getPort()));
    		//连接到主机
			conn.connect();
			//使用用户名和密码校验
	        boolean isconn = conn.authenticateWithPassword(hostInfo.getIp(), hostInfo.getPasswd());
	        if(!isconn){
	        	logger.error("用户名称或者是密码不正确");
				logInfoService.save(hostInfo.getIp(),"用户名称或者是密码不正确",StaticKeys.LOG_ERROR);
				return null;
	        }else{
	        	return conn;
	        }
		} catch (IOException e) {
			logger.error("链接服务器错误：",e);
			logInfoService.save(hostInfo.getIp(),"链接服务器错误："+e.toString(),StaticKeys.LOG_ERROR);
			return null;
		}
	}
	
	/**
	 * 远程执行命令
	 * @param ip
	 * @param user
	 * @param pwd
	 * @param cmd
	 * @return
	 */
	public static String doCommond(Connection conn,String cmd){
		String result = "";
        
        if(conn==null){
        	logger.error("请先链接服务器");
        }else{
        	Session sess = null;
        	BufferedReader stdoutReader = null;
        	try {
	            sess = conn.openSession();
	            sess.execCommand(cmd);
				stdoutReader = new BufferedReader(new InputStreamReader( new StreamGobbler(sess.getStdout())));
				while(true){
					String line = stdoutReader.readLine();
					if (line == null)
						break;
					result+=line+StaticKeys.SPLIT_BR;
				}
        	} catch (IOException e) {
            	logger.error("执行linux命令错误：",e);
				logInfoService.save(conn.getHostname(),"执行linux命令错误："+e.toString(),StaticKeys.LOG_ERROR);
            } finally {
            	//连接的Session和Connection对象都需要关闭 
            	if (stdoutReader != null) {
            		try {
						stdoutReader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                if (sess != null) {
                    sess.close();
                }
            }
        }
        
        if(result.endsWith(StaticKeys.SPLIT_BR)){
        	result =  result.substring(0, result.length()-StaticKeys.SPLIT_BR.length());
        }
        
        if(!StringUtils.isEmpty(result)){
        	if(cmd.contains("DEV")||cmd.contains("iostat")){
        		if(result.contains("</br></br>")){
        			result = result.substring(result.lastIndexOf("</br></br>")+10);
        		}
            }
        	if(cmd.contains("mpstat")){
        		if(result.contains("</br></br>")){
        			result = result.substring(result.lastIndexOf("</br></br>")+10);
        			int s = result.indexOf("</br>")+5;
        			s = result.indexOf("</br>",s);
        			result = result.substring(0,s);
        		}
            }
        }
        
        return result;
	}
	
	/**
	  * 压缩字符串 
	  * @param str
	  * @return
	  * @throws IOException
	  */
	 public static String compress(String str) throws IOException {   
	     if(StringUtils.isEmpty(str)) {   
	        return "";   
	     }   
	    ByteArrayOutputStream bos  = new ByteArrayOutputStream();  
	    Base64OutputStream b64os = new Base64OutputStream(bos); 
	    GZIPOutputStream gout = new GZIPOutputStream(b64os);  
	    gout.write(str.getBytes("UTF-8"));  
	    gout.close();  
	    b64os.close();
	    byte b1[] = bos.toByteArray();  
	    bos.close();
	    return new String(b1);
	  } 
	
	
	
}
