package com.aaa.project.system.systemUser.mapper;

import com.aaa.project.system.systemUser.domain.SystemUser;
import java.util.List;	

/**
 * 用户 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface SystemUserMapper 
{
	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
	public SystemUser selectSystemUserById(Integer userId);
	
	/**
     * 查询用户列表
     * 
     * @param systemUser 用户信息
     * @return 用户集合
     */
	public List<SystemUser> selectSystemUserList(SystemUser systemUser);
	
	/**
     * 新增用户
     * 
     * @param systemUser 用户信息
     * @return 结果
     */
	public int insertSystemUser(SystemUser systemUser);
	
	/**
     * 修改用户
     * 
     * @param systemUser 用户信息
     * @return 结果
     */
	public int updateSystemUser(SystemUser systemUser);
	
	/**
     * 删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
	public int deleteSystemUserById(Integer userId);
	
	/**
     * 批量删除用户
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSystemUserByIds(String[] userIds);
	
}