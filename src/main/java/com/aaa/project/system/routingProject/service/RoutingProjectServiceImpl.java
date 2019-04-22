package com.aaa.project.system.routingProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.routingProject.mapper.RoutingProjectMapper;
import com.aaa.project.system.routingProject.domain.RoutingProject;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源项目 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class RoutingProjectServiceImpl implements IRoutingProjectService 
{
	@Autowired
	private RoutingProjectMapper routingProjectMapper;

	/**
     * 查询巡检资源项目信息
     * 
     * @param routingProjectId 巡检资源项目ID
     * @return 巡检资源项目信息
     */
    @Override
	public RoutingProject selectRoutingProjectById(Integer routingProjectId)
	{
	    return routingProjectMapper.selectRoutingProjectById(routingProjectId);
	}
	
	/**
     * 查询巡检资源项目列表
     * 
     * @param routingProject 巡检资源项目信息
     * @return 巡检资源项目集合
     */
	@Override
	public List<RoutingProject> selectRoutingProjectList(RoutingProject routingProject)
	{
	    return routingProjectMapper.selectRoutingProjectList(routingProject);
	}
	
    /**
     * 新增巡检资源项目
     * 
     * @param routingProject 巡检资源项目信息
     * @return 结果
     */
	@Override
	public int insertRoutingProject(RoutingProject routingProject)
	{
	    return routingProjectMapper.insertRoutingProject(routingProject);
	}
	
	/**
     * 修改巡检资源项目
     * 
     * @param routingProject 巡检资源项目信息
     * @return 结果
     */
	@Override
	public int updateRoutingProject(RoutingProject routingProject)
	{
	    return routingProjectMapper.updateRoutingProject(routingProject);
	}

	/**
     * 删除巡检资源项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRoutingProjectByIds(String ids)
	{
		return routingProjectMapper.deleteRoutingProjectByIds(Convert.toStrArray(ids));
	}
	
}
