package com.aaa.project.system.check.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.mession.service.IMessionService;
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.project.system.planDay.domain.PlanDay;
import com.aaa.project.system.planDay.service.IPlanDayService;
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.project.system.planMonthStatus.domain.PlanMonthStatus;
import com.aaa.project.system.planMonthStatus.service.IPlanMonthStatusService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.check.domain.Check;
import com.aaa.project.system.check.service.ICheckService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 月计划审核 信息操作处理
 *
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/check")
@Transactional
public class CheckController extends BaseController {
    private String prefix = "system/check";

    @Autowired
    private ICheckService checkService;

    @Autowired
    private IPlanMonthStatusService planMonthStatusService;

    @Autowired
    private IPlanMonthService planMonthService;
    @Autowired
    private IStagnationService stagnationService;
    @Autowired
    private IPlanCalendarService planCalendarService;
    @Autowired
    private IPlanDayService planDayService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IMessionService messionService;

    @RequiresPermissions("system:check:view")
    @GetMapping()
    public String check(Map<String, Object> map, @RequestParam(required = false) String sta) {
        List<City> cities = cityService.selectCityList(null);
        map.put("cities", cities);
        List<PlanMonthStatus> planMonthStatuses = planMonthStatusService.selectPlanMonthStatusList(null);
        map.put("statusList", planMonthStatuses);
        map.put("from", "check");
        return "system/planMonth/planMonth";
    }

    /**
     * 审核
     */
    @PostMapping("/sendCheck")
    @ResponseBody
    public AjaxResult sendCheck(@RequestParam(name = "monthPlanid", required = false) Integer id, Check check) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(id);
        check.setCheckMonthPlan(id);
        if (check.getCheckResult())
            planMonth.setMonthPlanStatus(3);//更改状态为审核通过
        else
            planMonth.setMonthPlanStatus(1);//更改状态为制定中
        planMonthService.updatePlanMonth(planMonth);
        int i = checkService.insertCheck(check);
        if (check.getCheckResult()) {
            PlanDay planDay = new PlanDay();
            planDay.setMonthPlanId(id);
            List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
            for (PlanDay planDay1 : planDayList) {
                //设置日计划状态为可用
                planDay1.setDayPlanStatus(1);
                planDayService.updatePlanDay(planDay1);
                //审核成功时将计划添加到任务中
                Mession mession = new Mession();
                mession.setMessionDayId(planDay1.getDayPlanId());
                if (planDay1.getDayPlanSite() != null)
                    mession.setMessionSiteId(planDay1.getDayPlanSite());
                else
                    mession.setMessionResourceId(planDay1.getDayPlanResource());
                mession.setMessionDate(planDay1.getDayPlanDate());
                mession.setMessionStatus(0);
                mession.setMessionStagnationId(planDay1.getDayPlanStagnation());
                messionService.insertMession(mession);
            }
        }
        return toAjax(i);
    }

    /**
     * 导出月计划审核列表
     */
    @RequiresPermissions("system:check:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Check check) {
        List<Check> list = checkService.selectCheckList(check);
        ExcelUtil<Check> util = new ExcelUtil<Check>(Check.class);
        return util.exportExcel(list, "check");
    }

    /**
     * 新增月计划审核
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存月计划审核
     */
    @RequiresPermissions("system:check:add")
    @Log(title = "月计划审核", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Check check) {
        return toAjax(checkService.insertCheck(check));
    }

    /**
     * 修改月计划审核
     */
    @GetMapping("/edit/{checkId}")
    public String edit(@PathVariable("checkId") Integer checkId, ModelMap mmap) {
        Check check = checkService.selectCheckById(checkId);
        mmap.put("check", check);
        return prefix + "/edit";
    }

    /**
     * 修改保存月计划审核
     */
    @RequiresPermissions("system:check:edit")
    @Log(title = "月计划审核", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Check check) {
        return toAjax(checkService.updateCheck(check));
    }

    /**
     * 删除月计划审核
     */
    @RequiresPermissions("system:check:remove")
    @Log(title = "月计划审核", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(checkService.deleteCheckByIds(ids));
    }

}
