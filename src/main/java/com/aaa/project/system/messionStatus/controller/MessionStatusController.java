package com.aaa.project.system.messionStatus.controller;

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
import com.aaa.project.system.messionStatus.domain.MessionStatus;
import com.aaa.project.system.messionStatus.service.IMessionStatusService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 任务状态 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/messionStatus")
public class MessionStatusController extends BaseController
{
    private String prefix = "system/messionStatus";
	
	@Autowired
	private IMessionStatusService messionStatusService;
	
	@RequiresPermissions("system:messionStatus:view")
	@GetMapping()
	public String messionStatus()
	{
	    return prefix + "/messionStatus";
	}
	
	/**
	 * 查询任务状态列表
	 */
	@RequiresPermissions("system:messionStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MessionStatus messionStatus)
	{
		startPage();
        List<MessionStatus> list = messionStatusService.selectMessionStatusList(messionStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务状态列表
	 */
	@RequiresPermissions("system:messionStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MessionStatus messionStatus)
    {
    	List<MessionStatus> list = messionStatusService.selectMessionStatusList(messionStatus);
        ExcelUtil<MessionStatus> util = new ExcelUtil<MessionStatus>(MessionStatus.class);
        return util.exportExcel(list, "messionStatus");
    }
	
	/**
	 * 新增任务状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务状态
	 */
	@RequiresPermissions("system:messionStatus:add")
	@Log(title = "任务状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MessionStatus messionStatus)
	{		
		return toAjax(messionStatusService.insertMessionStatus(messionStatus));
	}

	/**
	 * 修改任务状态
	 */
	@GetMapping("/edit/{messionStatusId}")
	public String edit(@PathVariable("messionStatusId") Integer messionStatusId, ModelMap mmap)
	{
		MessionStatus messionStatus = messionStatusService.selectMessionStatusById(messionStatusId);
		mmap.put("messionStatus", messionStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务状态
	 */
	@RequiresPermissions("system:messionStatus:edit")
	@Log(title = "任务状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MessionStatus messionStatus)
	{		
		return toAjax(messionStatusService.updateMessionStatus(messionStatus));
	}
	
	/**
	 * 删除任务状态
	 */
	@RequiresPermissions("system:messionStatus:remove")
	@Log(title = "任务状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(messionStatusService.deleteMessionStatusByIds(ids));
	}
	
}
