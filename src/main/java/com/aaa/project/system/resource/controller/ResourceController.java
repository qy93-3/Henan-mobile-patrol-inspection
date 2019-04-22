package com.aaa.project.system.resource.controller;

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
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 资源点 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController extends BaseController
{
    private String prefix = "system/resource";
	
	@Autowired
	private IResourceService resourceService;
	
	@RequiresPermissions("system:resource:view")
	@GetMapping()
	public String resource()
	{
	    return prefix + "/resource";
	}
	
	/**
	 * 查询资源点列表
	 */
	@RequiresPermissions("system:resource:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Resource resource)
	{
		startPage();
        List<Resource> list = resourceService.selectResourceList(resource);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源点列表
	 */
	@RequiresPermissions("system:resource:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Resource resource)
    {
    	List<Resource> list = resourceService.selectResourceList(resource);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        return util.exportExcel(list, "resource");
    }
	
	/**
	 * 新增资源点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源点
	 */
	@RequiresPermissions("system:resource:add")
	@Log(title = "资源点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Resource resource)
	{		
		return toAjax(resourceService.insertResource(resource));
	}

	/**
	 * 修改资源点
	 */
	@GetMapping("/edit/{resourceId}")
	public String edit(@PathVariable("resourceId") Long resourceId, ModelMap mmap)
	{
		Resource resource = resourceService.selectResourceById(resourceId);
		mmap.put("resource", resource);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源点
	 */
	@RequiresPermissions("system:resource:edit")
	@Log(title = "资源点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Resource resource)
	{		
		return toAjax(resourceService.updateResource(resource));
	}
	
	/**
	 * 删除资源点
	 */
	@RequiresPermissions("system:resource:remove")
	@Log(title = "资源点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(resourceService.deleteResourceByIds(ids));
	}
	
}
