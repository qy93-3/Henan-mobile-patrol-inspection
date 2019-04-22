package com.aaa.project.system.areaLevel.controller;

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
import com.aaa.project.system.areaLevel.domain.AreaLevel;
import com.aaa.project.system.areaLevel.service.IAreaLevelService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 地域级别(省市县) 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/areaLevel")
public class AreaLevelController extends BaseController
{
    private String prefix = "system/areaLevel";
	
	@Autowired
	private IAreaLevelService areaLevelService;
	
	@RequiresPermissions("system:areaLevel:view")
	@GetMapping()
	public String areaLevel()
	{
	    return prefix + "/areaLevel";
	}
	
	/**
	 * 查询地域级别(省市县)列表
	 */
	@RequiresPermissions("system:areaLevel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AreaLevel areaLevel)
	{
		startPage();
        List<AreaLevel> list = areaLevelService.selectAreaLevelList(areaLevel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出地域级别(省市县)列表
	 */
	@RequiresPermissions("system:areaLevel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AreaLevel areaLevel)
    {
    	List<AreaLevel> list = areaLevelService.selectAreaLevelList(areaLevel);
        ExcelUtil<AreaLevel> util = new ExcelUtil<AreaLevel>(AreaLevel.class);
        return util.exportExcel(list, "areaLevel");
    }
	
	/**
	 * 新增地域级别(省市县)
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存地域级别(省市县)
	 */
	@RequiresPermissions("system:areaLevel:add")
	@Log(title = "地域级别(省市县)", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AreaLevel areaLevel)
	{		
		return toAjax(areaLevelService.insertAreaLevel(areaLevel));
	}

	/**
	 * 修改地域级别(省市县)
	 */
	@GetMapping("/edit/{areaLevelId}")
	public String edit(@PathVariable("areaLevelId") Integer areaLevelId, ModelMap mmap)
	{
		AreaLevel areaLevel = areaLevelService.selectAreaLevelById(areaLevelId);
		mmap.put("areaLevel", areaLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存地域级别(省市县)
	 */
	@RequiresPermissions("system:areaLevel:edit")
	@Log(title = "地域级别(省市县)", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AreaLevel areaLevel)
	{		
		return toAjax(areaLevelService.updateAreaLevel(areaLevel));
	}
	
	/**
	 * 删除地域级别(省市县)
	 */
	@RequiresPermissions("system:areaLevel:remove")
	@Log(title = "地域级别(省市县)", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(areaLevelService.deleteAreaLevelByIds(ids));
	}
	
}
