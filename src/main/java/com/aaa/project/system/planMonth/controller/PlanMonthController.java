package com.aaa.project.system.planMonth.controller;

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
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源月计划 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/planMonth")
public class PlanMonthController extends BaseController
{
    private String prefix = "system/planMonth";
	
	@Autowired
	private IPlanMonthService planMonthService;
	
	@RequiresPermissions("system:planMonth:view")
	@GetMapping()
	public String planMonth()
	{
	    return prefix + "/planMonth";
	}
	
	/**
	 * 查询巡检资源月计划列表
	 */
	@RequiresPermissions("system:planMonth:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PlanMonth planMonth)
	{
		startPage();
        List<PlanMonth> list = planMonthService.selectPlanMonthList(planMonth);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源月计划列表
	 */
	@RequiresPermissions("system:planMonth:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlanMonth planMonth)
    {
    	List<PlanMonth> list = planMonthService.selectPlanMonthList(planMonth);
        ExcelUtil<PlanMonth> util = new ExcelUtil<PlanMonth>(PlanMonth.class);
        return util.exportExcel(list, "planMonth");
    }
	
	/**
	 * 新增巡检资源月计划
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源月计划
	 */
	@RequiresPermissions("system:planMonth:add")
	@Log(title = "巡检资源月计划", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PlanMonth planMonth)
	{		
		return toAjax(planMonthService.insertPlanMonth(planMonth));
	}

	/**
	 * 修改巡检资源月计划
	 */
	@GetMapping("/edit/{monthPlanId}")
	public String edit(@PathVariable("monthPlanId") Integer monthPlanId, ModelMap mmap)
	{
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
	public AjaxResult editSave(PlanMonth planMonth)
	{		
		return toAjax(planMonthService.updatePlanMonth(planMonth));
	}
	
	/**
	 * 删除巡检资源月计划
	 */
	@RequiresPermissions("system:planMonth:remove")
	@Log(title = "巡检资源月计划", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(planMonthService.deletePlanMonthByIds(ids));
	}
	
}
