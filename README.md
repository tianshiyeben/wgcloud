## WGCLOUD

linux性能监测工具，运维监控，网络吞吐率，服务器cpu监控，内存监控。[English README](https://github.com/tianshiyeben/wgcloud/blob/master/README_en.md)

WAR包下载请拉到最底部。

![a.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/a.jpg)

![b.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/b.jpg)

![c.jpg](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/c.jpg)

## 运行环境

1.JDK1.8

2.tomcat8.5+

3.mysql5.6+

4.CentOS6.4或以上，Red Hat6.4或以上，其他系统暂不支持

5.检测被监控主机是否已经安装sysstat，如下信息说明已经安装，如果没有请通过yum install sysstat安装

```
[root@localhost ~]# mpstat
Linux 3.10.0-514.el7.x86_64 (localhost.localdomain) 	2019年01月10日 	_x86_64_	(4 CPU)

13时40分26秒  CPU    %usr   %nice    %sys %iowait    %irq   %soft  %steal  %guest  %gnice   %idle
13时40分26秒  all    0.60    0.00    0.19    0.16    0.00    0.03    0.00    0.00    0.00   99.02
```

## 源码使用

在eclispe新建一个java web maven工程，使用src替换新建工程的src目录，使用pom.xml替换工程里的pom.xml，设置好jdk1.8即可。

sql文件夹里是数据库脚本，在mysql新建名为dats的数据库，然后执行dats.sql脚本初始化表。

application.properties，配置数据库链接等信息，里面有注释说明。

host.properties，配置被监控主机信息，格式为ip=端口//主机用户名//主机密码，可以配置多个，尽量不要使用root账号。

本应用会通过host.properties信息来读取被监控主机状态，所以被监控主机不需要安装WGCLOUD。

## 监控指标

```
%usr：用户态的CPU时间（%）
%sys：核心时间（%））
%idle：空闲时间（%）
%iowait：IO等待时间（%）
rxpck/s：每秒钟接收的数据包
txpck/s：每秒钟发送的数据包
rxkB/s：每秒钟接收的kb
txkB/s：每秒钟发送的kb
active/s：每秒本地发起的TCP连接数，既通过connect调用创建的TCP连接
passive/s：每秒远程发起的TCP连接数，即通过accept调用创建的TCP连接
%CPU：进程占用CPU的使用率
%MEM：进程使用的物理内存和总内存的百分比
r/s, w/s, rkB/s, wkB/s：分别表示每秒读写次数和每秒读写数据量（千字节）。读写量过大，可能会引起性能问题。
await：IO操作的平均等待时间，单位是毫秒。这是应用程序在和磁盘交互时，需要消耗的时间，包括IO等待和实际操作的耗时。
如果这个数值过大，可能是硬件设备遇到了瓶颈或者出现故障。
avgqu-sz：向设备发出的请求平均数量。如果这个数值大于1，可能是硬件设备已经饱和（部分前端硬件设备支持并行写入）。
%util：设备利用率。这个数值表示设备的繁忙程度，经验值是如果超过60，可能会影响IO性能（可以参照IO操作平均等待时间）。
如果到达100%，说明硬件设备已经饱和。
```



## 连接失败的服务器信息怎么删除

服务器每3天会清空连接失败的服务器信息，所以3天后你就看不到连接失败的服务器信息了。目前暂不支持手动操作，全部由系统自动处理。

同时系统会定时清除30天前的监控数据信息。

## 告警提示规则

系统内存使用率阈值99%，超过发送邮件，24小时不会重复发。

进程内存监控报警百分比阈值99%，超过发送邮件，24小时不会重复发。

进程CPU使用率监控报警百分比阈值99%，超过发送邮件，24小时不会重复发。

若不想接受告警通知，可在applications.properties里关闭。



## 更新记录

- 2019.2.26

  修复了清楚历史数据的任务机制

  修复了应用监控的bug

  删除多余的注释及其他优化


## 访问

http://localhost:9000/wgcloud

登陆账号/密码：admin/111111



## 联系

QQ群：623503772



## 下载

Tomcat下可运行的war包请加qq群后，在群文件下载



## 赞赏

开源不易，如果你觉得本项目帮助到你了，请给作者一点点鼓励。



![赞赏](https://raw.githubusercontent.com/tianshiyeben/wgcloud/master/demo/zanshang.jpg)



