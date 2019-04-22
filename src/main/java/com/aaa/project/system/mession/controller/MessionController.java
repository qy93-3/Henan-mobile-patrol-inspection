package com.aaa.project.system.mession.controller;

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
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.mession.service.IMessionService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

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
	
}
