package com.aaa.project.system.planDay.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.planDay.domain.PlanDay;
import com.aaa.project.system.planDay.service.IPlanDayService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源日计划 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/planDay")
public class PlanDayController extends BaseController
{
    private String prefix = "system/planDay";
	
	@Autowired
	private IPlanDayService planDayService;
	
	@RequiresPermissions("system:planDay:view")
	@GetMapping()
	public String planDay()
	{
	    return prefix + "/planDay";
	}
	
	/**
	 * 查询巡检资源日计划列表
	 */
	@RequiresPermissions("system:planDay:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanDay planDay)
	{
		startPage();
        List<PlanDay> list = planDayService.selectPlanDayList(planDay);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源日计划列表
	 */
	@RequiresPermissions("system:planDay:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanDay planDay)
    {
    	List<PlanDay> list = planDayService.selectPlanDayList(planDay);
        ExcelUtil<PlanDay> util = new ExcelUtil<PlanDay>(PlanDay.class);
        return util.exportExcel(list, "planDay");
    }
	
	/**
	 * 新增巡检资源日计划
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源日计划
	 */
	@RequiresPermissions("system:planDay:add")
	@Log(title = "巡检资源日计划", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanDay planDay)
	{		
		return toAjax(planDayService.insertPlanDay(planDay));
	}

	/**
	 * 修改巡检资源日计划
	 */
	@GetMapping("/edit/{dayPlanId}")
	public String edit(@PathVariable("dayPlanId") Integer dayPlanId, ModelMap mmap)
	{
		PlanDay planDay = planDayService.selectPlanDayById(dayPlanId);
		mmap.put("planDay", planDay);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源日计划
	 */
	@RequiresPermissions("system:planDay:edit")
	@Log(title = "巡检资源日计划", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlanDay planDay)
	{		
		return toAjax(planDayService.updatePlanDay(planDay));
	}
	
	/**
	 * 删除巡检资源日计划
	 */
	@RequiresPermissions("system:planDay:remove")
	@Log(title = "巡检资源日计划", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planDayService.deletePlanDayByIds(ids));
	}
	
}
