<p align="center">
  <a target="_blank" href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>



## WGCLOUD-v2.3.6

[中文版README](./README.md)

Wgcloud is a high-performance and high concurrent distributed monitoring system based on lightweight springboot architecture. Its core modules include:  host monitoring, ES cluster management, CPU monitoring, CPU temperature monitoring, large screen visualization display board, memory monitoring, data monitoring (mysql, Oracle, PG, etc.), service heartbeat detection, application process management, disk space and IO monitoring, hard disk smart health detection, System load monitoring, network topology, port monitoring, log file monitoring, docker monitoring, integrated with web version SSH client tools, monitoring alarm information (can be integrated with wechat pinning SMS, etc.) push .

1.It adopts the collaborative work mode of server and client, which is lighter and more efficient. After cluster optimization, it can support 5000 + host monitoring.

2.The server is responsible for receiving data, processing data and generating charts. The agent side is responsible for regularly reporting the indicator data.

3.Support the installation and deployment of mainstream server platforms, such as Linux, windows, Solaris, AIX, HP-UX, etc.

4.Wgcloud adopts the microservice springboot + bootstrap to realize the distributed monitoring system perfectly, which is open-source community and secondary open-source.

## site

<http://www.wgstart.com>

## video

<https://space.bilibili.com/549621501/video>

## **Source code use**

1.Because wgcloud is developed with spring boot framework, it is necessary to build new Maven projects, wgcloud server and wgcloud agent respectively

2.Then copy the source code and POM files under the wgcloud-server and wgcloud-agent to the newly-built project wgcloud server and wgcloud agent respectively, and overwrite the corresponding directory

3.Run the required SQL script (MySQL database is used in this project). Under the SQL folder, create the database wgcloud in MySQL database and import wgcloud.sql.

4.So, DLL and other files of sigar required for wgcloud agent to run are listed in sigarLibs.zip It can be used after decompression.

**5. If you feel that wgcloud has helped you, please support [www.wgstart.com](http://www.wgstart.com). With your support, open source can do better. Thank you.**

## **Demo**





![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控图表](./demo/demo4.jpg)



![WGCLOUD网络拓扑图](./demo/tpdemo.jpg)

![WGCLOUD主机画像图](./demo/ssh.jpg)

![WGCLOUD主机画像图](./demo/huaxiang.jpg)


## Running environment

1.JDK1.8

3.mysql5.6 或 5.7



## contact

tianshiyeben@qq.com

## thank

Free license from JetBrains