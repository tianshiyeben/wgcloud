<p align="center">
  <a target="_blank" href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>



## WGCLOUD-v2.3.6

[中文版README](./README.md)

Wgcloud is a distributed monitoring platform based on Java language. Its core modules include: server cluster monitoring, ES cluster monitoring, CPU monitoring, memory monitoring, data monitoring(mysql，oracle，pg), service heartbeat detection, application process management, disk IO monitoring, system load monitoring, network topology, port monitoring, log file monitoring, monitoring alarm information push.

1.It adopts the collaborative work mode of server and client, which is lighter and more efficient. After cluster optimization, it can support 5000 + host monitoring.

2.The server is responsible for receiving data, processing data and generating charts. The agent side is responsible for regularly reporting the indicator data.

3.Support the installation and deployment of mainstream server platforms, such as Linux, windows, Solaris, AIX, HP-UX, etc.

4.Wgcloud adopts the microservice springboot + bootstrap to realize the distributed monitoring system perfectly, which is open-source community and secondary open-source.

## site

<http://www.wgstart.com>

## **Source code use**

1.Because wgcloud is developed with spring boot framework, it is necessary to build new Maven projects, wgcloud server and wgcloud agent respectively

2.Then copy the source code and POM files under the wgcloud-server and wgcloud-agent to the newly-built project wgcloud server and wgcloud agent respectively, and overwrite the corresponding directory

3.Run the required SQL script (MySQL database is used in this project). Under the SQL folder, create the database wgcloud in MySQL database and import wgcloud.sql.

4.So, DLL and other files of sigar required for wgcloud agent to run are listed in sigarLibs.zip It can be used after decompression.

**5. If you feel that wgcloud has helped you, please support [www.wgstart.com](http://www.wgstart.com). With your support, open source can do better. Thank you.**

**6.If gpl3.0 cannot meet your needs, please contact me for more flexible authorization, tianshiyeben@qq.com .**

## **Demo**





![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控图表](./demo/demo4.jpg)



![WGCLOUD网络拓扑图](./demo/tpdemo.jpg)

![WGCLOUD主机画像图](./demo/huaxiang.jpg)


## Running environment

1.JDK1.8

3.mysql5.6 或 5.7



## contact

tianshiyeben@qq.com

## thank

Free license from JetBrains