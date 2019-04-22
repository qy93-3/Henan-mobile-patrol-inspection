package com.aaa.project.system.change.controller;

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
import com.aaa.project.system.change.domain.Change;
import com.aaa.project.system.change.service.IChangeService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 月计划变更申请 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/change")
public class ChangeController extends BaseController
{
    private String prefix = "system/change";
	
	@Autowired
	private IChangeService changeService;
	
	@RequiresPermissions("system:change:view")
	@GetMapping()
	public String change()
	{
	    return prefix + "/change";
	}
	
	/**
	 * 查询月计划变更申请列表
	 */
	@RequiresPermissions("system:change:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Change change)
	{
		startPage();
        List<Change> list = changeService.selectChangeList(change);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出月计划变更申请列表
	 */
	@RequiresPermissions("system:change:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Change change)
    {
    	List<Change> list = changeService.selectChangeList(change);
        ExcelUtil<Change> util = new ExcelUtil<Change>(Change.class);
        return util.exportExcel(list, "change");
    }
	
	/**
	 * 新增月计划变更申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存月计划变更申请
	 */
	@RequiresPermissions("system:change:add")
	@Log(title = "月计划变更申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Change change)
	{		
		return toAjax(changeService.insertChange(change));
	}

	/**
	 * 修改月计划变更申请
	 */
	@GetMapping("/edit/{changeId}")
	public String edit(@PathVariable("changeId") Integer changeId, ModelMap mmap)
	{
		Change change = changeService.selectChangeById(changeId);
		mmap.put("change", change);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存月计划变更申请
	 */
	@RequiresPermissions("system:change:edit")
	@Log(title = "月计划变更申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Change change)
	{		
		return toAjax(changeService.updateChange(change));
	}
	
	/**
	 * 删除月计划变更申请
	 */
	@RequiresPermissions("system:change:remove")
	@Log(title = "月计划变更申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(changeService.deleteChangeByIds(ids));
	}
	
}
