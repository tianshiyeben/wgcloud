<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HOST主机入侵检测信息详情</title>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/hierapolis/assets/images/favicon.png"/>


<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/bootstrap/css/ionicons.min.css">
<link rel="stylesheet" href="${ctx}/static/AdminLTE/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${ctx}/static/AdminLTE/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet" href="${ctx}/static/AdminLTE/plugins/morris/morris.css">

<link href="${ctx}/static/hierapolis/assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/hierapolis/assets/stylesheets/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/common/code.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/static/echarts-2.2.7/doc/example/www/js/echarts.js"></script>
    
</head>
<body class='main page'>
  <!-- Navbar -->
  <jsp:include page="/common/header.jsp"></jsp:include>
  <div id='wrapper'>
    <!-- Sidebar -->
     <jsp:include page="/common/menu.jsp"></jsp:include>
    <!-- Tools -->
    <section id='tools'>
      <ul class='breadcrumb' id='breadcrumb'>
        <li class='title'>HOST主机入侵检测信息详情(127.0.0.1)</li>
      </ul>
    </section>
    <!-- Content -->
    <div id='content'>
        <div class='panel panel-default grid'>
          <div class='panel-heading'>
            <i class='icon-time icon-large'></i>
            	检测时间：2017-05-26 13:52:36
           	<div class='panel-tools'>
             <div class='btn-group'>
               <a class='btn' onclick="history.back()">返回</a>
           </div>
          </div>
          </div>
          
		<div class="content body">
			<section id="download">
		  	<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">RPC开放状态<br># rpcinfo -p<br>program vers proto   port  service</br>100000    4   tcp    111  portmapper</br>100000    3   tcp    111  portmapper</br>100000    2   tcp    111  portmapper</br>100000    4   udp    111  portmapper</br>100000    3   udp    111  portmapper</br>100000    2   udp    111  portmapper</br>100024    1   udp  42799  status</br>100024    1   tcp  35206  status
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查网络是否是promisc模式，正常网卡不该在promisc模式<br># ip link | grep promisc<br>
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查passwd文件修改时间<br># ls -l /etc/passwd<br>-rw-r--r--. 1 root root 1375 May 25 14:15 /etc/passwd
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查系统内核模块<br># lsmod<br>Module                  Size  Used by</br>iptable_filter          2793  0 </br>ip_tables              17831  1 iptable_filter</br>autofs4                26513  3 </br>ipt_REJECT              2351  0 </br>ip6t_REJECT             4628  2 </br>nf_conntrack_ipv6       8748  2 </br>nf_defrag_ipv6         11182  1 nf_conntrack_ipv6</br>xt_state                1492  2 </br>nf_conntrack           79758  2 nf_conntrack_ipv6,xt_state</br>ip6table_filter         2889  1 </br>ip6_tables             18732  1 ip6table_filter</br>ipv6                  317340  61 ip6t_REJECT,nf_conntrack_ipv6,nf_defrag_ipv6</br>microcode             112685  0 </br>ppdev                   8537  0 </br>vmware_balloon          7199  0 </br>parport_pc             22690  0 </br>parport                36209  2 ppdev,parport_pc</br>vmxnet3                46421  0 </br>i2c_piix4              12608  0 </br>i2c_core               31084  1 i2c_piix4</br>sg                     29350  0 </br>shpchp                 32778  0 </br>ext4                  374902  2 </br>jbd2                   93427  1 ext4</br>mbcache                 8193  1 ext4</br>sr_mod                 15177  0 </br>cdrom                  39085  1 sr_mod</br>sd_mod                 39069  3 </br>crc_t10dif              1541  1 sd_mod</br>pata_acpi               3701  0 </br>ata_generic             3837  0 </br>ata_piix               24601  0 </br>mptspi                 16603  2 </br>mptscsih               36700  1 mptspi</br>mptbase                93615  2 mptspi,mptscsih</br>scsi_transport_spi     25863  1 mptspi</br>dm_mirror              14384  0 </br>dm_region_hash         12085  1 dm_mirror</br>dm_log                  9930  2 dm_mirror,dm_region_hash</br>dm_mod                 84209  8 dm_mirror,dm_log
		    </code></pre>
		    
		    <pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">检查系统计划任务<br># cat /etc/crontab<br>SHELL=/bin/bash</br>PATH=/sbin:/bin:/usr/sbin:/usr/bin</br>MAILTO=root</br>HOME=/</br></br># For details see man 4 crontabs</br></br># Example of job definition:</br># .---------------- minute (0 - 59)</br># |  .------------- hour (0 - 23)</br># |  |  .---------- day of month (1 - 31)</br># |  |  |  .------- month (1 - 12) OR jan,feb,mar,apr ...</br># |  |  |  |  .---- day of week (0 - 6) (Sunday=0 or 7) OR sun,mon,tue,wed,thu,fri,sat</br># |  |  |  |  |</br># *  *  *  *  * user-name command to be executed</br>
		    </code></pre>
			</section>
		
		</div>	    
    
			      
        </div>
      </div>
  </div>
  <!-- Footer -->
  <jsp:include page="/common/footer.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-2.2.3.min.js"></script>
</body>
</html>
