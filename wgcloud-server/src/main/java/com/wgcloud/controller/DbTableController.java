package com.wgcloud.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.wgcloud.entity.DbInfo;
import com.wgcloud.entity.DbTable;
import com.wgcloud.entity.DbTableCount;
import com.wgcloud.service.DbInfoService;
import com.wgcloud.service.DbTableCountService;
import com.wgcloud.service.DbTableService;
import com.wgcloud.service.LogInfoService;
import com.wgcloud.util.PageUtil;
import com.wgcloud.util.jdbc.RDSConnection;
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
import java.util.List;
import java.util.Map;

/**
 * @version v2.3
 * @ClassName:DbTableController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: DbTableController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping("/dbTable")
public class DbTableController {


    private static final Logger logger = LoggerFactory.getLogger(DbTableController.class);

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
    public String DbTableList(DbTable DbTable, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            PageInfo<DbTable> pageInfo = dbTableService.selectByParams(params, DbTable.getPage(), DbTable.getPageSize());
            PageUtil.initPageNumber(pageInfo, model);
            List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(params);
            for (DbTable dbTable : pageInfo.getList()) {
                for (DbInfo dbInfo : dbInfoList) {
                    if (dbInfo.getId().equals(dbTable.getDbInfoId())) {
                        dbTable.setTableName(dbInfo.getAliasName());
                    }
                }
            }
            model.addAttribute("pageUrl", "/dbTable/list?1=1");
            model.addAttribute("page", pageInfo);
        } catch (Exception e) {
            logger.error("查询数据表信息错误", e);
            logInfoService.save("查询数据表信息错误", e.toString(), StaticKeys.LOG_ERROR);

        }
        return "mysql/list";
    }


    /**
     * 保存数据源表信息
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "save")
    public String saveDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String whereVal = DbTable.getWhereVal().toLowerCase();
            if (!StringUtils.isEmpty(whereVal)) {
                String[] sqlinkeys = RDSConnection.SQL_INKEYS.split(",");
                for (String sqlinkey : sqlinkeys) {
                    if (whereVal.indexOf(sqlinkey) > -1) {
                        model.addAttribute("dbTable", DbTable);
                        List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(new HashMap<>());
                        model.addAttribute("dbInfoList", dbInfoList);
                        model.addAttribute("msg", "where语句含有sql敏感字符" + sqlinkey + "，请检查");
                        return "mysql/add";
                    }
                }
            }
            if (StringUtils.isEmpty(DbTable.getId())) {
                dbTableService.save(DbTable);
            } else {
                dbTableService.updateById(DbTable);
            }
        } catch (Exception e) {
            logger.error("保存数据表错误：", e);
            logInfoService.save("保存数据表错误", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "redirect:/dbTable/list";
    }


    /**
     * 查看数据源表信息
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "edit")
    public String editDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            DbTable dbTableInfo = new DbTable();
            if (!StringUtils.isEmpty(id)) {
                dbTableInfo = dbTableService.selectById(id);
            }
            List<DbInfo> dbInfoList = dbInfoService.selectAllByParams(new HashMap<>());
            model.addAttribute("dbInfoList", dbInfoList);
            model.addAttribute("dbTable", dbTableInfo);
        } catch (Exception e) {
            logger.error("查看数据表错误：", e);
            logInfoService.save("查看数据表错误", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "mysql/add";
    }

    /**
     * 查看数据源表图表统计信息
     *
     * @param DbTable
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "viewChart")
    public String viewChartDbTable(DbTable DbTable, Model model, HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            if (!StringUtils.isEmpty(id)) {
                DbTable dbTableInfo = dbTableService.selectById(id);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("dbTableId", id);
                List<DbTableCount> dbTableCounts = dbTableCountService.selectAllByParams(params);
                model.addAttribute("dbTableCounts", JSONUtil.parseArray(dbTableCounts));
                model.addAttribute("dbTable", dbTableInfo);
                String sql = RDSConnection.query_table_count.replace("{tableName}", dbTableInfo.getTableName()) + dbTableInfo.getWhereVal();
                model.addAttribute("sqlCount", sql);
            }
        } catch (Exception e) {
            logger.error("查看数据表图表统计错误：", e);
            logInfoService.save("查看数据表图表统计错误", e.toString(), StaticKeys.LOG_ERROR);
        }
        return "mysql/view";
    }


    /**
     * 删除数据源表
     *
     * @param id
     * @param model
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "del")
    public String delete(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMsg = "删除数据源表信息错误：";
        try {
            if (!StringUtils.isEmpty(request.getParameter("id"))) {
                DbTable dbTable = dbTableService.selectById(request.getParameter("id"));
                logInfoService.save("删除数据表：" + dbTable.getTableName(), "删除数据表：" + dbTable.getTableName(), StaticKeys.LOG_ERROR);
                dbTableService.deleteById(request.getParameter("id").split(","));
            }
        } catch (Exception e) {
            logger.error(errorMsg, e);
            logInfoService.save(errorMsg, e.toString(), StaticKeys.LOG_ERROR);
        }

        return "redirect:/dbTable/list";
    }


}
