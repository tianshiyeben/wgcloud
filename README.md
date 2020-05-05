<p align="center">
  <a href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>


## WGCLOUD-v2.3.6

[中文版README](./README_zh.md)

Wgcloud is a distributed monitoring platform based on Java language. Its core modules include: server cluster monitoring, ES cluster monitoring, CPU monitoring, memory monitoring, data monitoring(mysql，oracle，pg), service heartbeat detection, application process management, disk IO monitoring, system load monitoring, monitoring alarm information push.

1.Adopt the collaborative work mode of server and client, which is lighter, more efficient, and can support hundreds of servers online monitoring.

2.The server is responsible for receiving data, processing data and generating charts. The agent side is responsible for regularly reporting the indicator data.

3.Support the installation and deployment of mainstream server platforms, such as Linux, windows, Solaris, AIX, HP-UX, etc.

4.Wgcloud adopts the microservice springboot + bootstrap to realize the distributed monitoring system perfectly, which is open-source community and secondary open-source.

## **Source code use**

1.Because wgcloud is developed with spring boot framework, it is necessary to build new Maven projects, wgcloud server and wgcloud agent respectively

2.Then copy the source code and POM files under the wgcloud-server and wgcloud-agent to the newly-built project wgcloud server and wgcloud agent respectively, and overwrite the corresponding directory

3.Run the required SQL script (MySQL database is used in this project). Under the SQL folder, create the database wgcloud in MySQL database and import wgcloud.sql.

4.In the installation package (**Note: not in the warehouse**), click the download link below to download the installation package. After downloading, unzip it. In the wgcloud agent / lib folder.

## download

<http://www.wgstart.com>

## **Demo**





![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控图表](./demo/demo4.jpg)




## Running environment

1.JDK1.8

3.mysql5.6 或 5.7



## contact

tianshiyeben@qq.com



## Support open source

If you think this application project is helpful to you, you can appreciate the author. Thousands of rivers and mountains are always in love!

![c.jpg](./demo/wxzf.jpg)
