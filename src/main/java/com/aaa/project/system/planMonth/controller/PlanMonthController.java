package com.aaa.project.system.planMonth.controller;

import com.aaa.common.support.Convert;
import com.aaa.common.utils.poi.ExcelUtil;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.project.system.planDay.domain.PlanDay;
import com.aaa.project.system.planDay.service.IPlanDayService;
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.project.system.planMonthStatus.domain.PlanMonthStatus;
import com.aaa.project.system.planMonthStatus.service.IPlanMonthStatusService;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import com.aaa.project.system.resourcesCycleType.service.IResourcesCycleTypeService;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 巡检资源月计划 信息操作处理
 *
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@Transactional
@RequestMapping("/system/planMonth")
public class PlanMonthController extends BaseController {
    private String prefix = "system/planMonth";

    @Autowired
    private IPlanMonthService planMonthService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IStagnationService stagnationService;

    @Autowired
    private IPlanDayService planDayService;

    @Autowired
    private ISiteService siteService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IPlanCalendarService planCalendarService;

    @Autowired
    private IResourcesCycleTypeService resourcesCycleTypeService;

    @Autowired
    private IPlanMonthStatusService planMonthStatusService;

    /**
     * 设置驻点
     *
     * @param map
     * @return
     */
    @GetMapping("/toSetStagnation")
    public String toSetStagnation(Map<String, Object> map) {
        List<City> cities = cityService.selectCityList(null);
        map.put("cities", cities);
        return prefix + "/setStagnation";
    }

    /**
     * 跳转到表格页
     *
     * @return
     */
    @RequiresPermissions("system:planMonth:view")
    @GetMapping()
    public String planMonth(Map<String, Object> map) {
        List<City> cities = cityService.selectCityList(null);
        List<PlanMonthStatus> statusList = planMonthStatusService.selectPlanMonthStatusList(null);
        map.put("statusList", statusList);
        map.put("cities", cities);
        map.put("from", "month");
        return prefix + "/planMonth";
    }

    /**
     * 查询巡检资源月计划列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam(required = false) Integer planYear, @RequestParam(required = false) Integer planStatus, @RequestParam(required = false) Integer stagantion, PlanMonth planMonth) {
        if (stagantion != null) {
            for (int i = 1; i <= 12; i++) {
                PlanMonth planMonth1 = new PlanMonth();
                planMonth1.setMonthPlanName("基站综合代维-2019年" + i + "月巡检计划");
                if (stagantion != 0)
                    planMonth1.setMonthPlanStagnation(stagantion);
                planMonth1.setMonthPlanMonth(i);
                planMonth1.setAreaLevel(stagnationService.selectStagnationById(stagantion).getAreaLevelId());
                planMonth1.setMonthPlanYear(2019);
                planMonth1.setMonthPlanType("基站");
                planMonth1.setMonthPlanStatus(0);
                List<PlanMonth> planMonths = planMonthService.selectPlanMonthList(planMonth1);
                if (planMonths.size() == 0) {
                    planMonthService.insertPlanMonth(planMonth1);
                }
            }
            if (stagantion != 0)
                planMonth.setMonthPlanStagnation(stagantion);
        }
        if (planYear != null && planYear != 0)
            planMonth.setMonthPlanYear(planYear);
        if (planStatus != null && planStatus != 0)
            planMonth.setMonthPlanStatus(planStatus);
        startPage();
        List<PlanMonth> list = planMonthService.selectPlanMonthList(planMonth);
        for (PlanMonth plan : list) {
            plan.setMonth(plan.getMonthPlanYear() + "年" + plan.getMonthPlanMonth() + "月");
            if (plan.getMonthPlanStagnation() != null)
                plan.setStagnationName(stagnationService.selectStagnationById(plan.getMonthPlanStagnation()).getStagnationPname());
            if (plan.getMonthPlanStatus() != null) {
                plan.setStatusName(planMonthStatusService.selectPlanMonthStatusById(plan.getMonthPlanStatus()).getMonthStatusName());
            } else {
                plan.setStatusName("未制定");
            }
            PlanCalendar planCalendar = new PlanCalendar();
            planCalendar.setMonthPlanId(plan.getMonthPlanId());
            List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
            int resourcesNumMonth = 0;
            for (PlanCalendar calendar : planCalendarList) {
                int resourcesNumCal = 0;
                PlanDay planDay = new PlanDay();
                planDay.setCalendarPlanId(calendar.getCalendarId());
                List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
                for (PlanDay day : planDayList) {
                    resourcesNumCal++;
                }
                calendar.setCalendarDayResources(resourcesNumCal);
                planCalendarService.updatePlanCalendar(calendar);
                resourcesNumMonth += calendar.getCalendarDayResources();
            }
            plan.setMonthPlanResources(resourcesNumMonth);
            planMonthService.updatePlanMonth(plan);
        }
        return getDataTable(list);
    }

    /**
     * 跳转到显示日历页
     *
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/toPlanCalendar")
    @RequiresPermissions("system:planMonth:showDetail")
    public String toPlanCalendar(@RequestParam(name = "id") Integer id, @RequestParam(name = "from") String from, Map<String, Object> map) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(id);
        PlanCalendar planCalendar = new PlanCalendar();
        planCalendar.setCalendarYear(planMonth.getMonthPlanYear());
        planCalendar.setMonthPlanId(id);
        planCalendar.setCalendarMonth(planMonth.getMonthPlanMonth());
        List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
        String dateStr = "";
        for (int i = 0; i < planCalendarList.size(); i++) {
            //向页面传有日计划的日期
            PlanDay planDay = new PlanDay();
            planDay.setCalendarPlanId(planCalendarList.get(i).getCalendarId());
            planDay.setMonthPlanId(planMonth.getMonthPlanId());
            planDay.setDayPlanDate(planCalendarList.get(i).getCalendarDate());
            List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
            if (planDayList.size() != 0) {
                Date date = planCalendarList.get(i).getCalendarDate();
                DateFormat df1 = DateFormat.getDateInstance();
                dateStr += df1.format(date) + ",";
            } else {
                //删除没有日计划的对应日历记录
                planCalendarService.deletePlanCalendarByIds(planCalendarList.get(i).getCalendarId() + "");
            }
        }
        if (dateStr.length() != 0)
            dateStr = dateStr.substring(0, dateStr.length() - 1);
        String sta = "";
        if (planMonth.getMonthPlanStatus() != null) {
            if (planMonth.getMonthPlanStatus() == 1)
                sta = "ing";
            else if (planMonth.getMonthPlanStatus() == 2)
                sta = "wait";
            else if(planMonth.getMonthPlanStatus() == 3)
                sta = "checked";
            else if(planMonth.getMonthPlanStatus() == 0)
                sta = "not";
        }
        map.put("planMonth", planMonth);
        map.put("dateStr", dateStr);
        map.put("from", from);
        map.put("sta", sta);
        return "system/planCalendar/planCalendar";
    }

    /**
     * 提交审核
     */
    @PostMapping("/sendToCheck")
    @ResponseBody
    public AjaxResult sendToCheck(@RequestParam("id") Integer planMonthId) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        planMonth.setMonthPlanStatus(2);
        planMonthService.updatePlanMonth(planMonth);
        return toAjax(1);
    }

    /**
     * 沿用上期计划
     *
     * @param planMonthId
     * @param map
     * @return
     */
    @PostMapping("/useLastMonth/{id}")
    @RequiresPermissions("system:planMonth:showDetail")
    @ResponseBody
    public AjaxResult useLastMonth(@RequestParam(name = "id") Integer planMonthId, Map<String, Object> map) {
        int i = 0;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        PlanCalendar planCalendar = new PlanCalendar();
        planCalendar.setMonthPlanId(planMonthId - 1);
        List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
        for (PlanCalendar planCalendar1 : planCalendarList) {
            PlanCalendar planCalendar2 = (PlanCalendar) planCalendar1.clone();
            planCalendar2.setMonthPlanId(planMonthId);
            planCalendar2.setCalendarMonth(planCalendar1.getCalendarMonth() + 1);
            calendar.setTime(planCalendar1.getCalendarDate());
            calendar.add(Calendar.MONTH, 1);
            planCalendar2.setCalendarId(null);
            planCalendar2.setCalendarDate(calendar.getTime());
            i += planCalendarService.insertPlanCalendar(planCalendar2);
        }
        //获取上个月的所有日计划列表
        PlanDay planDay = new PlanDay();
        planDay.setMonthPlanId(planMonthId - 1);
        List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
        for (PlanDay planDay1 : planDayList) {
            PlanDay planDay2 = (PlanDay) planDay1.clone();
            Date dayPlan1Date = planDay1.getDayPlanDate();
            calendar.setTime(dayPlan1Date);
            calendar.add(Calendar.MONTH, 1);
            planDay2.setDayPlanDate(calendar.getTime());
            planDay2.setMonthPlanId(planMonthId);
            planDay2.setDayPlanId(null);
            if (planDay1.getDayPlanSite() != null) {
                Site site = siteService.selectSiteById(planDay1.getDayPlanSite());
                site.setSiteLastDate(calendar.getTime());
            } else {
                Resource resource = resourceService.selectResourceById(planDay1.getDayPlanResource());
                resource.setResourceLastDate(calendar.getTime());
            }
            PlanCalendar planCalendar2 = new PlanCalendar();
            planCalendar2.setMonthPlanId(planMonthId);
            planCalendar2.setCalendarDate(calendar.getTime());
            Integer planCalendar2Id = planCalendarService.selectPlanCalendarList(planCalendar2).get(0).getCalendarId();
            planDay2.setCalendarPlanId(planCalendar2Id);
            i += planDayService.insertPlanDay(planDay2);
        }
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        planMonth.setMonthPlanStatus(1);
        planMonthService.updatePlanMonth(planMonth);
        return toAjax(i);
    }

    /**
     * 跳转到显示日计划页
     *
     * @param date
     * @param planMonthId
     * @param map
     * @return
     */
    @GetMapping("/toDayDetile")
    public String toDayDetile(@RequestParam(required = false) String date, @RequestParam(required = false) String from, @RequestParam(required = false) String sta, @RequestParam(required = false) Integer planMonthId, Map<String, Object> map) {
        map.put("date", date);
        map.put("from", from);
        map.put("sta", sta);
        map.put("planMonthId", planMonthId);
        return "system/planDay/planDay";
    }

    /**
     * 巡检资源日计划列表
     */
    @PostMapping("/dayPlanList")
    @ResponseBody
    public TableDataInfo list(@RequestParam(required = false) Integer planMonthId, @RequestParam(required = false) Date date, PlanDay planDay) {
        planDay.setDayPlanDate(date);
        planDay.setMonthPlanId(planMonthId);
        startPage();
        List<PlanDay> list = planDayService.selectPlanDayList(planDay);
        for (PlanDay day : list) {
            if (day.getDayPlanSite() != null)
                day.setResourcesName(siteService.selectSiteById(day.getDayPlanSite()).getSiteName());
            else
                day.setResourcesName(resourceService.selectResourceById(day.getDayPlanResource()).getResourceName());

        }
        return getDataTable(list);
    }

    /**
     * 跳转到站点列表
     *
     * @param date
     * @param planMonthId
     * @param map
     * @return
     */
    @GetMapping("/toSiteList")
    public String toSiteList(@RequestParam(required = false) String date, @RequestParam(required = false) Integer planMonthId, Map<String, Object> map) {
        map.put("date", date.substring(0, 10));
        map.put("planMonthId", planMonthId);
        return prefix + "/planAddSite";
    }

    /**
     * 跳转到资源点列表
     *
     * @param date
     * @param planMonthId
     * @param map
     * @return
     */
    @GetMapping("/toResourceList")
    public String toResourceList(@RequestParam(required = false) String date, @RequestParam(required = false) Integer planMonthId, Map<String, Object> map) {
        map.put("date", date.substring(0, 10));
        map.put("planMonthId", planMonthId);
        return prefix + "/planAddReaource";
    }

    /**
     * 查询巡检资源列表
     */
    @PostMapping("/siteList")
    @ResponseBody
    public TableDataInfo siteList(@RequestParam(required = false) String dateStr, @RequestParam(required = false) Integer planMonthId) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startPage();
        List<Site> list = siteService.selectSiteHasDate(date, planMonth.getMonthPlanStagnation());
        for (Site site : list) {
            if (site.getSiteCycle() != null) {
                Integer siteCycleId = site.getSiteCycle();
                ResourcesCycleType resourcesCycleType = resourcesCycleTypeService.selectResourcesCycleTypeById(siteCycleId);
                site.setCycleName(resourcesCycleType.getResourcesCycleName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 查询巡检资源列表
     */
    @PostMapping("/resourceList")
    @ResponseBody
    public TableDataInfo resourceList(@RequestParam(required = false) String dateStr, @RequestParam(required = false) Integer planMonthId) {
        System.out.println(3);
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startPage();
        List<Resource> list = resourceService.selectResourceHasDate(date, planMonth.getMonthPlanStagnation());
        for (Resource resource : list) {
            if (resource.getResourceCycle() != null) {
                Integer resourceCycleId = resource.getResourceCycle();
                ResourcesCycleType resourcesCycleType = resourcesCycleTypeService.selectResourcesCycleTypeById(resourceCycleId);
                resource.setCycleName(resourcesCycleType.getResourcesCycleName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 分配资源到日计划
     *
     * @param ids
     * @return
     */
    @PostMapping("/setResources")
    @RequiresPermissions("system:resource:distribute")
    @ResponseBody
    @Transactional
    public AjaxResult setSite(@RequestParam(required = false) String ids, @RequestParam(required = false) String type, @RequestParam(required = false) String dateStr, @RequestParam(required = false) Integer planMonthId) {
        int i = 0;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        //更新月计划状态为制定中
        planMonth.setMonthPlanStatus(1);
        planMonthService.updatePlanMonth(planMonth);
        PlanCalendar planCalendar = new PlanCalendar();
        planCalendar.setCalendarDate(date);
        planCalendar.setCalendarDay(day);
        planCalendar.setCalendarMonth(month);
        planCalendar.setCalendarStatus(1);
        planCalendar.setCalendarYear(year);
        planCalendar.setMonthPlanId(planMonthId);
        List<PlanCalendar> planCalendars = planCalendarService.selectPlanCalendarList(planCalendar);
        if (planCalendars.size() == 0) {
            //插入一条计划日历的记录
            planCalendarService.insertPlanCalendar(planCalendar);
        }
        planCalendar = planCalendarService.selectPlanCalendarList(planCalendar).get(0);
        for (String id : ids.split(",")) {
            PlanDay planDay = new PlanDay();
            planDay.setDayPlanDate(date);
            planDay.setCalendarPlanId(planCalendar.getCalendarId());
            planDay.setMonthPlanId(planCalendar.getMonthPlanId());
            planDay.setDayPlanName("基站综合代维-" + year + "年" + month + "月" + day + "日巡检计划");
            planDay.setDayPlanStatus(0);
            if ("site".equals(type)) {
                //如果点击的是站点，则添加站点
                Site site = siteService.selectSiteById(Long.valueOf(id).longValue());
                planDay.setDayPlanSite(site.getSiteId());
                planDay.setDayPlanStagnation(site.getSiteStagantionCompany());
                planDay.setDayPlanType("站点");
                site.setSitePlanDay(day);
                site.setSiteLastDate(date);
                Integer addMonth = resourcesCycleTypeService.selectResourcesCycleTypeById(site.getSiteCycle()).getResourcesCycleMonth();
                cal.add(Calendar.MONTH, addMonth);
                site.setSiteLastedDate(cal.getTime());
                siteService.updateSite(site);
            } else {
                //如果点击的是资源点，则添加资源点
                Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
                planDay.setDayPlanResource(resource.getResourceId());
                planDay.setDayPlanStagnation(resource.getResourceStagantionCompany());
                planDay.setDayPlanType("资源点");
                resource.setResourcePlanDay(day);
                resource.setResourceLastDate(date);
                Integer addMonth = resourcesCycleTypeService.selectResourcesCycleTypeById(resource.getResourceCycle()).getResourcesCycleMonth();
                cal.add(Calendar.MONTH, addMonth);
                resource.setResourceLastedDate(cal.getTime());
                resourceService.updateResource(resource);
            }
            //一个资源就是一条记录
            i += planDayService.insertPlanDay(planDay);
        }
        planCalendarService.updatePlanCalendar(planCalendar);
        return toAjax(i);
    }

    /**
     * 释放日计划资源
     */
    @PostMapping("/releaseResource")
    @ResponseBody
    public AjaxResult releaseResource(String ids) {
        Integer[] intArray = Convert.toIntArray(",", ids);
        for (Integer planDayId : intArray) {
            PlanDay planDay = planDayService.selectPlanDayById(planDayId);
            if (planDay.getDayPlanSite() != null) {
                Site site = siteService.selectSiteById(planDay.getDayPlanSite());
                siteService.relaseResources(site);
            } else {
                Resource resource = resourceService.selectResourceById(planDay.getDayPlanResource());
                resourceService.relaseResources(resource);
            }
        }
        int i = planDayService.deletePlanDayByIds(ids);
        return toAjax(i);
    }

    /**
     * 导出巡检资源月计划列表
     */
    @RequiresPermissions("system:planMonth:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanMonth planMonth) {
        List<PlanMonth> list = planMonthService.selectPlanMonthList(planMonth);
        ExcelUtil<PlanMonth> util = new ExcelUtil<PlanMonth>(PlanMonth.class);
        return util.exportExcel(list, "planMonth");
    }

    /**
     * 新增巡检资源月计划
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存巡检资源月计划
     */
    @RequiresPermissions("system:planMonth:add")
    @Log(title = "巡检资源月计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlanMonth planMonth) {
        return toAjax(planMonthService.insertPlanMonth(planMonth));
    }

    /**
     * 修改巡检资源月计划
     */
    @GetMapping("/edit/{monthPlanId}")
    public String edit(@PathVariable("monthPlanId") Integer monthPlanId, ModelMap mmap) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(monthPlanId);
        mmap.put("planMonth", planMonth);
        return prefix + "/edit";
    }

    /**
     * 修改保存巡检资源月计划
     */
    @RequiresPermissions("system:planMonth:edit")
    @Log(title = "巡检资源月计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlanMonth planMonth) {
        return toAjax(planMonthService.updatePlanMonth(planMonth));
    }

    /**
     * 删除巡检资源月计划
     */
    @RequiresPermissions("system:planMonth:remove")
    @Log(title = "巡检资源月计划", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(planMonthService.deletePlanMonthByIds(ids));
    }

}
