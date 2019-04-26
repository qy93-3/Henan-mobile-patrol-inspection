package com.aaa.project.system.mession.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aaa.project.system.danger.domain.Danger;
import com.aaa.project.system.danger.service.IDangerService;
import com.aaa.project.system.mession.results.Result;
import com.aaa.project.system.messionStatus.domain.MessionStatus;
import com.aaa.project.system.messionStatus.service.IMessionStatusService;
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.project.system.reply.domain.Reply;
import com.aaa.project.system.reply.service.IReplyService;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.aaa.project.system.routingProject.domain.RoutingProject;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class MessionController extends BaseController
{
    private String prefix = "system/mession";
	
	@Autowired
	private IMessionService messionService;
	@Autowired
    private IResourceService resourceService;
	@Autowired
    private ISiteService siteService;
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
	@RequiresPermissions("system:mession:view")
	@GetMapping()
	public String mession()
	{
	    return prefix + "/mession";
	}
	
	/**
	 * 查询日计划分配任务列表
	 */
	@RequiresPermissions("system:mession:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Mession mession)
	{
		startPage();
        List<Mession> list = messionService.selectMessionList(mession);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出日计划分配任务列表
	 */
	@RequiresPermissions("system:mession:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Mession mession)
    {
    	List<Mession> list = messionService.selectMessionList(mession);
        ExcelUtil<Mession> util = new ExcelUtil<Mession>(Mession.class);
        return util.exportExcel(list, "mession");
    }
	
	/**
	 * 新增日计划分配任务
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存日计划分配任务
	 */
	@RequiresPermissions("system:mession:add")
	@Log(title = "日计划分配任务", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Mession mession)
	{		
		return toAjax(messionService.insertMession(mession));
	}

	/**
	 * 修改日计划分配任务
	 */
	@GetMapping("/edit/{messionId}")
	public String edit(@PathVariable("messionId") Integer messionId, ModelMap mmap)
	{
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
	public AjaxResult editSave(Mession mession)
	{		
		return toAjax(messionService.updateMession(mession));
	}
	
	/**
	 * 删除日计划分配任务
	 */
	@RequiresPermissions("system:mession:remove")
	@Log(title = "日计划分配任务", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(messionService.deleteMessionByIds(ids));
	}

	/**
	 * 微信小程序
	 */
    /**
     * 待巡检日任务列表
     * @param mession
     * @return
     */
	@PostMapping("/wxmessioonlist")
    @ResponseBody
	public  Result<Mession>  wxmessionlist(Mession mession,RoutingPeople routingPeople, @RequestBody Map<String,String> data){

		routingPeople.setOpenId(data.get("openid"));
		System.out.println(data.get("openid"));
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);
        int p=Integer.parseInt(data.get("messionStatus"));
		mession.setMessionRoutingId(routingPeople1.getRoutingId());
		mession.setMessionStatus(p);
		Result<Mession> result=new Result();
        List<Mession> messionList = messionService.selectMessionList(mession);
        for (Mession mession1 : messionList) {
            if(mession1.getMessionResourceId()==null){
                mession1.setSite(siteService.selectSiteById(mession1.getMessionSiteId()));
            }
            mession1.setResource(resourceService.selectResourceById(mession1.getMessionResourceId()));
        }
        result.setCode(200);
        result.setData(messionList);
        return result;
	}
	/**
	 * @Author ryy
	 * @Description
	 * @Date 2019/4/26 17:50
	 * @Param [routingPeople]
	 * @return java.util.List<com.aaa.project.system.mession.domain.Mession>
	 **/
	@RequestMapping("/empMessionList")
	@ResponseBody
	public List<Mession> empMessionList(@RequestParam String routingPeople){
		if (routingPeople!=null||routingPeople!=""){
			JSONObject parse = (JSONObject) JSON.parse(routingPeople);
			Integer routingId = Integer.parseInt(parse.getString("routingId"));

			Mession mession = new Mession();
			mession.setMessionRoutingId(routingId);

			List<Mession> messionList = messionService.selectMessionList(mession);
			for (Mession tmession : messionList) {
				//获取巡检站点信息
				Site site = siteService.selectSiteById(tmession.getMessionSiteId());
				tmession.setSite(site);
				//获取巡检任务状态信息

				MessionStatus messionStatus = messionStatusService.selectMessionStatusById(tmession.getMessionStatus());
				tmession.setTblMessionStatus(messionStatus);
			}
			return  messionList;
		}

		return null;
	}


    /**
     * 获取巡检项目
     * @param routingProject
     * @return
     */
	@RequestMapping("/wxproject")
    @ResponseBody
    public  Result<RoutingProject> wxproject(RoutingProject routingProject){
        Result<RoutingProject> result = new Result<>();
        List<RoutingProject> routingProjectsList = routingProjectService.selectRoutingProjectList(routingProject);
        result.setCode(200);
        result.setMsg("获取成功");
        result.setData(routingProjectsList);
        return  result;
    }

	/**
	 * 巡检项目保存
	 * @param file
	 * @param routingPeople
	 * @param mession
	 * @param routingProject
	 * @param reply
	 * @return
	 */
    @RequestMapping("/wxprojectsave")
    @ResponseBody
    public Result wxprojectsave(@RequestParam(value = "file", required = false) MultipartFile file, RoutingPeople routingPeople,Mession mession, RoutingProject routingProject, Reply reply){
        Result result = new Result();
        System.out.println(mession.getMessionId());
        RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);
        Mession mession1 = messionService.selectMessionById(mession.getMessionId());
        reply.setRoutingPersonId(routingPeople1.getRoutingId());
        reply.setMessionId(mession.getMessionId());
        reply.setRoutingProjectId(routingProject.getRoutingProjectId());
        if(mession1.getMessionResourceId()==null){
            reply.setReplySiteId(mession1.getMessionSiteId());
        }else {
            reply.setReplyResourceId(mession1.getMessionResourceId());
        }
        /**
         * 上传图片
         */
		String path="D:\\IdeaProjects\\Henan-mobile-patrol-inspection\\src\\main\\resources\\file\\";
        if(!file.isEmpty()){
			// 上传文件名(防止文件名重复)
			System.out.println(file.getOriginalFilename());
			String fileName=new Date().getTime()+"."+file.getOriginalFilename().split("\\.")[3];
			//创建了一个File对象，名字是filepath，路径是path，名字是filename。
			File filepath = new File(path, fileName);
			// 判断路径是否存在，如果不存在就创建一个
			if(!filepath.getParentFile().exists()){
				filepath.getParentFile().mkdirs();
			}
			try {
				file.transferTo(filepath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 reply.setPicture(path+fileName);

		}
        replyService.insertReply(reply);
        result.setCode(200);
        result.setMsg("保存成功");
        return result;
    }

	/**
	 * 上报隐患
	 * @param file
	 * @param mession
	 * @param routingPeople
	 * @param routingProject
	 * @param danger
	 * @return
	 */

      @RequestMapping("/wxwrong")
	  @ResponseBody
    public  Result wxwrong(@RequestParam(value = "file", required = false) MultipartFile file, Mession mession, RoutingPeople routingPeople, RoutingProject routingProject, Danger danger){
		Result result = new Result();
		  System.out.println("danger "+danger.getDangerLevel());
		  Mession mession1 = messionService.selectMessionById(mession.getMessionId());
		  RoutingPeople routingPeople1 = routingPeopleService.selectRoutingPeople(routingPeople);
		  danger.setMessionId(mession1.getMessionId());
		  danger.setRoutingPersonId(routingPeople1.getRoutingId());
		  danger.setRoutingProjectId(routingProject.getRoutingProjectId());
		  Date date = new Date();
		  danger.setDangerDate(date);
		  if(mession1.getMessionResourceId()==null){
			 danger.setDangerSiteId(mession1.getMessionSiteId());
		  }else {
			  danger.setDangerResourceId(mession1.getMessionResourceId());
		  }
		  String path="D:\\IdeaProjects\\Henan-mobile-patrol-inspection\\src\\main\\resources\\wrong\\";
		  if(!file.isEmpty()){
		  	//上传文件名
			  String filename=new Date().getTime()+"."+file.getOriginalFilename().split("\\.")[3];
			 // 创建了一个File对象，名字是filepath，路径是path，名字是filename。
			  File filepath = new File(path, filename);
			  // 判断路径是否存在，如果不存在就创建一个
			  if(filepath.getParentFile().exists()){
			  	filepath.getParentFile().mkdirs();
			  }

			  try {
				  file.transferTo(filepath);
			  } catch (IOException e) {
				  e.printStackTrace();
			  }

			  danger.setDangerPicture(path+filename);
		  }
		  dangerService.insertDanger(danger);
		  result.setCode(200);
		  result.setMsg("上报成功");
		  return  result;
	}

	/**
	 * 完成
	 * @param map
	 * @return
	 */
	@RequestMapping("/wxfinish")
	@ResponseBody
	public  Result wxfinish(@RequestBody Map<String,Integer> map){
		Result result = new Result();
		Mession mession= messionService.selectMessionById(map.get("messionId"));
		mession.setMessionStatus(mession.getMessionStatus()+1);
		messionService.updateMession(mession);
		return result;
	}
	@RequestMapping("/wxplan")
	@ResponseBody
	public Result wxplan(@RequestBody Map<String,String> map){
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
		for (PlanMonth month : planMonths) {
			planCalendar.setCalendarMonth(month.getMonthPlanId());
		}
		List<PlanCalendar> planCalendarsLsit = planCalendarService.selectPlanCalendarList(planCalendar);
		result.setData(planCalendarsLsit);
		return  result;
	}
}
