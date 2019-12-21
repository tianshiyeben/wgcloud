package com.wgcloud.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.DbInfo;
import com.wgcloud.entity.AppState;
import com.wgcloud.service.*;
import com.wgcloud.util.DateUtil;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.jdbc.ConnectionUtil;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName:DbInfoController.java
 * @version v2.1
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbInfoController.java
 * @Copyright: 2019-2020 wgcloud. All rights reserved.
 *
 */
@Controller
@RequestMapping("/dbInfo")
public class DbInfoController {
	
	 
	private static final Logger logger = LoggerFactory.getLogger(DbInfoController.class);
	
    @Resource
    private DbInfoService dbInfoService;
	@Resource
	private LogInfoService logInfoService;
	@Resource
	private ConnectionUtil connectionUtil;

	    
	/**
     * 根据条件查询列表
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="list")
	public String DbInfoList(DbInfo DbInfo, Model model,HttpServletRequest request) {
		String msg = request.getParameter("msg");
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			PageInfo pageInfo  = dbInfoService.selectByParams(params, DbInfo.getPage(), DbInfo.getPageSize());
			PageUtil.initPageNumber(pageInfo,model);
			model.addAttribute("pageUrl", "/dbInfo/list?1=1");
			model.addAttribute("page", pageInfo);
			if(pageInfo.getList().size()>0){
				model.addAttribute("dbInfo", pageInfo.getList().get(0));
				if(!StringUtils.isEmpty(msg)) {
					JdbcTemplate JdbcTemplate = connectionUtil.getJdbcTemplate((com.wgcloud.entity.DbInfo) pageInfo.getList().get(0));
					if (JdbcTemplate == null) {
						model.addAttribute("code1", "1");
						model.addAttribute("msg1", "连接数据库错误，请检查参数是否正确。请在系统信息里查看日志");
					} else {
						model.addAttribute("code1", "0");
						model.addAttribute("msg1", "连接数据库成功");
					}
				}else{
					model.addAttribute("code1", "");
					model.addAttribute("msg1", "");
				}
			}
		} catch (Exception e) {
			logger.error("查询数据源信息错误",e);
			logInfoService.save("查询数据源信息错误",e.toString(),StaticKeys.LOG_ERROR);

		}

		if(!StringUtils.isEmpty(msg)){
			model.addAttribute("code", "0");
			if(msg.equals("save")) {
				model.addAttribute("msg", "保存成功");
			}else{
				model.addAttribute("msg", "删除成功");
			}
		}else{
			model.addAttribute("code", "");
			model.addAttribute("msg", "");
		}
		return "mysql/init";
	}
	

    /**
     * 保存数据源信息
     * @param DbInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="save")
    public String saveDbInfo(DbInfo DbInfo, Model model,HttpServletRequest request) {
    	 try {
    	 	 if(StringUtils.isEmpty(DbInfo.getId())) {
				 dbInfoService.save(DbInfo);
			 }else{
				 dbInfoService.updateById(DbInfo);
			 }
		} catch (Exception e) {
			logger.error("保存数据源错误：",e);
			logInfoService.save("保存数据源错误",e.toString(),StaticKeys.LOG_ERROR);
		}
    	 return "redirect:/dbInfo/list?msg=save";
    }


    /**
     * 删除数据源
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="del")
    public String delete(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes) {
    	String errorMsg = "删除数据源信息错误：";
    	DbInfo DbInfo = new DbInfo();
		try {
			if(!StringUtils.isEmpty(request.getParameter("id"))){
				DbInfo = dbInfoService.selectById(request.getParameter("id"));
				dbInfoService.deleteById(request.getParameter("id").split(","));
			}
		} catch (Exception e) {
			logger.error(errorMsg,e);
			logInfoService.save(errorMsg,e.toString(),StaticKeys.LOG_ERROR);
		}
        return "redirect:/dbInfo/list?msg=del";
    }   
    

}
