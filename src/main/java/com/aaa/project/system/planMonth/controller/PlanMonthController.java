package com.aaa.project.system.planMonth.controller;

import com.aaa.common.support.Convert;
import com.aaa.common.utils.poi.ExcelUtil;
import com.aaa.common.utils.security.ShiroUtils;
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
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     *
     * @param planYear   查询条件：月计划年份
     * @param planStatus 查询条件：月计划状态
     * @param stagantion 查询条件：驻点编号
     * @param planMonth  查询条件：月计划
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam(required = false) Integer planYear, @RequestParam(required = false) Integer planStatus, @RequestParam(required = false) Integer stagantion, PlanMonth planMonth) {
        if (stagantion != null && stagantion != 0) {
            for (int i = 1; i <= 12; i++) {
                PlanMonth planMonth1 = new PlanMonth();
                //若选择了驻点，则设置为月计划的查询条件
                if (stagantion != 0)
                    planMonth1.setMonthPlanStagnation(stagantion);
                //对于1-12月遍历设置月计划的月份
                planMonth1.setMonthPlanMonth(i);
                //设置月计划的年份为2019
                planMonth1.setMonthPlanYear(2019);
                //根据查询条件查询月计划
                List<PlanMonth> planMonths = planMonthService.selectPlanMonthList(planMonth1);
                planMonth1.setMonthPlanName(stagnationService.selectStagnationById(stagantion).getStagnationPname() + "-基站综合代维-2019年" + i + "月巡检计划");
                //设置月计划的地域级别为对应驻点的地域级别
                planMonth1.setAreaLevel(stagnationService.selectStagnationById(stagantion).getAreaLevelId());
                //设置月计划类型为‘基站’
                planMonth1.setMonthPlanType("基站");
                if (planMonths.size() == 0) {
                    //设置月计划状态为未制定
                    planMonth1.setMonthPlanStatus(0);
                    //若所选驻点无月计划，则新建每个月的月计划
                    planMonthService.insertPlanMonth(planMonth1);
                }
            }
            //若选择了驻点，则设置为月计划的查询条件
            planMonth.setMonthPlanStagnation(stagantion);
        }
        if (planYear != null && planYear != 0)
            //若选择了年份，就设置为月计划的查询条件
            planMonth.setMonthPlanYear(planYear);
        if (planStatus != null && planStatus != 0)
            //若选择了状态，就设置为月计划的查询条件
            planMonth.setMonthPlanStatus(planStatus);
        startPage();
        List<PlanMonth> list = planMonthService.selectPlanMonthList(planMonth);
        for (PlanMonth plan : list) {
            //设置表格中的字段，根据年和月来拼接月计划的月份
            plan.setMonth(plan.getMonthPlanYear() + "年" + plan.getMonthPlanMonth() + "月");
            if (plan.getMonthPlanStagnation() != null)
                //若月计划的驻点不为空，则设置月计划的驻点名为驻点的名称
                plan.setStagnationName(stagnationService.selectStagnationById(plan.getMonthPlanStagnation()).getStagnationPname());
            if (plan.getMonthPlanStatus() != null) {
                //若月计划的状态不为空，则设置月计划的状态名为状态的名称
                plan.setStatusName(planMonthStatusService.selectPlanMonthStatusById(plan.getMonthPlanStatus()).getMonthStatusName());
            } else {
                //若月计划状态为空，则设置为'未制定'
                plan.setStatusName("未制定");
            }
            PlanCalendar planCalendar = new PlanCalendar();
            planCalendar.setMonthPlanId(plan.getMonthPlanId());
            //根据月计划的ID来获取对应的日历计划
            List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
            int resourcesNumMonth = 0;
            for (PlanCalendar calendar : planCalendarList) {
                int resourcesNumCal = 0;
                PlanDay planDay = new PlanDay();
                planDay.setCalendarPlanId(calendar.getCalendarId());
                //根据当前日历计划ID来获取当天的所有日计划
                List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
                for (PlanDay day : planDayList) {
                    //获取当天所有的日计划资源数之和
                    resourcesNumCal++;
                }
                //设置日历计划每一天的资源数为获取的资源数
                calendar.setCalendarDayResources(resourcesNumCal);
                planCalendarService.updatePlanCalendar(calendar);
            }
            for (PlanCalendar calendar : planCalendarService.selectPlanCalendarList(planCalendar)) {
                //获取当前月计划下所有日历计划的资源数之和
                resourcesNumMonth += calendar.getCalendarDayResources();
            }
            //设置月计划的资源数
            plan.setMonthPlanResources(resourcesNumMonth);
            planMonthService.updatePlanMonth(plan);
            List<PlanMonth> planMonthList = planMonthService.selectPlanMonthList(planMonth);
        }
        for (PlanMonth month : list) {
            Stagnation stagnation = new Stagnation();
            stagnation.setStagnationId(month.getMonthPlanStagnation());
            //根据月计划ID来获取对应的驻点列表
            List<Stagnation> stagnationList = stagnationService.selectStagnationList(stagnation);
            for (Stagnation stagnation1 : stagnationList) {
                PlanMonth planMonth1 = new PlanMonth();
                planMonth1.setMonthPlanStagnation(stagnation1.getStagnationId());
                //根据驻点来获取驻点所对应的12个月的月计划
                List<PlanMonth> planMonthList = planMonthService.selectPlanMonthList(planMonth1);
                Integer stagnationResourceNum = 0;
                for (PlanMonth planMonth2 : planMonthList) {
                    if (planMonth2.getMonthPlanResources() != null)
                        //获取12个月的月计划资源数之和
                        stagnationResourceNum += planMonth2.getMonthPlanResources();
                }
                //设置驻点的应巡检资源数
                stagnation1.setResourcesNumber(stagnationResourceNum);
                stagnationService.updateStagnation(stagnation1);
            }
        }
        return getDataTable(list);
    }

    /**
     * 跳转到显示日历页
     *
     * @param id   月计划编号
     * @param from 请求的来源，审核或者计划
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
        //根据所选月计划编号，来显示相应的日历计划
        List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
        String dateStr = "";
        for (int i = 0; i < planCalendarList.size(); i++) {
            //向页面传有日计划的日期
            PlanDay planDay = new PlanDay();
            planDay.setCalendarPlanId(planCalendarList.get(i).getCalendarId());
            planDay.setMonthPlanId(planMonth.getMonthPlanId());
            planDay.setDayPlanDate(planCalendarList.get(i).getCalendarDate());
            //遍历日历计划，根据每一条日历计划来获取所对应的所有日计划
            List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
            if (planDayList.size() != 0) {
                //将日计划的日期拼接成字符串
                Date date = planCalendarList.get(i).getCalendarDate();
                DateFormat df1 = DateFormat.getDateInstance();
                dateStr += df1.format(date) + ",";
            } else {
                //删除没有日计划的对应日历记录
                planCalendarService.deletePlanCalendarByIds(planCalendarList.get(i).getCalendarId() + "");
            }
        }
        if (dateStr.length() != 0)
            //去掉最后一位的','
            dateStr = dateStr.substring(0, dateStr.length() - 1);
        String sta = "";
        if (planMonth.getMonthPlanStatus() != null) {
            //设置一个String变量来存储月计划的状态
            if (planMonth.getMonthPlanStatus() == 1)
                sta = "ing";//状态为制定中
            else if (planMonth.getMonthPlanStatus() == 2)
                sta = "wait";//状态为等待审核
            else if (planMonth.getMonthPlanStatus() == 3)
                sta = "checked";//状态为已审核
            else if (planMonth.getMonthPlanStatus() == 0)
                sta = "not";//状态为未制定
        }
        map.put("planMonth", planMonth);
        map.put("dateStr", dateStr);
        map.put("from", from);
        map.put("sta", sta);
        return "system/planCalendar/planCalendar";
    }

    /**
     * 提交审核
     *
     * @param planMonthId 月计划ID
     * @return
     */
    @PostMapping("/sendToCheck")
    @ResponseBody
    public AjaxResult sendToCheck(@RequestParam("id") Integer planMonthId) {
        //获取提交审核的月计划
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        //将月计划状态设置为审核中
        planMonth.setMonthPlanStatus(2);
        int i = planMonthService.updatePlanMonth(planMonth);
        return toAjax(i);
    }

    /**
     * 沿用上期计划
     *
     * @param planMonthId 当前月计划ID
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
        //获取上个月月计划下所有的日历计划列表
        List<PlanCalendar> planCalendarList = planCalendarService.selectPlanCalendarList(planCalendar);
        for (PlanCalendar planCalendar1 : planCalendarList) {
            //克隆日历计划
            PlanCalendar planCalendar2 = (PlanCalendar) planCalendar1.clone();
            //设置要新增的日历计划的月计划ID为当前月计划ID
            planCalendar2.setMonthPlanId(planMonthId);
            //设置要新增的日历计划的月份为上个月日历计划月份+1
            planCalendar2.setCalendarMonth(planCalendar1.getCalendarMonth() + 1);
            //获取上个月日历计划列表的日期，并将月份+1
            calendar.setTime(planCalendar1.getCalendarDate());
            calendar.add(Calendar.MONTH, 1);
            planCalendar2.setCalendarId(null);
            planCalendar2.setCalendarDate(calendar.getTime());
            //新增当前月计划下的日历计划
            planCalendarService.insertPlanCalendar(planCalendar2);
        }
        //获取上个月的所有日计划列表
        PlanDay planDay = new PlanDay();
        planDay.setMonthPlanId(planMonthId - 1);
        List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
        for (PlanDay planDay1 : planDayList) {
            //克隆日计划
            PlanDay planDay2 = (PlanDay) planDay1.clone();
            Date dayPlan1Date = planDay1.getDayPlanDate();
            calendar.setTime(dayPlan1Date);
            calendar.add(Calendar.MONTH, 1);
            //将上个月日计划的月份+1
            planDay2.setDayPlanDate(calendar.getTime());
            planDay2.setMonthPlanId(planMonthId);
            planDay2.setDayPlanId(null);
            if (planDay1.getDayPlanSite() != null) {
                //若日计划的资源为站点，则设置站点的上一次巡检日期
                Site site = siteService.selectSiteById(planDay1.getDayPlanSite());
                site.setSiteLastDate(calendar.getTime());
            } else {
                //若日计划的资源为资源点，则设置资源点的上一次巡检日期
                Resource resource = resourceService.selectResourceById(planDay1.getDayPlanResource());
                resource.setResourceLastDate(calendar.getTime());
            }
            PlanCalendar planCalendar2 = new PlanCalendar();
            planCalendar2.setMonthPlanId(planMonthId);
            planCalendar2.setCalendarDate(calendar.getTime());
            //根据月计划ID和日期来获取唯一的日历计划
            Integer planCalendar2Id = planCalendarService.selectPlanCalendarList(planCalendar2).get(0).getCalendarId();
            //设置日计划的日历计划ID
            planDay2.setCalendarPlanId(planCalendar2Id);
            //新增当前月计划下的日计划
            i += planDayService.insertPlanDay(planDay2);
        }
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        //设置当前月计划的状态为制定中
        planMonth.setMonthPlanStatus(1);
        planMonthService.updatePlanMonth(planMonth);
        return toAjax(i);
    }

    /**
     * * 跳转到显示日计划页
     *
     * @param date        点击日历的日期
     * @param planMonthId 当前月计划ID
     * @param from        请求的来源，审核或者计划
     * @param sta         月计划的状态
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
     *
     * @param planMonthId 月计划ID
     * @param date        点击日历的日期
     * @param planDay     日计划
     * @return
     */
    @PostMapping("/dayPlanList")
    @ResponseBody
    public TableDataInfo list(@RequestParam(required = false) Integer planMonthId, @RequestParam(required = false) Date date, PlanDay planDay) {
        planDay.setDayPlanDate(date);
        planDay.setMonthPlanId(planMonthId);
        //分页查询当前月计划，当前日期下所有的日计划
        startPage();
        List<PlanDay> list = planDayService.selectPlanDayList(planDay);
        for (PlanDay day : list) {
            //设置表格内显示的资源名称
            if (day.getDayPlanSite() != null) {
                day.setResourcesName(siteService.selectSiteById(day.getDayPlanSite()).getSiteName());
                day.setResourcesAddress(siteService.selectSiteById(day.getDayPlanSite()).getSiteAddress());
            } else {
                day.setResourcesName(resourceService.selectResourceById(day.getDayPlanResource()).getResourceName());
                day.setResourcesAddress(resourceService.selectResourceById(day.getDayPlanResource()).getResourceAddress());
            }

        }
        return getDataTable(list);
    }

    /**
     * Ajax显示具体资源信息
     *
     * @param id   日计划ID或资源点ID或站点ID
     * @param from 发送该请求的页面标识
     * @return
     */
    @PostMapping("/resourcesDetail")
    @ResponseBody
    public String resourcesDetail(@RequestParam(required = false) Long id, @RequestParam(required = false) String from) {
        Map<String, Object> resultMap = new HashMap<>();
        String resourcesName = "", resourcesType = "", resourcesLongitude = "", resourcesLatitude = "", resourcesAddress = "", resourcesInnetDate = "";
        Site site = null;
        Resource resource = null;
        int flag = 1;
        if ("planDay".equals(from)) {
            PlanDay planDay = planDayService.selectPlanDayById(id.intValue());
            if (planDay.getDayPlanSite() != null) {
                //若日计划的站点编号不为空，则资源类型为站点
                site = siteService.selectSiteById(planDay.getDayPlanSite());
                flag = 1;
            } else {
                //若日计划的站点编号为空，则资源类型为资源点
                resource = resourceService.selectResourceById(planDay.getDayPlanResource());
                flag = 0;
            }
        } else if ("addSite".equals(from)) {
            //如果点击添加站点之后的获取站点信息，则id为站点id
            site = siteService.selectSiteById(id);
            flag = 1;
        } else if ("addResource".equals(from)) {
            //如果点击添加资源点之后的获取资源点信息，则id为资源点id
            resource = resourceService.selectResourceById(id);
            flag = 0;
        }
        //设置资源信息
        if (flag == 1) {
            resourcesName = site.getSiteName();
            resourcesType = site.getSiteType();
            resourcesLongitude = site.getSiteLongitude();
            resourcesLatitude = site.getSiteLatitude();
            resourcesAddress = site.getSiteAddress();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            resourcesInnetDate = sdf.format(site.getSiteInnetDate());
        } else {
            resourcesName = resource.getResourceName();
            resourcesType = resource.getResourceType();
            resourcesLongitude = resource.getResourceLongitude();
            resourcesLatitude = resource.getResourceLatitude();
            resourcesAddress = resource.getResourceAddress();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            resourcesInnetDate = sdf.format(resource.getResourceStartTime());
        }
        //将资源信息包装成json返回
        resultMap.put("resourcesName", resourcesName);
        resultMap.put("resourcesType", resourcesType);
        resultMap.put("resourcesLongitude", resourcesLongitude);
        resultMap.put("resourcesLatitude", resourcesLatitude);
        resultMap.put("resourcesAddress", resourcesAddress);
        resultMap.put("resourcesInnetDate", resourcesInnetDate);
        return JSON.toJSONString(resultMap);
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
     * 查询巡检站点列表
     *
     * @param dateStr     日期字符串
     * @param planMonthId 当前月计划ID
     * @return
     */
    @PostMapping("/siteList")
    @ResponseBody
    public TableDataInfo siteList(@RequestParam(required = false) String dateStr, @RequestParam(required = false) Integer planMonthId) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        //获取当前日期
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //查询有驻点，并且当前日期大于最近可以巡检日期的站点
        startPage();
        List<Site> list = siteService.selectSiteHasDate(date, planMonth.getMonthPlanStagnation());
        for (Site site : list) {
            //设置表格内字段：巡检周期名称
            if (site.getSiteCycle() != null) {
                Integer siteCycleId = site.getSiteCycle();
                ResourcesCycleType resourcesCycleType = resourcesCycleTypeService.selectResourcesCycleTypeById(siteCycleId);
                site.setCycleName(resourcesCycleType.getResourcesCycleName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 查询巡检资源点列表
     *
     * @param dateStr     日期字符串
     * @param planMonthId 当前月计划ID
     * @return
     */
    @PostMapping("/resourceList")
    @ResponseBody
    public TableDataInfo resourceList(@RequestParam(required = false) String dateStr, @RequestParam(required = false) Integer planMonthId) {
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planMonthId);
        //获取当前日期
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //查询有驻点，并且当前日期大于最近可以巡检日期的站点
        startPage();
        List<Resource> list = resourceService.selectResourceHasDate(date, planMonth.getMonthPlanStagnation());
        for (Resource resource : list) {
            if (resource.getResourceCycle() != null) {
                //设置表格内字段：巡检周期名称
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
     * @param ids         所选的所有资源ID
     * @param type        资源类型
     * @param dateStr     当前日期字符串
     * @param planMonthId 当前月计划ID
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
        //获取字符串日期里的年月日
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
        //设置日历计划的属性
        planCalendar.setCalendarDate(date);
        planCalendar.setCalendarDay(day);
        planCalendar.setCalendarMonth(month);
        planCalendar.setCalendarStatus(1);
        planCalendar.setCalendarYear(year);
        planCalendar.setMonthPlanId(planMonthId);
        List<PlanCalendar> planCalendars = planCalendarService.selectPlanCalendarList(planCalendar);
        //若符合条件的日历计划不存在，则新增一条
        if (planCalendars.size() == 0) {
            //插入一条计划日历的记录
            planCalendarService.insertPlanCalendar(planCalendar);
        }
        //根据条件获取唯一的一条日历计划
        planCalendar = planCalendarService.selectPlanCalendarList(planCalendar).get(0);
        for (String id : ids.split(",")) {
            PlanDay planDay = new PlanDay();
            //设置日计划日期为日历计划日期
            planDay.setDayPlanDate(date);
            planDay.setCalendarPlanId(planCalendar.getCalendarId());
            planDay.setMonthPlanId(planCalendar.getMonthPlanId());
            planDay.setDayPlanName("基站综合代维-" + year + "年" + month + "月" + day + "日巡检计划");
            //设置日计划状态为不可用
            planDay.setDayPlanStatus(0);
            if ("site".equals(type)) {
                //如果点击的是站点，则为日计划添加站点
                Site site = siteService.selectSiteById(Long.valueOf(id).longValue());
                planDay.setDayPlanSite(site.getSiteId());
                planDay.setDayPlanStagnation(site.getSiteStagantionCompany());
                planDay.setDayPlanType("站点");
                site.setSitePlanDay(day);
                site.setSiteLastDate(date);
                //根据站点巡检周期设置上次巡检日期之后下一次可以巡检的最近日期
                Integer addMonth = resourcesCycleTypeService.selectResourcesCycleTypeById(site.getSiteCycle()).getResourcesCycleMonth();
                cal.add(Calendar.MONTH, addMonth);
                site.setSiteLastedDate(cal.getTime());
                siteService.updateSite(site);
            } else {
                //如果点击的是资源点，则为日计划添加资源点
                Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
                planDay.setDayPlanResource(resource.getResourceId());
                planDay.setDayPlanStagnation(resource.getResourceStagantionCompany());
                planDay.setDayPlanType("资源点");
                resource.setResourcePlanDay(day);
                resource.setResourceLastDate(date);
                //根据资源点巡检周期设置上次巡检日期之后下一次可以巡检的最近日期
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
     *
     * @param ids 选中的要释放资源的日计划ID
     * @return
     */
    @PostMapping("/releaseResource")
    @ResponseBody
    public AjaxResult releaseResource(String ids) {
        Integer[] intArray = Convert.toIntArray(",", ids);
        for (Integer planDayId : intArray) {
            PlanDay planDay = planDayService.selectPlanDayById(planDayId);
            //首先将资源点或站点的上次巡检日期与最近可巡检日期设置为空
            if (planDay.getDayPlanSite() != null) {
                Site site = siteService.selectSiteById(planDay.getDayPlanSite());
                siteService.relaseResources(site);
            } else {
                Resource resource = resourceService.selectResourceById(planDay.getDayPlanResource());
                resourceService.relaseResources(resource);
            }
        }
        //其次删除所选的日计划ID
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
