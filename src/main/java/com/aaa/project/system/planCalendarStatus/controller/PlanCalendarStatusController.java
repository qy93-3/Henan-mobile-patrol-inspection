package com.aaa.project.system.planCalendarStatus.controller;

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
import com.aaa.project.system.planCalendarStatus.domain.PlanCalendarStatus;
import com.aaa.project.system.planCalendarStatus.service.IPlanCalendarStatusService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 日历每日状态(日历上显示) 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/planCalendarStatus")
public class PlanCalendarStatusController extends BaseController
{
    private String prefix = "system/planCalendarStatus";
	
	@Autowired
	private IPlanCalendarStatusService planCalendarStatusService;
	
	@RequiresPermissions("system:planCalendarStatus:view")
	@GetMapping()
	public String planCalendarStatus()
	{
	    return prefix + "/planCalendarStatus";
	}
	
	/**
	 * 查询日历每日状态(日历上显示)列表
	 */
	@RequiresPermissions("system:planCalendarStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanCalendarStatus planCalendarStatus)
	{
		startPage();
        List<PlanCalendarStatus> list = planCalendarStatusService.selectPlanCalendarStatusList(planCalendarStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出日历每日状态(日历上显示)列表
	 */
	@RequiresPermissions("system:planCalendarStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanCalendarStatus planCalendarStatus)
    {
    	List<PlanCalendarStatus> list = planCalendarStatusService.selectPlanCalendarStatusList(planCalendarStatus);
        ExcelUtil<PlanCalendarStatus> util = new ExcelUtil<PlanCalendarStatus>(PlanCalendarStatus.class);
        return util.exportExcel(list, "planCalendarStatus");
    }
	
	/**
	 * 新增日历每日状态(日历上显示)
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存日历每日状态(日历上显示)
	 */
	@RequiresPermissions("system:planCalendarStatus:add")
	@Log(title = "日历每日状态(日历上显示)", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanCalendarStatus planCalendarStatus)
	{		
		return toAjax(planCalendarStatusService.insertPlanCalendarStatus(planCalendarStatus));
	}

	/**
	 * 修改日历每日状态(日历上显示)
	 */
	@GetMapping("/edit/{monthStatusId}")
	public String edit(@PathVariable("monthStatusId") Integer monthStatusId, ModelMap mmap)
	{
		PlanCalendarStatus planCalendarStatus = planCalendarStatusService.selectPlanCalendarStatusById(monthStatusId);
		mmap.put("planCalendarStatus", planCalendarStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存日历每日状态(日历上显示)
	 */
	@RequiresPermissions("system:planCalendarStatus:edit")
	@Log(title = "日历每日状态(日历上显示)", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PlanCalendarStatus planCalendarStatus)
	{		
		return toAjax(planCalendarStatusService.updatePlanCalendarStatus(planCalendarStatus));
	}
	
	/**
	 * 删除日历每日状态(日历上显示)
	 */
	@RequiresPermissions("system:planCalendarStatus:remove")
	@Log(title = "日历每日状态(日历上显示)", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planCalendarStatusService.deletePlanCalendarStatusByIds(ids));
	}
	
}
