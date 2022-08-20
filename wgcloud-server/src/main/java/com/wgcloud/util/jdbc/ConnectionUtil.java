package com.wgcloud.util.jdbc;

import com.wgcloud.entity.DbInfo;
import com.wgcloud.service.DbInfoService;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.staticvar.StaticKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version v2.3
 * @ClassName:ConnectionUtil.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: ConnectionUtil.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Component
public class ConnectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    @Resource
    private LogInfoService logInfoService;
    @Resource
    private DbInfoService dbInfoService;

    public JdbcTemplate getJdbcTemplate(DbInfo dbInfo) throws Exception {
        JdbcTemplate jdbcTemplate = null;
        String driver = "";
        String url = "";
        if ("mysql".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_mysql;
            url = RDSConnection.url_mysql;
        } else if ("postgresql".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_postgresql;
            url = RDSConnection.url_postgresql;
        } else if ("sqlserver".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_sqlserver;
            url = RDSConnection.url_sqlserver;
        } else if ("db2".equals(dbInfo.getDbType())) {
            driver = RDSConnection.driver_db2;
            url = RDSConnection.url_db2;
        } else {
            driver = RDSConnection.driver_oracle;
            url = RDSConnection.url_oracle;
        }
        url = url.replace("{ip}", dbInfo.getIp()).replace("{port}", dbInfo.getPort()).replace("{dbname}", dbInfo.getDbName());
        try {
            //创建连接池
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(dbInfo.getUser());
            dataSource.setPassword(dbInfo.getPasswd());
            jdbcTemplate = new JdbcTemplate(dataSource);
            if ("mysql".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.MYSQL_VERSION);
            } else if ("postgresql".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.MYSQL_VERSION);
            } else if ("sqlserver".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.SQLSERVER_VERSION);
            } else if ("db2".equals(dbInfo.getDbType())) {
                jdbcTemplate.queryForRowSet(RDSConnection.DB2_VERSION);
            } else {
                jdbcTemplate.queryForRowSet(RDSConnection.ORACLE_VERSION);
            }
            dbInfo.setDbState("1");
            dbInfoService.updateById(dbInfo);
            return jdbcTemplate;
        } catch (Exception e) {
            jdbcTemplate = null;
            logger.error("连接数据库错误", e);
            logInfoService.save("连接数据库错误：" + dbInfo.getAliasName(), "IP：" + dbInfo.getIp() + "，端口：" + dbInfo.getPort() + "，数据库别名："
                    + dbInfo.getAliasName() + "，错误信息：" + e.toString(), StaticKeys.LOG_ERROR);
            dbInfo.setDbState("2");
            dbInfoService.updateById(dbInfo);
        }
        return null;
    }

    public long queryTableCount(DbInfo dbInfo, String sql) {
        try {
            JdbcTemplate jdbcTemplate = getJdbcTemplate(dbInfo);
            if (null == jdbcTemplate) {
                return 0;
            }
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (Exception e) {
            logger.error("统计数据表错误：", e);
            logInfoService.save("统计数据表错误：" + dbInfo.getAliasName(), "IP：" + dbInfo.getIp() + "，端口：" + dbInfo.getPort() + "，数据库别名："
                    + dbInfo.getAliasName() + "，错误信息：" + e.toString(), StaticKeys.LOG_ERROR);
            return 0;
        }
    }

}