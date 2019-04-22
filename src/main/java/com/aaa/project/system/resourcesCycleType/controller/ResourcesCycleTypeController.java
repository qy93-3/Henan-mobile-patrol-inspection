package com.aaa.project.system.resourcesCycleType.controller;

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
import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import com.aaa.project.system.resourcesCycleType.service.IResourcesCycleTypeService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 资源巡检周期类型月份 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/resourcesCycleType")
public class ResourcesCycleTypeController extends BaseController
{
    private String prefix = "system/resourcesCycleType";
	
	@Autowired
	private IResourcesCycleTypeService resourcesCycleTypeService;
	
	@RequiresPermissions("system:resourcesCycleType:view")
	@GetMapping()
	public String resourcesCycleType()
	{
	    return prefix + "/resourcesCycleType";
	}
	
	/**
	 * 查询资源巡检周期类型月份列表
	 */
	@RequiresPermissions("system:resourcesCycleType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ResourcesCycleType resourcesCycleType)
	{
		startPage();
        List<ResourcesCycleType> list = resourcesCycleTypeService.selectResourcesCycleTypeList(resourcesCycleType);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源巡检周期类型月份列表
	 */
	@RequiresPermissions("system:resourcesCycleType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ResourcesCycleType resourcesCycleType)
    {
    	List<ResourcesCycleType> list = resourcesCycleTypeService.selectResourcesCycleTypeList(resourcesCycleType);
        ExcelUtil<ResourcesCycleType> util = new ExcelUtil<ResourcesCycleType>(ResourcesCycleType.class);
        return util.exportExcel(list, "resourcesCycleType");
    }
	
	/**
	 * 新增资源巡检周期类型月份
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源巡检周期类型月份
	 */
	@RequiresPermissions("system:resourcesCycleType:add")
	@Log(title = "资源巡检周期类型月份", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ResourcesCycleType resourcesCycleType)
	{		
		return toAjax(resourcesCycleTypeService.insertResourcesCycleType(resourcesCycleType));
	}

	/**
	 * 修改资源巡检周期类型月份
	 */
	@GetMapping("/edit/{resourcesCycleId}")
	public String edit(@PathVariable("resourcesCycleId") Integer resourcesCycleId, ModelMap mmap)
	{
		ResourcesCycleType resourcesCycleType = resourcesCycleTypeService.selectResourcesCycleTypeById(resourcesCycleId);
		mmap.put("resourcesCycleType", resourcesCycleType);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源巡检周期类型月份
	 */
	@RequiresPermissions("system:resourcesCycleType:edit")
	@Log(title = "资源巡检周期类型月份", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ResourcesCycleType resourcesCycleType)
	{		
		return toAjax(resourcesCycleTypeService.updateResourcesCycleType(resourcesCycleType));
	}
	
	/**
	 * 删除资源巡检周期类型月份
	 */
	@RequiresPermissions("system:resourcesCycleType:remove")
	@Log(title = "资源巡检周期类型月份", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(resourcesCycleTypeService.deleteResourcesCycleTypeByIds(ids));
	}
	
}
