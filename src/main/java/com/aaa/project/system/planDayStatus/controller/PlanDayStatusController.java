package com.aaa.project.system.planDayStatus.controller;

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
import com.aaa.project.system.planDayStatus.domain.PlanDayStatus;
import com.aaa.project.system.planDayStatus.service.IPlanDayStatusService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源日计划状态 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/planDayStatus")
public class PlanDayStatusController extends BaseController
{
    private String prefix = "system/planDayStatus";
	
	@Autowired
	private IPlanDayStatusService planDayStatusService;
	
	@RequiresPermissions("system:planDayStatus:view")
	@GetMapping()
	public String planDayStatus()
	{
	    return prefix + "/planDayStatus";
	}
	
	/**
	 * 查询巡检资源日计划状态列表
	 */
	@RequiresPermissions("system:planDayStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanDayStatus planDayStatus)
	{
		startPage();
        List<PlanDayStatus> list = planDayStatusService.selectPlanDayStatusList(planDayStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源日计划状态列表
	 */
	@RequiresPermissions("system:planDayStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanDayStatus planDayStatus)
    {
    	List<PlanDayStatus> list = planDayStatusService.selectPlanDayStatusList(planDayStatus);
        ExcelUtil<PlanDayStatus> util = new ExcelUtil<PlanDayStatus>(PlanDayStatus.class);
        return util.exportExcel(list, "planDayStatus");
    }
	
	/**
	 * 新增巡检资源日计划状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源日计划状态
	 */
	@RequiresPermissions("system:planDayStatus:add")
	@Log(title = "巡检资源日计划状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanDayStatus planDayStatus)
	{		
		return toAjax(planDayStatusService.insertPlanDayStatus(planDayStatus));
	}

	/**
	 * 修改巡检资源日计划状态
	 */
	@GetMapping("/edit/{dayStatusId}")
	public String edit(@PathVariable("dayStatusId") Integer dayStatusId, ModelMap mmap)
	{
		PlanDayStatus planDayStatus = planDayStatusService.selectPlanDayStatusById(dayStatusId);
		mmap.put("planDayStatus", planDayStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源日计划状态
	 */
	@RequiresPermissions("system:planDayStatus:edit")
	@Log(title = "巡检资源日计划状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlanDayStatus planDayStatus)
	{		
		return toAjax(planDayStatusService.updatePlanDayStatus(planDayStatus));
	}
	
	/**
	 * 删除巡检资源日计划状态
	 */
	@RequiresPermissions("system:planDayStatus:remove")
	@Log(title = "巡检资源日计划状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planDayStatusService.deletePlanDayStatusByIds(ids));
	}
	
}
