package com.wgcloud.util.jdbc;

import com.wgcloud.entity.DbInfo;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @ClassName:ConnectionUtil.java
 * @version v2.1
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: ConnectionUtil.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Component
public class ConnectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    @Resource
    private  LogInfoService logInfoService;

    private static JdbcTemplate jdbcTemplate;

      public JdbcTemplate getJdbcTemplate(DbInfo dbInfo){
        if(jdbcTemplate!=null){
            return jdbcTemplate;
        }
        String driver = "";
        String url = "";
        if("mysql".equals(dbInfo.getDbType())){
            driver = RDSConnection.driver_mysql;
            url = RDSConnection.url_mysql;
        }else{
            driver = RDSConnection.driver_oracle;
            url = RDSConnection.url_oracle;
        }
        url = url.replace("{ip}",dbInfo.getIp()).replace("{port}",dbInfo.getPort()).replace("{dbname}",dbInfo.getDbName());
        try{
            //创建连接池
            DriverManagerDataSource  dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(dbInfo.getUser());
            dataSource.setPassword(dbInfo.getPasswd());
            jdbcTemplate = new JdbcTemplate(dataSource);
            if("mysql".equals(dbInfo.getDbType())){
                jdbcTemplate.queryForRowSet(RDSConnection.MYSQL_VERSION);
            }else{
                jdbcTemplate.queryForRowSet(RDSConnection.ORACLE_VERSION);
            }

            return jdbcTemplate;
        }catch (Exception e){
            jdbcTemplate=null;
            logger.error("连接数据库错误",e);
            logInfoService.save("连接数据库错误："+dbInfo.getIp()+":"+dbInfo.getPort(),e.toString(), StaticKeys.LOG_ERROR);
        }
        return null;
    }

    public  long queryTableCount(DbInfo dbInfo,String sql) throws Exception{
        JdbcTemplate jdbcTemplate =  getJdbcTemplate( dbInfo);
        return jdbcTemplate.queryForObject(sql,Long.class);
    }

}