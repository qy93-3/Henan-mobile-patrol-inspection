package com.aaa.project.system.routingProject.service;

import com.aaa.project.system.routingProject.domain.RoutingProject;
import java.util.List;

/**
 * 巡检资源项目 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IRoutingProjectService 
{
	/**
     * 查询巡检资源项目信息
     * 
     * @param routingProjectId 巡检资源项目ID
     * @return 巡检资源项目信息
     */
	public RoutingProject selectRoutingProjectById(Integer routingProjectId);
	
	/**
     * 查询巡检资源项目列表
     * 
     * @param routingProject 巡检资源项目信息
     * @return 巡检资源项目集合
     */
	public List<RoutingProject> selectRoutingProjectList(RoutingProject routingProject);
	
	/**
     * 新增巡检资源项目
     * 
     * @param routingProject 巡检资源项目信息
     * @return 结果
     */
	public int insertRoutingProject(RoutingProject routingProject);
	
	/**
     * 修改巡检资源项目
     * 
     * @param routingProject 巡检资源项目信息
     * @return 结果
     */
	public int updateRoutingProject(RoutingProject routingProject);
		
	/**
     * 删除巡检资源项目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRoutingProjectByIds(String ids);
	
}
