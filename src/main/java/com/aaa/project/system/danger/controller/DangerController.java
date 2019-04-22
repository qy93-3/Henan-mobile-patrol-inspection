package com.aaa.project.system.danger.controller;

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
import com.aaa.project.system.danger.domain.Danger;
import com.aaa.project.system.danger.service.IDangerService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源隐患 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/danger")
public class DangerController extends BaseController
{
    private String prefix = "system/danger";
	
	@Autowired
	private IDangerService dangerService;
	
	@RequiresPermissions("system:danger:view")
	@GetMapping()
	public String danger()
	{
	    return prefix + "/danger";
	}
	
	/**
	 * 查询巡检资源隐患列表
	 */
	@RequiresPermissions("system:danger:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Danger danger)
	{
		startPage();
        List<Danger> list = dangerService.selectDangerList(danger);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源隐患列表
	 */
	@RequiresPermissions("system:danger:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Danger danger)
    {
    	List<Danger> list = dangerService.selectDangerList(danger);
        ExcelUtil<Danger> util = new ExcelUtil<Danger>(Danger.class);
        return util.exportExcel(list, "danger");
    }
	
	/**
	 * 新增巡检资源隐患
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源隐患
	 */
	@RequiresPermissions("system:danger:add")
	@Log(title = "巡检资源隐患", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Danger danger)
	{		
		return toAjax(dangerService.insertDanger(danger));
	}

	/**
	 * 修改巡检资源隐患
	 */
	@GetMapping("/edit/{dangerId}")
	public String edit(@PathVariable("dangerId") Integer dangerId, ModelMap mmap)
	{
		Danger danger = dangerService.selectDangerById(dangerId);
		mmap.put("danger", danger);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源隐患
	 */
	@RequiresPermissions("system:danger:edit")
	@Log(title = "巡检资源隐患", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Danger danger)
	{		
		return toAjax(dangerService.updateDanger(danger));
	}
	
	/**
	 * 删除巡检资源隐患
	 */
	@RequiresPermissions("system:danger:remove")
	@Log(title = "巡检资源隐患", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangerService.deleteDangerByIds(ids));
	}
	
}
