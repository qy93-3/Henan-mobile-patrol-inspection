package com.aaa.project.system.planCalendar.controller;

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
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 每日计划分配(显示日历) 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-24
 */
@Controller
@RequestMapping("/system/planCalendar")
public class PlanCalendarController extends BaseController
{
    private String prefix = "system/planCalendar";
	
	@Autowired
	private IPlanCalendarService planCalendarService;
	
	@RequiresPermissions("system:planCalendar:view")
	@GetMapping()
	public String planCalendar()
	{
	    return prefix + "/planCalendar";
	}
	
	/**
	 * 查询每日计划分配(显示日历)列表
	 */
	@RequiresPermissions("system:planCalendar:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanCalendar planCalendar)
	{
		startPage();
        List<PlanCalendar> list = planCalendarService.selectPlanCalendarList(planCalendar);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出每日计划分配(显示日历)列表
	 */
	@RequiresPermissions("system:planCalendar:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanCalendar planCalendar)
    {
    	List<PlanCalendar> list = planCalendarService.selectPlanCalendarList(planCalendar);
        ExcelUtil<PlanCalendar> util = new ExcelUtil<PlanCalendar>(PlanCalendar.class);
        return util.exportExcel(list, "planCalendar");
    }
	
	/**
	 * 新增每日计划分配(显示日历)
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存每日计划分配(显示日历)
	 */
	@RequiresPermissions("system:planCalendar:add")
	@Log(title = "每日计划分配(显示日历)", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanCalendar planCalendar)
	{		
		return toAjax(planCalendarService.insertPlanCalendar(planCalendar));
	}

	/**
	 * 修改每日计划分配(显示日历)
	 */
	@GetMapping("/edit/{calendarId}")
	public String edit(@PathVariable("calendarId") Integer calendarId, ModelMap mmap)
	{
		PlanCalendar planCalendar = planCalendarService.selectPlanCalendarById(calendarId);
		mmap.put("planCalendar", planCalendar);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存每日计划分配(显示日历)
	 */
	@RequiresPermissions("system:planCalendar:edit")
	@Log(title = "每日计划分配(显示日历)", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlanCalendar planCalendar)
	{		
		return toAjax(planCalendarService.updatePlanCalendar(planCalendar));
	}
	
	/**
	 * 删除每日计划分配(显示日历)
	 */
	@RequiresPermissions("system:planCalendar:remove")
	@Log(title = "每日计划分配(显示日历)", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planCalendarService.deletePlanCalendarByIds(ids));
	}
	
}
