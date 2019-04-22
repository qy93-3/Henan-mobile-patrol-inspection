package com.aaa.project.system.dangerLevel.controller;

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
import com.aaa.project.system.dangerLevel.domain.DangerLevel;
import com.aaa.project.system.dangerLevel.service.IDangerLevelService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源隐患严重程度 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/dangerLevel")
public class DangerLevelController extends BaseController
{
    private String prefix = "system/dangerLevel";
	
	@Autowired
	private IDangerLevelService dangerLevelService;
	
	@RequiresPermissions("system:dangerLevel:view")
	@GetMapping()
	public String dangerLevel()
	{
	    return prefix + "/dangerLevel";
	}
	
	/**
	 * 查询巡检资源隐患严重程度列表
	 */
	@RequiresPermissions("system:dangerLevel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DangerLevel dangerLevel)
	{
		startPage();
        List<DangerLevel> list = dangerLevelService.selectDangerLevelList(dangerLevel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源隐患严重程度列表
	 */
	@RequiresPermissions("system:dangerLevel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DangerLevel dangerLevel)
    {
    	List<DangerLevel> list = dangerLevelService.selectDangerLevelList(dangerLevel);
        ExcelUtil<DangerLevel> util = new ExcelUtil<DangerLevel>(DangerLevel.class);
        return util.exportExcel(list, "dangerLevel");
    }
	
	/**
	 * 新增巡检资源隐患严重程度
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源隐患严重程度
	 */
	@RequiresPermissions("system:dangerLevel:add")
	@Log(title = "巡检资源隐患严重程度", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DangerLevel dangerLevel)
	{		
		return toAjax(dangerLevelService.insertDangerLevel(dangerLevel));
	}

	/**
	 * 修改巡检资源隐患严重程度
	 */
	@GetMapping("/edit/{dangerLevelId}")
	public String edit(@PathVariable("dangerLevelId") Integer dangerLevelId, ModelMap mmap)
	{
		DangerLevel dangerLevel = dangerLevelService.selectDangerLevelById(dangerLevelId);
		mmap.put("dangerLevel", dangerLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源隐患严重程度
	 */
	@RequiresPermissions("system:dangerLevel:edit")
	@Log(title = "巡检资源隐患严重程度", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DangerLevel dangerLevel)
	{		
		return toAjax(dangerLevelService.updateDangerLevel(dangerLevel));
	}
	
	/**
	 * 删除巡检资源隐患严重程度
	 */
	@RequiresPermissions("system:dangerLevel:remove")
	@Log(title = "巡检资源隐患严重程度", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dangerLevelService.deleteDangerLevelByIds(ids));
	}
	
}
