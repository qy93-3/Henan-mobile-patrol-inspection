package com.aaa.project.system.danger.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.dangerLevel.domain.DangerLevel;
import com.aaa.project.system.dangerLevel.service.IDangerLevelService;
import com.aaa.project.system.dangerStatus.domain.DangerStatus;
import com.aaa.project.system.dangerStatus.service.IDangerStatusService;
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.mession.service.IMessionService;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.danger.domain.Danger;
import com.aaa.project.system.danger.service.IDangerService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpSession;

/**
 * 巡检资源隐患 信息操作处理
 *
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/danger")
public class DangerController extends BaseController {
    private String prefix = "system/danger";

    @Autowired
    private IDangerService dangerService;
    @Autowired
    private IMessionService messionService;
    @Autowired
    private ISiteService siteService;
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IDangerLevelService dangerLevelService;
    @Autowired
    private IDangerStatusService dangerStatusService;
    @Autowired
    private IRoutingPeopleService routingPeopleService;
    @Autowired
    private IRoutingProjectService routingProjectService;

    @RequiresPermissions("system:danger:view")
    @GetMapping()
    public String danger(Map<String, Object> map) {
        map.put("projects", routingProjectService.selectRoutingProjectList(null));
        map.put("levels", dangerLevelService.selectDangerLevelList(null));
        map.put("statuses", dangerStatusService.selectDangerStatusList(null));
        return prefix + "/danger";
    }

    /**
     * 查询巡检资源隐患列表
     */
    @RequiresPermissions("system:danger:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam(required = false) Integer projectId,
                              @RequestParam(required = false) Integer level, @RequestParam(required = false) Integer status) {
        Danger danger = new Danger();
        if (projectId != null && projectId != 0)
            danger.setRoutingProjectId(projectId);
        if (level != null && level != 0)
            danger.setDangerLevel(level);
        if (status != null && status != 10)
            danger.setDangerStatus(status);
        startPage();
        List<Danger> list = dangerService.selectDangerList(danger);
        for (Danger danger1 : list) {
            //设置表格内显示的资源名称
            if (danger1.getDangerResourceId() == null)
                danger1.setResourcesName(siteService.selectSiteById(danger1.getDangerSiteId()).getSiteName());
            else
                danger1.setResourcesName(resourceService.selectResourceById(danger1.getDangerResourceId()).getResourceName());
            //设置表格内显示的隐患项目名称
            danger1.setProjectName(routingProjectService.selectRoutingProjectById(danger1.getRoutingProjectId()).getRoutingProjectName());
            //设置表格内显示的隐患状态名称
            danger1.setStatusName(dangerStatusService.selectDangerStatusById(danger1.getDangerStatus()).getDangerHandleName());
            //设置表格内显示的隐患级别名称
            danger1.setLevelName(dangerLevelService.selectDangerLevelById(danger1.getDangerLevel()).getDangerLevelName());
        }
        return getDataTable(list);
    }


    /**
     * 导出巡检资源隐患列表
     */
    @RequiresPermissions("system:danger:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Danger danger) {
        List<Danger> list = dangerService.selectDangerList(danger);
        ExcelUtil<Danger> util = new ExcelUtil<Danger>(Danger.class);
        return util.exportExcel(list, "danger");
    }

    /**
     * 新增巡检资源隐患
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存巡检资源隐患
     */
    @RequiresPermissions("system:danger:add")
    @Log(title = "巡检资源隐患", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Danger danger) {
        return toAjax(dangerService.insertDanger(danger));
    }

    /**
     * 修改巡检资源隐患
     */
    @GetMapping("/edit/{dangerId}")
    public String edit(@PathVariable("dangerId") Integer dangerId, ModelMap mmap) {
        Danger danger = dangerService.selectDangerById(dangerId);
        mmap.put("danger", danger);
        return prefix + "/edit";
    }

    /**
     * 修改保存巡检资源隐患
     */
    @RequiresPermissions("system:danger:edit")
    @Log(title = "巡检资源隐患", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Danger danger) {
        return toAjax(dangerService.updateDanger(danger));
    }

    /**
     * 删除巡检资源隐患
     */
    @RequiresPermissions("system:danger:remove")
    @Log(title = "巡检资源隐患", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dangerService.deleteDangerByIds(ids));
    }

    /**
     * @return java.util.List<com.aaa.project.system.danger.domain.Danger>
     * @Author ryy
     * @Description 获取巡检人员对应的提交的隐患信息
     * @Date 2019/4/23 16:23
     * @Param []
     **/
    @RequestMapping("/empDangerList")
    @ResponseBody
    public List<Danger> empDangerList(@RequestParam String routingPeople) {
        if (routingPeople != null || routingPeople != "") {
            JSONObject parse = (JSONObject) JSON.parse(routingPeople);
            Integer routingId = Integer.parseInt(parse.getString("routingId"));
            List<Danger> dangerList = dangerService.selectDangerByPersonId(routingId);
            for (Danger danger : dangerList) {
                //获取巡检任务信息
                Mession mession = messionService.selectMessionById(danger.getMessionId());
                danger.setTmession(mession);
                //获取隐患站点信息
                Site site = siteService.selectSiteById(danger.getDangerSiteId());
                danger.setTsite(site);
                //获取隐患资源点信息
                Resource resource = resourceService.selectResourceById(danger.getDangerResourceId());
                danger.setResource(resource);
                //获取隐患等级信息
                DangerLevel dangerLevel = dangerLevelService.selectDangerLevelById(danger.getDangerLevel());
                danger.setTdangerLevel(dangerLevel);
                //获取隐患状态信息
                DangerStatus dangerStatus = dangerStatusService.selectDangerStatusById(danger.getDangerStatus());
                danger.setTdangerStatus(dangerStatus);
                //获取隐患提交人信息
                RoutingPeople tRoutingPeople = routingPeopleService.selectRoutingPeopleById(routingId);
                danger.setTroutingPeople(tRoutingPeople);

            }
            return dangerList;
        }

        return null;
    }

}
