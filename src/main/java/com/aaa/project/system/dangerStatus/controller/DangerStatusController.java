package com.aaa.project.system.dangerStatus.controller;

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
import com.aaa.project.system.dangerStatus.domain.DangerStatus;
import com.aaa.project.system.dangerStatus.service.IDangerStatusService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源隐患解决状态 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/dangerStatus")
public class DangerStatusController extends BaseController
{
    private String prefix = "system/dangerStatus";
	
	@Autowired
	private IDangerStatusService dangerStatusService;
	
	@RequiresPermissions("system:dangerStatus:view")
	@GetMapping()
	public String dangerStatus()
	{
	    return prefix + "/dangerStatus";
	}
	
	/**
	 * 查询巡检资源隐患解决状态列表
	 */
	@RequiresPermissions("system:dangerStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangerStatus dangerStatus)
	{
		startPage();
        List<DangerStatus> list = dangerStatusService.selectDangerStatusList(dangerStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源隐患解决状态列表
	 */
	@RequiresPermissions("system:dangerStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangerStatus dangerStatus)
    {
    	List<DangerStatus> list = dangerStatusService.selectDangerStatusList(dangerStatus);
        ExcelUtil<DangerStatus> util = new ExcelUtil<DangerStatus>(DangerStatus.class);
        return util.exportExcel(list, "dangerStatus");
    }
	
	/**
	 * 新增巡检资源隐患解决状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源隐患解决状态
	 */
	@RequiresPermissions("system:dangerStatus:add")
	@Log(title = "巡检资源隐患解决状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangerStatus dangerStatus)
	{		
		return toAjax(dangerStatusService.insertDangerStatus(dangerStatus));
	}

	/**
	 * 修改巡检资源隐患解决状态
	 */
	@GetMapping("/edit/{dangerHandleId}")
	public String edit(@PathVariable("dangerHandleId") Integer dangerHandleId, ModelMap mmap)
	{
		DangerStatus dangerStatus = dangerStatusService.selectDangerStatusById(dangerHandleId);
		mmap.put("dangerStatus", dangerStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源隐患解决状态
	 */
	@RequiresPermissions("system:dangerStatus:edit")
	@Log(title = "巡检资源隐患解决状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangerStatus dangerStatus)
	{		
		return toAjax(dangerStatusService.updateDangerStatus(dangerStatus));
	}
	
	/**
	 * 删除巡检资源隐患解决状态
	 */
	@RequiresPermissions("system:dangerStatus:remove")
	@Log(title = "巡检资源隐患解决状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangerStatusService.deleteDangerStatusByIds(ids));
	}
	
}
