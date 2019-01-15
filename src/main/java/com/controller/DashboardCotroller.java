package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Page;
import com.entity.dash.DashboardView;
import com.entity.host.IntrusionInfo;
import com.entity.host.MemState;
import com.entity.host.SystemInfo;
import com.entity.user.AccountInfo;
import com.service.dash.DashboardService;
import com.service.host.CpuStateService;
import com.service.host.DeskStateService;
import com.service.host.DiskIoStateService;
import com.service.host.IntrusionInfoService;
import com.service.host.MemStateService;
import com.service.host.NetIoStateService;
import com.service.host.SysLoadStateService;
import com.service.host.SystemInfoService;
import com.service.host.TcpStateService;
import com.util.DateUtil;
import com.util.staticvar.HostKeys;
import com.util.staticvar.StaticKeys;

/**
 *
 * @ClassName:DashboardCotroller.java     
 * @version V1.0 
 * @author: wgcloud     
 * @date: 2019年1月14日
 * @Description: DashboardCotroller.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
@Controller
@RequestMapping(value="/sys/dash")
public class DashboardCotroller {
    
	private static final Logger logger = LoggerFactory.getLogger(DashboardCotroller.class);
	@Resource
	DashboardService dashboardService;
	@Resource
	CpuStateService cpuStateService;
	@Resource
	DeskStateService deskStateService;
	@Resource
	MemStateService memStateService;
	@Resource
	NetIoStateService netIoStateService;
	@Resource
	SysLoadStateService sysLoadStateService;
	@Resource
	TcpStateService tcpStateService;
	@Resource
	SystemInfoService systemInfoService;
	@Resource
	DiskIoStateService diskIoStateService;
	@Resource
	IntrusionInfoService intrusionInfoService;
	
    /**
     * 根据条件查询host列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="main")
	public String hostInfoList(Model model,HttpServletRequest request) {
		List<DashboardView> dashboardList = new ArrayList();
		AccountInfo accountInfo =(AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
		List<SystemInfo> systemInfoList = null;
		Map<String, Object> params = new HashMap<String, Object>();
		Page page = null;
		List<MemState> memStateList = null;
		boolean isSetMemDate = false;//标识是否已经设置过最后获取内存时间
		try {
			systemInfoList = systemInfoService.selectByAccountId(accountInfo.getId());
			for(int i = 0 ; i < systemInfoList.size(); i++){
				SystemInfo systemInfo = systemInfoList.get(i);
				params.put("accountId", accountInfo.getId());
				params.put("hostname", systemInfo.getHostname());
				if(StaticKeys.CONN_HOST_ERROR.equals(systemInfo.getVersion())){
					DashboardView dashboardView = new DashboardView();
					dashboardView.setHostname(systemInfo.getHostname());
					dashboardView.setVersion(systemInfo.getVersion());
					dashboardList.add(dashboardView);
					continue;
				}
				page = memStateService.selectByParams(params, 1, 1);
				memStateList = page.getObjects();
				if(memStateList==null||memStateList.size()==0){
					continue;
				}
				if(!isSetMemDate){
					model.addAttribute("date", DateUtil.getDateTimeString(memStateList.get(0).getCreateTime()));
					isSetMemDate = true;
				}
				DashboardView dashboardView = new DashboardView();
				dashboardView.setHostname(systemInfo.getHostname());
				dashboardView.setVersion(systemInfo.getVersion());
				dashboardView.setYxDays(systemInfo.getYxDays());
				dashboardView.setMemPer(Double.valueOf(memStateList.get(0).getUsePer()));
				dashboardList.add(dashboardView);
			}
			
		} catch (Exception e) {
			logger.error("主面板信息异常：",e);
		}
		model.addAttribute("list", dashboardList);
		model.addAttribute("dashboard", StaticKeys.MENU_ACTIVE);
		return "dash/dashboard";
	}
	
	
	/**
     * 根据IP查询服务器详情信息
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="detail")
	public String hostDetail(Model model,HttpServletRequest request) {
		//服务器名称
		String hostname = request.getParameter("hostname");
		String date = request.getParameter("date");
		String am = request.getParameter("am");
		AccountInfo accountInfo =(AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
		model.addAttribute("dashboard", StaticKeys.MENU_ACTIVE);
		Page page = null;
		String returnView = "dash/dashdetail";
		try {
			if(!StringUtils.isEmpty(hostname)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("accountId", accountInfo.getId());
				params.put("hostname", hostname);
				if(StringUtils.isEmpty(date)){
					date = DateUtil.getCurrentDate();
					am = dashboardService.getDefaultAm();
				}
				dashboardService.setDateParam(am, date, params);
				//组装日期查询字符串集合
				model.addAttribute("dateList", dashboardService.getDateList());
				model.addAttribute("date", date);
				model.addAttribute("am", am);
				//加载服务器系统版本信息
				SystemInfo systemInfo = systemInfoService.selectAllByParams(params).get(0);
				//磁盘空间
				model.addAttribute("systemInfo", systemInfo);
				model.addAttribute("hostname", hostname);
				page = deskStateService.selectByParams(params, 1, 1);
				if( page.getObjects().size()>0){
					model.addAttribute("deskState", page.getObjects().get(0));
				}else{
					return returnView;
				}
				//组装cpu占用情况图表字符串
				page = cpuStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("cpu", dashboardService.cpuStateChar(page.getObjects()));
				//echart图长度计算
				if(page.getObjects().size()>HostKeys.BAR_SIZE){
					model.addAttribute("chartWidth", ";width:"+page.getObjects().size()*HostKeys.BAR_WIDTH);
				}
				
				//组装内存使用情况图表字符串
				page = memStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("mem", dashboardService.memStateChar(page.getObjects()));
				//组装网络设备的吞吐率图表字符串
				page = netIoStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("netio", dashboardService.netIoStateChar(page.getObjects()));
				//组装TCP连接状态图表字符串
				page = tcpStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("tcp",  dashboardService.tcpStateChar(page.getObjects()));
				//组装系统负载状态图表字符串
				page = sysLoadStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("load", dashboardService.loadStateChar(page.getObjects()));
				//组装磁盘使用情况图表字符串
				page = diskIoStateService.selectByParams(params, 1, HostKeys.CHART_DATA_SIZE);
				model.addAttribute("diskio", dashboardService.diskIoStateChar(page.getObjects()));
			}
			
		} catch (Exception e) {
			logger.error(hostname+"--服务器详细信息异常：",e);
		}
		return returnView;
	}
	
	
	/**
     * 入侵监控列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="intrusionMain")
	public String intrusionMain(Model model,HttpServletRequest request) {
		AccountInfo accountInfo =(AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
		List<IntrusionInfo> intrusionInfoList = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			intrusionInfoList = intrusionInfoService.selectByAccountId(accountInfo.getId());
			if(intrusionInfoList.size()>0){
				model.addAttribute("date", DateUtil.getDateTimeString(intrusionInfoList.get(0).getCreateTime()));
			}
			
		} catch (Exception e) {
			logger.error("入侵检测主面板信息异常：",e);
		}
		model.addAttribute("list", intrusionInfoList);
		model.addAttribute("intrusion", StaticKeys.MENU_ACTIVE);
		return "intrusion/dashboard";
	}
	
	
	/**
     * 根据id查询服务器入侵监控详情信息
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="intrusionDetail")
	public String intrusionDetail(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		AccountInfo accountInfo =(AccountInfo) request.getSession().getAttribute(StaticKeys.LOGIN_KEY);
		model.addAttribute("intrusion", StaticKeys.MENU_ACTIVE);
		String returnView = "intrusion/dashdetail";
		try {
			IntrusionInfo info = intrusionInfoService.selectById(id);
			if(info!=null){
				model.addAttribute("info", info);
			}
		} catch (Exception e) {
			logger.error("--查看服务器入侵监控详情异常：",e);
		}
		return returnView;
	}
	
}