<p align="center">
  <a target="_blank" href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>



## WGCLOUD-v2.3.6

[中文版README](./README.md)

Wgcloud design idea is a new generation of very simple operation and maintenance monitoring system, which advocates rapid deployment, reduces the difficulty of operation and maintenance learning, runs automatically, and has no template and script. The current warehouse version is v2.3.6.

Wgcloud is developed based on the microservice springboot architecture, and is a lightweight and high-performance distributed monitoring system. The core collection indexes include: **Host system information, network flow, CPU status, CPU temperature, memory status, disk space and IO monitoring, hard disk smart health detection, system load, large screen visualization, ES cluster status, business data monitoring (mysql, Oracle, PgSQL, etc.), service interface detection, application process monitoring, network topology, port monitoring, log file monitoring, docker monitoring, web SSH, Fortress machine, alarm information (e-mail, wechat, SMS, etc.) push** 

1. the server and client work together, which is lighter and more efficient, and can support thousands of hosts to monitor online at the same time.
2. The server side is responsible for receiving data, processing data, and generating chart display. The agent side reports the index data every 30 seconds (time adjustable) by default.
3. support the installation and deployment of mainstream server platforms, such as Linux, windows, MacOS, etc.
4. Wgcloud adopts springboot+bootstrap to realize the distributed monitoring system perfectly, which is used to feed the open source community and open source for the second time.
5. v2 means the current warehouse is open source version, V3 is commercial version (free of charge) . It is recommended to deploy commercial version in production environment. Commercial version has better function and performance. 
6. if you feel wggroup has helped you, you don't need to reward it, just click star to support it

## site

<http://www.wgstart.com>

## video

<https://space.bilibili.com/549621501/video>

## **Source code use**

1.If you use idea (recommended), you can directly open wgcloud server and wgcloud agent. JDK uses 1.8

2.If you use eclipse, you can import the Maven project wgcloud server and wgcloud agent. JDK uses 1.8

3.Run the required SQL script (MySQL database is used in this project). Under the SQL folder, create the database wgcloud in MySQL database and import wgcloud.sql.

4.So, DLL and other files of sigar required for wgcloud agent to run are listed in sigarLibs.zip It can be used after decompression.

**5. If you feel that wgcloud has helped you, please support [www.wgstart.com](http://www.wgstart.com). With your support, open source can do better. Thank you.**

## **Demo**





![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控主机列表](./demo/report.jpg)

![WGCLOUD监控图表](./demo/demo4.jpg)



![WGCLOUD网络拓扑图](./demo/tpdemo.jpg)

![WGCLOUD主机画像图](./demo/ssh.jpg)

![WGCLOUD主机画像图](./demo/huaxiang.jpg)


## Running environment

1.JDK1.8

2.Mysql5.6 and above

3.Support monitoring linux series: Debian, RedHat, CentOS, Ubuntu, Qilin, Tongxin, Loongson, raspberry pie, etc. PS: Linux kernel version 2.6.23 or higher, CentOS / RHEL 6.0 or higher

4.Support monitoring windows series: Windows Server 2003 or above (excluding 2003), win7, win8, win10

5.Support monitoring MacOS series: MacOS AMD64

6.Support monitoring of Solaris



## contact

tianshiyeben@qq.com

## thank

Free license from JetBrains