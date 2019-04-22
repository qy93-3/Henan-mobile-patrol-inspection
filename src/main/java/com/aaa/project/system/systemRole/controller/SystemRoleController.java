package com.aaa.project.system.systemRole.controller;

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
import com.aaa.project.system.systemRole.domain.SystemRole;
import com.aaa.project.system.systemRole.service.ISystemRoleService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 登录角色权限 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/systemRole")
public class SystemRoleController extends BaseController
{
    private String prefix = "system/systemRole";
	
	@Autowired
	private ISystemRoleService systemRoleService;
	
	@RequiresPermissions("system:systemRole:view")
	@GetMapping()
	public String systemRole()
	{
	    return prefix + "/systemRole";
	}
	
	/**
	 * 查询登录角色权限列表
	 */
	@RequiresPermissions("system:systemRole:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SystemRole systemRole)
	{
		startPage();
        List<SystemRole> list = systemRoleService.selectSystemRoleList(systemRole);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出登录角色权限列表
	 */
	@RequiresPermissions("system:systemRole:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SystemRole systemRole)
    {
    	List<SystemRole> list = systemRoleService.selectSystemRoleList(systemRole);
        ExcelUtil<SystemRole> util = new ExcelUtil<SystemRole>(SystemRole.class);
        return util.exportExcel(list, "systemRole");
    }
	
	/**
	 * 新增登录角色权限
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存登录角色权限
	 */
	@RequiresPermissions("system:systemRole:add")
	@Log(title = "登录角色权限", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SystemRole systemRole)
	{		
		return toAjax(systemRoleService.insertSystemRole(systemRole));
	}

	/**
	 * 修改登录角色权限
	 */
	@GetMapping("/edit/{roleId}")
	public String edit(@PathVariable("roleId") Integer roleId, ModelMap mmap)
	{
		SystemRole systemRole = systemRoleService.selectSystemRoleById(roleId);
		mmap.put("systemRole", systemRole);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存登录角色权限
	 */
	@RequiresPermissions("system:systemRole:edit")
	@Log(title = "登录角色权限", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SystemRole systemRole)
	{		
		return toAjax(systemRoleService.updateSystemRole(systemRole));
	}
	
	/**
	 * 删除登录角色权限
	 */
	@RequiresPermissions("system:systemRole:remove")
	@Log(title = "登录角色权限", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(systemRoleService.deleteSystemRoleByIds(ids));
	}
	
}
