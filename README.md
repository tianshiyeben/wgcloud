<p align="center">
  <a  target="_blank" href="http://www.wgstart.com">
    <img src="./demo/logo.png">
  </a>
 </p>



## WGCLOUD介绍

WGCLOUD设计思想为新一代极简运维监控系统，提倡快速部署，降低运维学习难度，全自动化运行，无模板和脚本。当前仓库版本为v2.3.6。

WGCLOUD基于微服务springboot架构开发，是轻量高性能的分布式监控系统，核心采集指标包括：**主机系统信息，网络流量，CPU状态，CPU温度，内存状态，磁盘空间和IO监控，硬盘smart健康检测，系统负载，大屏可视化，ES集群状态，数据可视化监控(mysql，oracle，pgsql等)，服务接口检测，应用进程监控，网络拓扑图，端口监控，日志文件监控，docker监控，文件防篡改保护，数通设备监测，Web SSH，堡垒机，指令下发，告警信息（邮件微信钉钉短信等）推送**。[english readme](<./README_en.md>)

1.采用服务端和客户端协同工作方式，更轻量，更高效，可支持数千台主机同时在线监控。

2.server端负责接受数据，处理数据，生成图表展示。agent端默认每隔30秒(时间可调)上报指标数据。

3.支持主流服务器平台安装部署，如Linux, Windows,macOS,Unix等。

4.WGCLOUD采用springboot+bootstrap，完美实现了分布式监控系统，为反哺开源社区，二次开源。

5.v2即当前仓库为开源版，v3为商业版（免费），**生产环境建议部署商业版**，因为商业版功能、性能各方面更优秀，请点击查看[开源版和商业版区别](<./开源版和商业版区别.md>)

6.如果你觉得WGCLOUD帮助到了你，不用打赏我们，只要点击star支持下就好了。

## **网站**

<http://www.wgstart.com>

## **Github仓库(2.7k⭐)**

<https://github.com/tianshiyeben/wgcloud>

## **视频**

B站WGCLOUD相关视频地址，<https://space.bilibili.com/549621501/video>

## **源码使用**

1.使用IDEA的话（推荐），直接打开wgcloud-server和wgcloud-agent即可，JDK使用1.8

2.使用Eclipse的话，导入maven工程wgcloud-server和wgcloud-agent即可，JDK使用1.8

2.运行所需sql脚本（本项目使用mysql数据库），在sql文件夹下，在mysql数据库里创建数据库wgcloud，导入wgcloud.sql即可

3.wgcloud-agent运行所需sigar的so，dll等文件，在sigarLibs里，解压后可用，开发环境注意配置编译器的VM参数即sigarLibs路径：-Djava.library.path=E:\wgcloud-agent-v2.3\sigarLibs

## **功能截图**



![WGCLOUD监控主面板](./demo/demo2.jpg)

![WGCLOUD监控主机列表](./demo/demo3.jpg)

![WGCLOUD监控主机列表](./demo/report.jpg)

![WGCLOUD监控主机列表](./demo/dp.jpg)

![WGCLOUD监控图表](./demo/demo4.jpg)



![WGCLOUD网络拓扑图](./demo/tpdemo.jpg)

![WGCLOUD主机web ssh客户端图](./demo/ssh.jpg)

![WGCLOUD主机画像图](./demo/huaxiang.jpg)


## 运行环境

1.JDK1.8

2.mysql5.6及以上

3.支持监测Linux系列：debian、redhat、centos、ubuntu、麒麟、统信、龙芯、树莓派等。PS：linux内核版本需要2.6.23或更高，CentOS/RHEL 6.0以上

4.支持监测windows系列：windows server2003以上(不含2003)，win7，win8，win10

5.支持监测macOS系列：macOS amd64

6.支持监测Linux系列：FreeBSD，OpenBSD，solaris

## 联系

邮箱：tianshiyeben@qq.com

## 感谢

JetBrains提供的免费license