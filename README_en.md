<p align="center">
  <a target="_blank" href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>



## WGCLOUD

[中文版README](./README.md)

Wgcloud design idea is a new generation of very simple operation and maintenance monitoring system, which advocates rapid deployment, reduces the difficulty of operation and maintenance learning, runs automatically, and has no template and script. 

**The current warehouse version is v2 3.7 for the latest secondary development, please pull the master branch.**

Wgcloud is developed based on the microservice springboot architecture, and is a lightweight and high-performance distributed monitoring system. The core collection indexes include: **CPU utilization, CPU temperature, memory utilization, disk capacity, disk IO, smart health status of hard disk, system load, number of connections, network card traffic, hardware system information, etc. Support the monitoring of process applications, files, ports, logs, docker containers, databases, data tables and other resources on the server. Support monitoring service interface API, data communication equipment (such as switch, router, printer), etc. Automatically generate network topology map, large screen visualization, web SSH (Fortress machine), statistical analysis chart, command issuance, batch execution, alarm information push (such as email, nail, wechat, SMS, etc.)** 

1. V2.3.7 abandons the sigar method of v2.3.6 to obtain host indicators, and v2.3.7 uses popular oshi components to collect host indicators
2. the server and client work together, which is lighter and more efficient, and can support thousands of hosts to monitor online at the same time.
3. The server side is responsible for receiving data, processing data, and generating chart display. The agent side reports the index data every 30 seconds (time adjustable) by default.
4. support the installation and deployment of mainstream server platforms, such as Linux, windows, MacOS, etc.
5. Wgcloud adopts springboot+bootstrap to realize the distributed monitoring system perfectly, which is used to feed the open source community and open source for the second time.
6. if you feel wggroup has helped you, you don't need to reward it, just click star to support it
7. About sharing, the original intention of open source is to share learning. If you can, please add [wgcloud] on your blog and website (if any)[WGCLOUD](http://www.wgstart.com)Link or write a post to share with others to help wgcloud learn and progress. Finally, if you like, you can send us your company name by email, and we will show it to the [thank you] column of wgcloud website.

## site

<http://www.wgstart.com>

## video

<https://space.bilibili.com/549621501/video>

## **Source code use**

1.If you use idea (recommended), you can directly open wgcloud server and wgcloud agent. JDK uses 1.8

2.If you use eclipse, you can import the Maven project wgcloud server and wgcloud agent. JDK uses 1.8

3.Run the required SQL script (MySQL database is used in this project). Under the SQL folder, create the database wgcloud in MySQL database and import wgcloud.sql.

**4. If you feel that wgcloud has helped you, please support [www.wgstart.com](http://www.wgstart.com). With your support, open source can do better. Thank you.**

## **Demo**





![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控主机磁盘图表](./demo/demo9.jpg)

![WGCLOUD监控主机告警图表](./demo/report.jpg)

![WGCLOUD监控主机告警图表](./demo/dp.jpg)

![WGCLOUD监控主机告警图表](./demo/dapingNew.jpg)

![WGCLOUD监控主机状态趋势图](./demo/demo4.jpg)



![WGCLOUD网络拓扑图](./demo/tpdemo.jpg)

![WGCLOUD主机画像图](./demo/ssh.jpg)

![WGCLOUD主机画像图](./demo/huaxiang.jpg)

## Example of communication diagram (HTTP protocol)

![WGCLOUD通信图示例](./demo/tongxin.jpg)

## Running environment

1.JDK1.8、JDK11

2.Mysql5.6 and above、MariaDB、PostgreSQL

3.Support operating system platform

> Support monitoring linux series: Debian, RedHat, CentOS, Ubuntu, Kirin, Tongxin, godson, raspberry pie, etc. 
>
> support monitoring windows series: Windows Server 2008 R2 2012 2016 2019, Windows 7, Windows 8, windows 10 
>
> support monitoring UNIX series: Solaris, FreeBSD, OpenBSD 
>
> support monitoring Mac OS series: Mac OS AMD64



## EMAIL

tianshiyeben@qq.com

## Thanks

Free license from JetBrains