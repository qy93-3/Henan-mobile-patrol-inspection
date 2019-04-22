package com.aaa.project.system.systemUser.controller;

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
import com.aaa.project.system.systemUser.domain.SystemUser;
import com.aaa.project.system.systemUser.service.ISystemUserService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 用户 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/systemUser")
public class SystemUserController extends BaseController
{
    private String prefix = "system/systemUser";
	
	@Autowired
	private ISystemUserService systemUserService;
	
	@RequiresPermissions("system:systemUser:view")
	@GetMapping()
	public String systemUser()
	{
	    return prefix + "/systemUser";
	}
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("system:systemUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SystemUser systemUser)
	{
		startPage();
        List<SystemUser> list = systemUserService.selectSystemUserList(systemUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户列表
	 */
	@RequiresPermissions("system:systemUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SystemUser systemUser)
    {
    	List<SystemUser> list = systemUserService.selectSystemUserList(systemUser);
        ExcelUtil<SystemUser> util = new ExcelUtil<SystemUser>(SystemUser.class);
        return util.exportExcel(list, "systemUser");
    }
	
	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("system:systemUser:add")
	@Log(title = "用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SystemUser systemUser)
	{		
		return toAjax(systemUserService.insertSystemUser(systemUser));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
	{
		SystemUser systemUser = systemUserService.selectSystemUserById(userId);
		mmap.put("systemUser", systemUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("system:systemUser:edit")
	@Log(title = "用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SystemUser systemUser)
	{		
		return toAjax(systemUserService.updateSystemUser(systemUser));
	}
	
	/**
	 * 删除用户
	 */
	@RequiresPermissions("system:systemUser:remove")
	@Log(title = "用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(systemUserService.deleteSystemUserByIds(ids));
	}
	
}
