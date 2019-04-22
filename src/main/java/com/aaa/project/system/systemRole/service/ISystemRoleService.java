package com.aaa.project.system.systemRole.service;

import com.aaa.project.system.systemRole.domain.SystemRole;
import java.util.List;

/**
 * 登录角色权限 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ISystemRoleService 
{
	/**
     * 查询登录角色权限信息
     * 
     * @param roleId 登录角色权限ID
     * @return 登录角色权限信息
     */
	public SystemRole selectSystemRoleById(Integer roleId);
	
	/**
     * 查询登录角色权限列表
     * 
     * @param systemRole 登录角色权限信息
     * @return 登录角色权限集合
     */
	public List<SystemRole> selectSystemRoleList(SystemRole systemRole);
	
	/**
     * 新增登录角色权限
     * 
     * @param systemRole 登录角色权限信息
     * @return 结果
     */
	public int insertSystemRole(SystemRole systemRole);
	
	/**
     * 修改登录角色权限
     * 
     * @param systemRole 登录角色权限信息
     * @return 结果
     */
	public int updateSystemRole(SystemRole systemRole);
		
	/**
     * 删除登录角色权限信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSystemRoleByIds(String ids);
	
}
