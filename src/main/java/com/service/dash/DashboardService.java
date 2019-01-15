package com.service.dash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entity.app.AppState;
import com.entity.host.CpuState;
import com.entity.host.DiskIoState;
import com.entity.host.MemState;
import com.entity.host.NetIoState;
import com.entity.host.SysLoadState;
import com.entity.host.TcpState;
import com.util.DateUtil;
import com.util.staticvar.HostKeys;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:DashboardService.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: 主面板信息管理
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Service
public class DashboardService {
	
	//不显示网格
	private String LINE_FALSE = "splitLine:{show:false},";

	//x轴标题加粗
	private String XAXIS_FONT = "textStyle:{fontWeight:'bolder',color:'#000000'}";
	
	//x轴倾斜度
	private String x_rotate = "45";
	
	//x轴grid样式设置,设置x轴高度，避免字符串太长显示不完整
	private String x_grid = "grid:{y2:90},";
	
	/**
	 * 组装cpu占用情况图表字符串
	 * @param cpuList
	 * @return
	 */
	public String cpuStateChar(List<CpuState> cpuList){
		Collections.reverse(cpuList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'CPU占用情况(Average：ALL)',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['%usr','%sys','%idle','%iowait']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(CpuState stat:cpuList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}%'}}],");
		strBuffer.append("series:[");
		//usr
		strBuffer.append("{name:'%usr',type:'line',itemStyle: {normal: {color: '#FF0000'}},data:[");
		for(CpuState stat:cpuList){
			strBuffer.append(stat.getUser()+",");
		}
		strBuffer.append("]},");
		//sys
		strBuffer.append("{name:'%sys',type:'line',itemStyle: {normal: {color: '#2DB82D'}},data:[");
		for(CpuState stat:cpuList){
			strBuffer.append(stat.getSys()+",");
		}
		strBuffer.append("]},");
		//idle
		strBuffer.append("{name:'%idle',type:'line',itemStyle: {normal: {color: '#823B93'}},data:[");
		for(CpuState stat:cpuList){
			strBuffer.append(stat.getIdle()+",");
		}
		strBuffer.append("]},");
		//iowait
		strBuffer.append("{name:'%iowait',type:'line',data:[");
		for(CpuState stat:cpuList){
			strBuffer.append(stat.getIowait()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	
	/**
	 * 组装内存使用情况图表字符串
	 * @param memList
	 * @return
	 */
	public String memStateChar(List<MemState> memList){
		Collections.reverse(memList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'内存已使用百分比',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['内存已使用百分比']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(MemState stat:memList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:'auto',rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}%'}}],");
		strBuffer.append("series:[");
		strBuffer.append("{name:'内存已使用百分比',type:'line', itemStyle: {normal: {areaStyle: {type: 'default'}}},data:[");
		for(MemState stat:memList){
			strBuffer.append(stat.getUsePer()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	//以下注释为内存柱状图显示
	/**
	 * 组装内存使用情况图表字符串
	 * @param memList
	 * @return
	 *//*
	public String memStateChar(List<MemState> memList){
		Collections.reverse(memList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'内存已使用百分比',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['已使用百分比']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:true,data:[");
		for(MemState stat:memList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:30,margin:2}}],");
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}%'}}],");
		strBuffer.append("series:[");
		strBuffer.append("{name:'已使用百分比',type:'bar',");
		if(memList.size()<=StaticKeys.BAR_SIZE){
			strBuffer.append("barWidth: 32,");
		}
		strBuffer.append("itemStyle: {normal: {color: '#BA55D3'}},data:[");
//		strBuffer.append("{name:'已使用百分比',type:'bar',barWidth: 35,itemStyle: {normal: {");
//		strBuffer.append("color: function(params) { var colorList = ['#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',");
//		strBuffer.append("'#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD','#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'");
//		strBuffer.append(",'#AE8463','#1BCA63','#FAD120','#F3A10B','#50C0DD','#B7504B','#C1E279','#F4E101','#F0005A','#22C0C0'");
//		strBuffer.append("];return colorList[params.dataIndex%25]}}},data:[");
		for(MemState stat:memList){
			strBuffer.append(stat.getUsePer()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}*/
	
	/**
	 * 组装网络设备的吞吐率图表字符串
	 * @param netIoList
	 * @return
	 */
	public String netIoStateChar(List<NetIoState> netIoList){
		Collections.reverse(netIoList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'网络设备吞吐率(eth*网卡吞吐率之和)',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['rxpck/s','txpck/s','rxkB/s','txkB/s']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(NetIoState stat:netIoList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}'}}],");
		strBuffer.append("series:[");
		//rxpck/s
		strBuffer.append("{name:'rxpck/s',type:'line',itemStyle: {normal:{color:'#823B93'}},data:[");
		for(NetIoState stat:netIoList){
			strBuffer.append(stat.getRxpck()+",");
		}
		strBuffer.append("]},");
		//txpck/s
		strBuffer.append("{name:'txpck/s',type:'line',itemStyle: {normal:{color:'#32CD32'}},data:[");
		for(NetIoState stat:netIoList){
			strBuffer.append(stat.getTxpck()+",");
		}
		strBuffer.append("]},");
		//rxkB/s
		strBuffer.append("{name:'rxkB/s',type:'line',itemStyle: {normal:{color:'#FF7F50'}},data:[");
		for(NetIoState stat:netIoList){
			strBuffer.append(stat.getRxbyt()+",");
		}
		strBuffer.append("]},");
		//txkB/s
		strBuffer.append("{name:'txkB/s',type:'line',itemStyle: {normal:{color:'#DA70D6'}},data:[");
		for(NetIoState stat:netIoList){
			strBuffer.append(stat.getTxbyt()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	
	/**
	 * 磁盘IO使用状况图表字符串
	 * @param diskIoList
	 * @return
	 */
	public String diskIoStateChar(List<DiskIoState> diskIoList){
		Collections.reverse(diskIoList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'磁盘IO使用情况(Average)',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['r/s','w/s','rkB/s','wkB/s','await','avgqu-sz','%util']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}'}}],");
		strBuffer.append("series:[");
		//r/s
		strBuffer.append("{name:'r/s',type:'bar',itemStyle: {normal:{color:'#6495ED'}},stack: '总量',data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getRs()+",");
		}
		strBuffer.append("]},");
		//w/s
		strBuffer.append("{name:'w/s',type:'bar',itemStyle: {normal:{color:'#32CD32'}},stack: '总量',");
		if(diskIoList.size()<=HostKeys.BAR_SIZE){
			strBuffer.append("barWidth: 28,data:[");
		}
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getWs()+",");
		}
		strBuffer.append("]},");
		//rkB/s
		strBuffer.append("{name:'rkB/s',type:'bar',itemStyle: {normal:{color:'#FF7F50'}},stack: '总量',data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getRkBS()+",");
		}
		strBuffer.append("]},");
		//wkB/s
		strBuffer.append("{name:'wkB/s',type:'bar',itemStyle: {normal:{color:'#DA70D6'}},stack: '总量',data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getWkBS()+",");
		}
		strBuffer.append("]},");
		//await
		strBuffer.append("{name:'await',type:'line',itemStyle: {normal:{color:'#C1232B'}},data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getAwait()+",");
		}
		strBuffer.append("]},");
		//avgqu-sz
		strBuffer.append("{name:'avgqu-sz',type:'line',itemStyle: {normal:{color:'#27727B'}},data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getAvgquSz()+",");
		}
		strBuffer.append("]},");
		//%util
		strBuffer.append("{name:'%util',type:'line',itemStyle: {normal:{color:'#D87A80'}},data:[");
		for(DiskIoState stat:diskIoList){
			strBuffer.append(stat.getUtil()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	
	
	/**
	 * 组装TCP连接状态图表字符串
	 * @param netIoList
	 * @return
	 */
	public String tcpStateChar(List<TcpState> tcpList){
		Collections.reverse(tcpList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'TCP连接状态(Average)',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['active/s','passive/s']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(TcpState stat:tcpList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}'}}],");
		strBuffer.append("series:[");
		//active/s
		strBuffer.append("{name:'active/s',type:'line',itemStyle: {normal: {areaStyle: {type: 'default'}}},data:[");
		for(TcpState stat:tcpList){
			strBuffer.append(stat.getActive()+",");
		}
		strBuffer.append("]},");
		//passive/s
		strBuffer.append("{name:'passive/s',type:'line',itemStyle: {normal: {areaStyle: {type: 'default'}}},data:[");
		for(TcpState stat:tcpList){
			strBuffer.append(stat.getPassive()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	

	/**
	 * 组装系统负载状态图表字符串
	 * @param netIoList
	 * @return
	 */
	public String loadStateChar(List<SysLoadState> loadList){
		//splitLine:{show:false},去掉网格线
		Collections.reverse(loadList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'系统负载状态',subtext:''},tooltip:{show:true,trigger:'item'},");
		strBuffer.append("legend: {data:['1分钟','5分钟','15分钟']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',data:[");
		for(SysLoadState stat:loadList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}'}}],");
		strBuffer.append("series:[");
		//1分钟
		strBuffer.append("{name:'1分钟',type:'bar',stack: '总量',");
		strBuffer.append("itemStyle: {normal: {color: '#6495ED'}},data:[");
		for(SysLoadState stat:loadList){
			strBuffer.append(stat.getOneLoad()+",");
		}
		strBuffer.append("]},");
		//5分钟
		strBuffer.append("{name:'5分钟',type:'bar',stack: '总量',");
		strBuffer.append("itemStyle: {normal: {color: '#32CD32'}},data:[");
		for(SysLoadState stat:loadList){
			strBuffer.append(stat.getFiveLoad()+",");
		}
		strBuffer.append("]},");
		//15分钟
		strBuffer.append("{name:'15分钟',type:'bar',stack: '总量',");
		if(loadList.size()<=HostKeys.BAR_SIZE){
			strBuffer.append("barWidth: 28,");
		}
		strBuffer.append("itemStyle: {normal: {color: '#FF7F50'}},data:[");
		for(SysLoadState stat:loadList){
			strBuffer.append(stat.getFifteenLoad()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	/**
	 * 组装应用状态图表字符串
	 * @param netIoList
	 * @return
	 */
	public String appStateChar(List<AppState> appStateList){
		Collections.reverse(appStateList);
		StringBuffer strBuffer = new StringBuffer("{title:{text:'应用进程历史状态',subtext:''},tooltip : {trigger: 'axis'},");
		strBuffer.append("legend: {data:['%CPU','%MEM']},");
		strBuffer.append("xAxis:[{"+LINE_FALSE+"type:'category',boundaryGap:false,data:[");
		for(AppState stat:appStateList){
			strBuffer.append("'"+stat.getDateStr()+"',");
		}
		strBuffer.append("],");
		strBuffer.append("axisLabel:{interval:0,rotate:"+x_rotate+",margin:2}}],");
		strBuffer.append(x_grid);
		strBuffer.append("yAxis:[{"+LINE_FALSE+"type:'value',axisLabel:{formatter:'{value}'}}],");
		strBuffer.append("series:[");
		//%CPU
		strBuffer.append("{name:'%CPU',type:'line',itemStyle: {normal: {areaStyle:{type:'default'},color:'#6EC71E'}},data:[");
		for(AppState stat:appStateList){
			strBuffer.append(stat.getCpuPer()+",");
		}
		strBuffer.append("]},");
		//%MEM
		strBuffer.append("{name:'%MEM',type:'line',itemStyle: {normal: {areaStyle:{type:'default'},color:'#4FA8F9'}},data:[");
		for(AppState stat:appStateList){
			strBuffer.append(stat.getMemPer()+",");
		}
		strBuffer.append("]}]}");
		return strBuffer.toString();
	}
	
	/**
	 * 获取从今天开始，往前倒数天数的日期集合
	 * @param days
	 * @return
	 */
	public List<String>  getDateList(){
		int days = 7;
		List<String> dateList = new ArrayList<String>();
		String nowTime = DateUtil.getCurrentDateTime();
		String sevenDayBefore = DateUtil.getDateBefore(nowTime, days);
		for(int i = 0; i< days; i++){
			sevenDayBefore = DateUtil.getDateBefore(nowTime, i);
			dateList.add(sevenDayBefore.substring(0, 10));
		}
		return dateList;
	}
	
	/**
	 * 获取当前时间对应的查询参数值
	 * @return
	 */
	public static String getDefaultAm(){
		int hour = DateUtil.getHour(new Date());
		if(hour>0&&hour<6){
			return "am1";
		}
		if(hour>=6&&hour<12){
			return "am2";
		}
		if(hour>=12&&hour<18){
			return "am3";
		}
		if(hour>=18&&hour<=23){
			return "am4";
		}
		return "am2";
	}
	
	/**
	 * 查看详细信息监控时候，组装日期查询条件
	 * @param am
	 * @param params
	 * @param accountInfo
	 */
	public void setDateParam(String am,String date,Map<String, Object> params){
		//测试帐号不加时间条件
		if("am1".equals(am)){
			params.put(StaticKeys.SEARCH_START_TIME, date+" 00:00:00");
			params.put(StaticKeys.SEARCH_END_TIME, date+" 05:59:59");
		}if("am2".equals(am)){
			params.put(StaticKeys.SEARCH_START_TIME, date+" 06:00:00");
			params.put(StaticKeys.SEARCH_END_TIME, date+" 11:59:59");
		}if("am3".equals(am)){
			params.put(StaticKeys.SEARCH_START_TIME, date+" 12:00:00");
			params.put(StaticKeys.SEARCH_END_TIME, date+" 17:59:59");
		}if("am4".equals(am)){
			params.put(StaticKeys.SEARCH_START_TIME, date+" 18:00:00");
			params.put(StaticKeys.SEARCH_END_TIME, date+" 23:59:59");
		}
	}
	
}
