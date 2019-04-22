package com.aaa.project.system.routingProject.controller;

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
import com.aaa.project.system.routingProject.domain.RoutingProject;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检资源项目 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/routingProject")
public class RoutingProjectController extends BaseController
{
    private String prefix = "system/routingProject";
	
	@Autowired
	private IRoutingProjectService routingProjectService;
	
	@RequiresPermissions("system:routingProject:view")
	@GetMapping()
	public String routingProject()
	{
	    return prefix + "/routingProject";
	}
	
	/**
	 * 查询巡检资源项目列表
	 */
	@RequiresPermissions("system:routingProject:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RoutingProject routingProject)
	{
		startPage();
        List<RoutingProject> list = routingProjectService.selectRoutingProjectList(routingProject);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检资源项目列表
	 */
	@RequiresPermissions("system:routingProject:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RoutingProject routingProject)
    {
    	List<RoutingProject> list = routingProjectService.selectRoutingProjectList(routingProject);
        ExcelUtil<RoutingProject> util = new ExcelUtil<RoutingProject>(RoutingProject.class);
        return util.exportExcel(list, "routingProject");
    }
	
	/**
	 * 新增巡检资源项目
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检资源项目
	 */
	@RequiresPermissions("system:routingProject:add")
	@Log(title = "巡检资源项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RoutingProject routingProject)
	{		
		return toAjax(routingProjectService.insertRoutingProject(routingProject));
	}

	/**
	 * 修改巡检资源项目
	 */
	@GetMapping("/edit/{routingProjectId}")
	public String edit(@PathVariable("routingProjectId") Integer routingProjectId, ModelMap mmap)
	{
		RoutingProject routingProject = routingProjectService.selectRoutingProjectById(routingProjectId);
		mmap.put("routingProject", routingProject);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检资源项目
	 */
	@RequiresPermissions("system:routingProject:edit")
	@Log(title = "巡检资源项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RoutingProject routingProject)
	{		
		return toAjax(routingProjectService.updateRoutingProject(routingProject));
	}
	
	/**
	 * 删除巡检资源项目
	 */
	@RequiresPermissions("system:routingProject:remove")
	@Log(title = "巡检资源项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(routingProjectService.deleteRoutingProjectByIds(ids));
	}
	
}
