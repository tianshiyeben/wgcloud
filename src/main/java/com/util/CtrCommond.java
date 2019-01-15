package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.lang3.StringUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:CtrCommond.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: CtrCommond.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class CtrCommond {
	

	/**
	 * 获取服务器链接
	 * @param hostname
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConn(HostInfo hostInfo){
        try {
        	Connection conn = new Connection(hostInfo.getHostname(),hostInfo.getSshPort());
    		//连接到主机
			conn.connect();
			//使用用户名和密码校验
	        boolean isconn = conn.authenticateWithPassword(hostInfo.getUsername(), hostInfo.getPassword());
	        if(!isconn){
	        	StaticKeys.badHostName.add(hostInfo.getHostname());
	        	System.out.println("用户名称或者是密码不正确");
	        }else{
	        	return conn;
	        }
		} catch (IOException e) {
			StaticKeys.badHostName.add(hostInfo.getHostname());
			System.out.println("获取服务器链接出现异常："+e);
			return null;
		}
        return null;
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
        try {
            if(conn==null){
            	System.out.println("请先链接服务器");
            }else{
                Session sess = conn.openSession();
                sess.execCommand(cmd);
                InputStream stdout = new StreamGobbler(sess.getStdout());
    			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
    			while(true){
    				String line = stdoutReader.readLine();
    				if (line == null)
    					break;
    				result+=line+StaticKeys.SPLIT_BR;
    			}
    			//连接的Session和Connection对象都需要关闭 
    			stdoutReader.close();
                sess.close();
            }
        } catch (IOException e) {
        	System.out.println("执行linux命令错误："+e);
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
