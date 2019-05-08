package com.aaa.project.system.mession.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aaa.common.utils.file.FileUploadUtils;
import com.aaa.framework.config.DouDouConfig;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.danger.domain.Danger;
import com.aaa.project.system.danger.service.IDangerService;
import com.aaa.project.system.message.domain.Message;
import com.aaa.project.system.message.service.IMessageService;
import com.aaa.project.system.mession.results.Result;
import com.aaa.project.system.messionStatus.domain.MessionStatus;
import com.aaa.project.system.messionStatus.service.IMessionStatusService;
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.project.system.planDay.domain.PlanDay;
import com.aaa.project.system.planDay.service.IPlanDayService;
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.project.system.reply.domain.Reply;
import com.aaa.project.system.reply.service.IReplyService;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.aaa.project.system.routingProject.domain.RoutingProject;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jna.platform.unix.solaris.LibKstat;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.mession.service.IMessionService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 日计划分配任务 信息操作处理
 *
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/mession")
public class MessionController extends BaseController {
    private String prefix = "system/mession";

    @Autowired
    private IMessionService messionService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ISiteService siteService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IMessionStatusService messionStatusService;

    @Autowired
    private IRoutingPeopleService routingPeopleService;

    @Autowired
    private IRoutingProjectService routingProjectService;

    @Autowired
    private IReplyService replyService;

    @Autowired
    private IDangerService dangerService;

    @Autowired
    private IPlanMonthService planMonthService;

    @Autowired
    private IPlanCalendarService planCalendarService;

    @Autowired
    private IStagnationService stagnationService;

    @Autowired
    private IPlanDayService planDayService;

    @Autowired
    private IMessageService messageService;

    @RequiresPermissions("system:mession:view")
    @GetMapping()
    public String mession(Map<String, Object> map) {
        List<MessionStatus> messionStatusList = messionStatusService.selectMessionStatusList(null);
        map.put("messionStatusList", messionStatusList);
        List<City> cities = cityService.selectCityList(null);
        map.put("cities", cities);
        return prefix + "/mession";
    }

    /**
     * 查询日计划分配任务列表
     */
    @RequiresPermissions("system:mession:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Mession mession, @RequestParam(required = false) Integer stagantion, @RequestParam(required = false) Integer status) {
        if (stagantion != null) {
            if (stagantion != 0)
                mession.setMessionStagnationId(stagantion);
        }
        if (status != null) {
            if (status != 10)
                mession.setMessionStatus(status);
        }
        startPage();
        List<Mession> list = messionService.selectMessionList(mession);
        for (Mession mession1 : list) {
            if (mession1.getMessionSiteId() != null) {
                mession1.setResourcesName(siteService.selectSiteById(mession1.getMessionSiteId()).getSiteName());
                mession1.setResourcesType("站点");
            }
            if (mession1.getMessionResourceId() != null) {
                mession1.setResourcesName(resourceService.selectResourceById(mession1.getMessionResourceId()).getResourceName());
                mession1.setResourcesType("资源点");
            }
            mession1.setStatusName(messionStatusService.selectMessionStatusById(mession1.getMessionStatus()).getMessionStatusName());
            mession1.setStagnationName(stagnationService.selectStagnationById(mession1.getMessionStagnationId()).getStagnationPname());
        }
        return getDataTable(list);
    }


    /**
     * 导出日计划分配任务列表
     */
    @RequiresPermissions("system:mession:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Mession mession) {
        List<Mession> list = messionService.selectMessionList(mession);
        ExcelUtil<Mession> util = new ExcelUtil<Mession>(Mession.class);
        return util.exportExcel(list, "mession");
    }

    /**
     * 新增日计划分配任务
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存日计划分配任务
     */
    @RequiresPermissions("system:mession:add")
    @Log(title = "日计划分配任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Mession mession) {
        return toAjax(messionService.insertMession(mession));
    }

    /**
     * 修改日计划分配任务
     */
    @GetMapping("/edit/{messionId}")
    public String edit(@PathVariable("messionId") Integer messionId, ModelMap mmap) {
        Mession mession = messionService.selectMessionById(messionId);
        mmap.put("mession", mession);
        return prefix + "/edit";
    }

    /**
     * 修改保存日计划分配任务
     */
    @RequiresPermissions("system:mession:edit")
    @Log(title = "日计划分配任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Mession mession) {
        return toAjax(messionService.updateMession(mession));
    }

    /**
     * 删除日计划分配任务
     */
    @RequiresPermissions("system:mession:remove")
    @Log(title = "日计划分配任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(messionService.deleteMessionByIds(ids));
    }

    /**
     * 微信小程序
     */
    /**
     * 待巡检日任务列表
     *
     * @param mession
     * @return
     */
    @PostMapping("/wxmessioonlist")
    @ResponseBody
    public Result<Mession> wxmessionlist(Mession mession, PlanDay planDay, @RequestBody Map<String, Integer> data) {
        ArrayList list = new ArrayList();
        planDay.setCalendarPlanId(data.get("calendarId"));
        List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
        for (PlanDay day : planDayList) {
            mession.setMessionStatus(data.get("messionStatus"));
            mession.setMessionDayId(day.getDayPlanId());
            Mession mession1 = messionService.selectMession(mession);
            if (mession1 != null) {
                if (mession1.getMessionResourceId() == null) {
                    mession1.setSite(siteService.selectSiteById(mession1.getMessionSiteId()));
                } else {
                    mession1.setResource(resourceService.selectResourceById(mession1.getMessionResourceId()));
                }
                list.add(mession1);
            }
        }

        Result<Mession> result = new Result();
        result.setCode(200);
        result.setData(list);
        return result;
    }

    /**
     * @return java.util.List<com.aaa.project.system.mession.domain.Mession>
     * @Author ryy
     * @Description 获取巡检人员对应的认领过的全部任务信息
     * @Date 2019/4/26 17:50
     * @Param [routingPeople]
     **/
    @RequestMapping("/empMessionList")
    @ResponseBody
    public List<Mession> empMessionList(@RequestParam String routingPeople) {
        if (routingPeople != null || routingPeople != "") {
            JSONObject parse = (JSONObject) JSON.parse(routingPeople);
            Integer routingId = Integer.parseInt(parse.getString("routingId"));

            Mession mession = new Mession();
            mession.setMessionRoutingId(routingId);

            List<Mession> messionList = messionService.selectMessionList(mession);
            for (Mession tmession : messionList) {
                //获取巡检站点信息
                Site site = siteService.selectSiteById(tmession.getMessionSiteId());
                tmession.setSite(site);
                //获取巡检资源点信息
                Resource resource = resourceService.selectResourceById(tmession.getMessionResourceId());
                tmession.setResource(resource);
                //获取巡检任务状态信息
                MessionStatus messionStatus = messionStatusService.selectMessionStatusById(tmession.getMessionStatus());
                tmession.setTblMessionStatus(messionStatus);
            }
            return messionList;
        }

        return null;
    }


    /**
     * 获取巡检项目
     *
     * @param routingProject
     * @return
     */
    @RequestMapping("/wxproject")
    @ResponseBody
    public Result<RoutingProject> wxproject(RoutingProject routingProject) {
        Result<RoutingProject> result = new Result<>();
        List<RoutingProject> routingProjectsList = routingProjectService.selectRoutingProjectList(routingProject);
        result.setCode(200);
        result.setMsg("获取成功");
        result.setData(routingProjectsList);
        return result;
    }

    /**
     * 巡检项目保存
     *
     * @param file
     * @param routingPeople
     * @param mession
     * @param routingProject
     * @param reply
     * @return
     */
    @RequestMapping("/wxprojectsave")
    @ResponseBody
    public Result wxprojectsave(@RequestParam(value = "file", required = false) MultipartFile file, RoutingPeople routingPeople, Mession mession, RoutingProject routingProject, Reply reply) throws IOException {
        Result result = new Result();
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);
        Mession mession1 = messionService.selectMessionById(mession.getMessionId());
        reply.setRoutingPersonId(routingPeople1.getRoutingId());
        reply.setMessionId(mession.getMessionId());
        reply.setRoutingProjectId(routingProject.getRoutingProjectId());
        if (mession1.getMessionResourceId() == null) {
            reply.setReplySiteId(mession1.getMessionSiteId());
        } else {
            reply.setReplyResourceId(mession1.getMessionResourceId());
        }
        /**
         * 上传图片
         */
        String avatarPath = DouDouConfig.getAvatarPath();
        String fileName = FileUploadUtils.upload(avatarPath, file);
        reply.setPicture(fileName);
        replyService.insertReply(reply);
        result.setCode(200);
        result.setMsg("保存成功");
        return result;
    }

    /**
     * 上报隐患
     *
     * @param file
     * @param mession
     * @param routingPeople
     * @param routingProject
     * @param danger
     * @return
     */

    @RequestMapping("/wxwrong")
    @ResponseBody
    public Result wxwrong(@RequestParam(value = "file", required = false) MultipartFile file, Mession mession, RoutingPeople routingPeople, RoutingProject routingProject, Danger danger) throws IOException {
        Result result = new Result();
        Mession mession1 = messionService.selectMessionById(mession.getMessionId());
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);
        danger.setMessionId(mession1.getMessionId());
        danger.setRoutingPersonId(routingPeople1.getRoutingId());
        danger.setRoutingProjectId(routingProject.getRoutingProjectId());
        Date date = new Date();
        danger.setDangerDate(date);
        if (mession1.getMessionResourceId() == null) {
            danger.setDangerSiteId(mession1.getMessionSiteId());
        } else {
            danger.setDangerResourceId(mession1.getMessionResourceId());
        }
        String avatarPath = DouDouConfig.getAvatarPath();
        String fileName = FileUploadUtils.upload(avatarPath, file);
        danger.setDangerPicture(fileName);
        dangerService.insertDanger(danger);
        result.setCode(200);
        result.setMsg("上报成功");
        return result;
    }

    /**
     * 完成
     *
     * @param map
     * @return
     */
    @RequestMapping("/wxfinish")
    @ResponseBody
    public Result wxfinish(@RequestBody Map<String, Integer> map) {
        Result result = new Result();
        Mession mession = messionService.selectMessionById(map.get("messionId"));

        //完成时更新月计划中的巡检完成资源数
        //根据mession获取对应的日计划
        PlanDay planDay = planDayService.selectPlanDayById(mession.getMessionDayId());
        //根据日计划获取月计划
        PlanMonth planMonth = planMonthService.selectPlanMonthById(planDay.getMonthPlanId());
        //将月计划已巡检资源数+1
        Integer finishResouecesNum = planMonth.getMonthRoutingResources() == null ? 0 : planMonth.getMonthRoutingResources();
        if (mession.getMessionStatus() != 2) {
            finishResouecesNum++;
            mession.setMessionStatus(2);
        }
        planMonth.setMonthRoutingResources(finishResouecesNum);
        planMonthService.updatePlanMonth(planMonth);

        messionService.updateMession(mession);
        return result;
    }

    /**
     * 每月计划
     *
     * @param map
     * @return
     */
    @RequestMapping("/wxplan")
    @ResponseBody
    public Result wxplan(@RequestBody Map<String, String> map) {
        Result result = new Result();
        List list = new ArrayList();
        PlanMonth planMonth = new PlanMonth();
        PlanCalendar planCalendar = new PlanCalendar();
        RoutingPeople routingPeople = new RoutingPeople();
        routingPeople.setOpenId(map.get("openId"));
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);

        planMonth.setMonthPlanStagnation(routingPeople1.getStagnationId());
        planMonth.setMonthPlanMonth(Integer.parseInt(map.get("month")));
        planMonth.setMonthPlanYear(Integer.parseInt(map.get("year")));
        List<PlanMonth> planMonths = planMonthService.selectPlanMonthList(planMonth);
        if (planMonths.size() != 0) {
            PlanMonth planMonth1 = planMonths.get(0);
            planCalendar.setMonthPlanId(planMonth1.getMonthPlanId());
            List<PlanCalendar> planCalendarsLsit = planCalendarService.selectPlanCalendarList(planCalendar);
            result.setData(planCalendarsLsit);
        }

        return result;
    }

    /**
     * 认领
     *
     * @param map
     * @return
     */
    @RequestMapping("/wxclaim")
    @ResponseBody
    public Result wxclaim(@RequestBody Map<String, String> map, RoutingPeople routingPeople) {
        Result result = new Result();
        routingPeople.setOpenId(map.get("openId"));
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);

        Mession mession = messionService.selectMessionById(Integer.parseInt(map.get("messionId")));
        mession.setMessionRoutingId(routingPeople1.getRoutingId());
        mession.setMessionStatus(1);
        messionService.updateMession(mession);
        result.setCode(200);
        return result;
    }

    /**
     * 任务详情
     *
     * @param map
     * @return
     */
    @RequestMapping("/wxdetails")
    @ResponseBody
    public Result wxdetails(@RequestBody Map<String, Long> map, PlanDay planDay) {
        Result result = new Result();
        if (map.get("resourceId") == null) {
            planDay.setDayPlanSite(map.get("siteId"));
        } else {
            planDay.setDayPlanResource(map.get("resourceId"));
        }
        int calendarId = Integer.parseInt(Long.toString(map.get("calendarId")));
        PlanCalendar planCalendar = planCalendarService.selectPlanCalendarById(calendarId);
        planDay.setMonthPlanId(planCalendar.getMonthPlanId());

        List<PlanDay> planDayList = planDayService.selectPlanDayList(planDay);
        for (PlanDay day : planDayList) {
            if (day.getDayPlanResource() == null) {
                day.setSite(siteService.selectSiteById(day.getDayPlanSite()));
            } else {
                day.setResource(resourceService.selectResourceById(day.getDayPlanResource()));
            }
        }
        result.setData(planDayList);
        result.setCode(200);
        return result;
    }

    /**
     * 公告
     *
     * @param message
     * @return
     */
    @RequestMapping("/wxnotice")
    @ResponseBody
    public Result wxnotice(Message message) {
        Result result = new Result();
        List<Message> messageList = messageService.selectMessageList(message);
        result.setCode(200);
        result.setData(messageList);
        return result;
    }

}
