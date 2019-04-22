package com.aaa.project.system.systemUser.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.systemUser.mapper.SystemUserMapper;
import com.aaa.project.system.systemUser.domain.SystemUser;
import com.aaa.project.system.systemUser.service.ISystemUserService;
import com.aaa.common.support.Convert;

/**
 * 用户 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService 
{
	@Autowired
	private SystemUserMapper systemUserMapper;

	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
	public SystemUser selectSystemUserById(Integer userId)
	{
	    return systemUserMapper.selectSystemUserById(userId);
	}
	
	/**
     * 查询用户列表
     * 
     * @param systemUser 用户信息
     * @return 用户集合
     */
	@Override
	public List<SystemUser> selectSystemUserList(SystemUser systemUser)
	{
	    return systemUserMapper.selectSystemUserList(systemUser);
	}
	
    /**
     * 新增用户
     * 
     * @param systemUser 用户信息
     * @return 结果
     */
	@Override
	public int insertSystemUser(SystemUser systemUser)
	{
	    return systemUserMapper.insertSystemUser(systemUser);
	}
	
	/**
     * 修改用户
     * 
     * @param systemUser 用户信息
     * @return 结果
     */
	@Override
	public int updateSystemUser(SystemUser systemUser)
	{
	    return systemUserMapper.updateSystemUser(systemUser);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSystemUserByIds(String ids)
	{
		return systemUserMapper.deleteSystemUserByIds(Convert.toStrArray(ids));
	}
	
}
