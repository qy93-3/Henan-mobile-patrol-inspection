package com.aaa.project.system.systemRole.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.systemRole.mapper.SystemRoleMapper;
import com.aaa.project.system.systemRole.domain.SystemRole;
import com.aaa.project.system.systemRole.service.ISystemRoleService;
import com.aaa.common.support.Convert;

/**
 * 登录角色权限 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class SystemRoleServiceImpl implements ISystemRoleService 
{
	@Autowired
	private SystemRoleMapper systemRoleMapper;

	/**
     * 查询登录角色权限信息
     * 
     * @param roleId 登录角色权限ID
     * @return 登录角色权限信息
     */
    @Override
	public SystemRole selectSystemRoleById(Integer roleId)
	{
	    return systemRoleMapper.selectSystemRoleById(roleId);
	}
	
	/**
     * 查询登录角色权限列表
     * 
     * @param systemRole 登录角色权限信息
     * @return 登录角色权限集合
     */
	@Override
	public List<SystemRole> selectSystemRoleList(SystemRole systemRole)
	{
	    return systemRoleMapper.selectSystemRoleList(systemRole);
	}
	
    /**
     * 新增登录角色权限
     * 
     * @param systemRole 登录角色权限信息
     * @return 结果
     */
	@Override
	public int insertSystemRole(SystemRole systemRole)
	{
	    return systemRoleMapper.insertSystemRole(systemRole);
	}
	
	/**
     * 修改登录角色权限
     * 
     * @param systemRole 登录角色权限信息
     * @return 结果
     */
	@Override
	public int updateSystemRole(SystemRole systemRole)
	{
	    return systemRoleMapper.updateSystemRole(systemRole);
	}

	/**
     * 删除登录角色权限对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemRoleByIds(String ids)
	{
		return systemRoleMapper.deleteSystemRoleByIds(Convert.toStrArray(ids));
	}
	
}
