package com.aaa.project.system.check.controller;

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
import com.aaa.project.system.check.domain.Check;
import com.aaa.project.system.check.service.ICheckService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 月计划审核 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/check")
public class CheckController extends BaseController
{
    private String prefix = "system/check";
	
	@Autowired
	private ICheckService checkService;
	
	@RequiresPermissions("system:check:view")
	@GetMapping()
	public String check()
	{
	    return prefix + "/check";
	}
	
	/**
	 * 查询月计划审核列表
	 */
	@RequiresPermissions("system:check:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Check check)
	{
		startPage();
        List<Check> list = checkService.selectCheckList(check);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出月计划审核列表
	 */
	@RequiresPermissions("system:check:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Check check)
    {
    	List<Check> list = checkService.selectCheckList(check);
        ExcelUtil<Check> util = new ExcelUtil<Check>(Check.class);
        return util.exportExcel(list, "check");
    }
	
	/**
	 * 新增月计划审核
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存月计划审核
	 */
	@RequiresPermissions("system:check:add")
	@Log(title = "月计划审核", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Check check)
	{		
		return toAjax(checkService.insertCheck(check));
	}

	/**
	 * 修改月计划审核
	 */
	@GetMapping("/edit/{checkId}")
	public String edit(@PathVariable("checkId") Integer checkId, ModelMap mmap)
	{
		Check check = checkService.selectCheckById(checkId);
		mmap.put("check", check);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存月计划审核
	 */
	@RequiresPermissions("system:check:edit")
	@Log(title = "月计划审核", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Check check)
	{		
		return toAjax(checkService.updateCheck(check));
	}
	
	/**
	 * 删除月计划审核
	 */
	@RequiresPermissions("system:check:remove")
	@Log(title = "月计划审核", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(checkService.deleteCheckByIds(ids));
	}
	
}
