package com.wgcloud.util.jdbc;

import org.springframework.stereotype.Component;

/**
 * @version v2.3
 * @ClassName:RDSConnection.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: RDSConnection.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Component
public class RDSConnection {
    public static final String driver_oracle = "oracle.jdbc.driver.OracleDriver";
    public static final String url_oracle = "jdbc:oracle:thin:@{ip}:{port}:{dbname}";

    public static final String driver_postgresql = "org.postgresql.Driver";
    public static final String url_postgresql = "jdbc:postgresql://{ip}:{port}/{dbname}";

    public static final String driver_mysql = "com.mysql.jdbc.Driver";
    public static final String url_mysql = "jdbc:mysql://{ip}:{port}/{dbname}?characterEncoding=utf-8&characterSetResults=utf8&autoReconnect=true&useSSL=false";

    public static final String driver_sqlserver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String url_sqlserver = "jdbc:sqlserver://{ip}:{port};DatabaseName={dbname}";

    public static final String driver_db2 = "com.ibm.db2.jdbc.app.DB2Driver";
    public static final String url_db2 = "jdbc:db2://{ip}:{port}/{dbname}";

    public static final String SQL_INKEYS = "execute,update,delete,insert,create,drop,alter,rename,where,modify";//sql注入关键字
    public static final String MYSQL_VERSION = "select version()";//查看MySQL版本
    public static final String ORACLE_VERSION = "select * from v$version";//查看Oracle版本
    public static final String SQLSERVER_VERSION = "SELECT @@VERSION";//查看sqlserver版本
    public static final String DB2_VERSION = "SELECT SERVICE_LEVEL FROM SYSIBMADM.ENV_INST_INFO";//查看db2版本
    public static final String PROCESS_LIST = "SHOW FULL PROCESSLIST";//查看当前处于连接未关闭状态的进程列表；
    public static final String GLOBAL_VAR = "SHOW GLOBAL VARIABLES";//查看mysql设置
    public static final String MAX_CONN = "show variables like '%max_connections%'";//数据库当前设置的最大连接数
    public static final String MAX_USED_CONN = "show global status like 'Max_used_connections'";//服务器响应的最大连接数
    public static final String query_table_count = "SELECT COUNT(*) FROM {tableName} WHERE 1=1 ";
    public static final String query_table_count_pg = "SELECT COUNT(*) FROM \"{tableName}\" WHERE 1=1 ";
}