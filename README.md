## WGCLOUD

服务器集群监控，运维监控，网络吞吐率，服务器cpu监控，内存监控。



## **下载**

http://www.wgstart.com



![a.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/a.jpg)

![b.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/b.jpg)

![c.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/c.jpg)

![d.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/d.jpg)



![a.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/demo1.jpg)

![b.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/demo2.jpg)

![c.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/demo3.jpg)

![c.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/demo4.jpg)

![c.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/demo5.jpg)



## 运行环境

1.JDK1.8

2.tomcat8.5+

3.mysql5.6 或 5.7

4.CentOS6.4或以上，Red Hat6.4或以上，其他系统暂不支持

## 源码使用

在eclispe新建一个java web maven工程，使用src替换新建工程的src目录，使用pom.xml替换工程里的pom.xml，设置好jdk1.8即可。

sql文件夹里是数据库脚本，在mysql新建名为dats的数据库，然后执行dats.sql脚本初始化表。

application.properties，配置数据库链接等信息，里面有注释说明。

host.properties，配置被监控主机信息，格式为ip=端口//主机用户名//主机密码，可以配置多个，尽量不要使用root账号。

本应用会通过host.properties信息来读取被监控主机状态，所以被监控主机不需要安装WGCLOUD。



## 更新记录

- 2019.2.26

  修复了清楚历史数据的任务机制

  修复了应用监控的bug

  删除多余的注释及其他优化

  

## 微信&QQ群

QQ群：623503772

![赞赏](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/wxq.jpg)