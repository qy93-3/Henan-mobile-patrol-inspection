package com.aaa.project.system.planMonthStatus.controller;

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
import com.aaa.project.system.planMonthStatus.domain.PlanMonthStatus;
import com.aaa.project.system.planMonthStatus.service.IPlanMonthStatusService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源月计划状态 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/planMonthStatus")
public class PlanMonthStatusController extends BaseController
{
    private String prefix = "system/planMonthStatus";
	
	@Autowired
	private IPlanMonthStatusService planMonthStatusService;
	
	@RequiresPermissions("system:planMonthStatus:view")
	@GetMapping()
	public String planMonthStatus()
	{
	    return prefix + "/planMonthStatus";
	}
	
	/**
	 * 查询巡检资源月计划状态列表
	 */
	@RequiresPermissions("system:planMonthStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanMonthStatus planMonthStatus)
	{
		startPage();
        List<PlanMonthStatus> list = planMonthStatusService.selectPlanMonthStatusList(planMonthStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源月计划状态列表
	 */
	@RequiresPermissions("system:planMonthStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanMonthStatus planMonthStatus)
    {
    	List<PlanMonthStatus> list = planMonthStatusService.selectPlanMonthStatusList(planMonthStatus);
        ExcelUtil<PlanMonthStatus> util = new ExcelUtil<PlanMonthStatus>(PlanMonthStatus.class);
        return util.exportExcel(list, "planMonthStatus");
    }
	
	/**
	 * 新增巡检资源月计划状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源月计划状态
	 */
	@RequiresPermissions("system:planMonthStatus:add")
	@Log(title = "巡检资源月计划状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanMonthStatus planMonthStatus)
	{		
		return toAjax(planMonthStatusService.insertPlanMonthStatus(planMonthStatus));
	}

	/**
	 * 修改巡检资源月计划状态
	 */
	@GetMapping("/edit/{monthStatusId}")
	public String edit(@PathVariable("monthStatusId") Integer monthStatusId, ModelMap mmap)
	{
		PlanMonthStatus planMonthStatus = planMonthStatusService.selectPlanMonthStatusById(monthStatusId);
		mmap.put("planMonthStatus", planMonthStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源月计划状态
	 */
	@RequiresPermissions("system:planMonthStatus:edit")
	@Log(title = "巡检资源月计划状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlanMonthStatus planMonthStatus)
	{		
		return toAjax(planMonthStatusService.updatePlanMonthStatus(planMonthStatus));
	}
	
	/**
	 * 删除巡检资源月计划状态
	 */
	@RequiresPermissions("system:planMonthStatus:remove")
	@Log(title = "巡检资源月计划状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planMonthStatusService.deletePlanMonthStatusByIds(ids));
	}
	
}
