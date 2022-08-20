package com.wgcloud.controller;

import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.DbTableCount;
import com.wgcloud.service.DbInfoService;
import com.wgcloud.service.DbTableCountService;
import com.wgcloud.service.DbTableService;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.staticvar.StaticKeys;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableCountCountController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbTableCountCountController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/dbTableCount")
public class DbTableCountController {


    private static final Logger logger = LoggerFactory.getLogger(DbTableCountController.class);

    @Resource
    private DbInfoService dbInfoService;
    @Resource
    private DbTableService dbTableService;
    @Resource
    private DbTableCountService dbTableCountService;
    @Resource
    private LogInfoService logInfoService;


    /**
     * 根据条件查询列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    public String dbTableCountList(DbTableCount dbTableCount, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PageInfo pageInfo = dbTableCountService.selectByParams(params, dbTableCount.getPage(), dbTableCount.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            model.addAttribute("pageUrl", "/dbTableCount/list?1=1");
            model.addAttribute("page", pageInfo);
        } catch (Exception e) {
            logger.error("查询数据源表统计信息错误", e);
            logInfoService.save("查询数据源表统计信息错误", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "dbTableCount/list";
    }


    /**
     * 保存数据源表统计信息
     *
     * @param DbTableCount
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveDbTableCount(DbTableCount DbTableCount, Model model, HttpServletRequest request) {
        try {
            dbTableCountService.save(DbTableCount);
        } catch (Exception e) {
            logger.error("保存数据源表统计错误：", e);
            logInfoService.save("保存数据源表统计错误", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dbTableCount/list";
    }


    /**
     * 删除数据源表统计
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "删除数据源表统计信息错误：";
        DbTableCount DbTableCount = new DbTableCount();
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                DbTableCount = dbTableCountService.selectById(request.getParameter("id"));
                dbTableCountService.deleteById(request.getParameter("id").split(","));
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(errorMsg, e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/dbTableCount/list";
    }


}
