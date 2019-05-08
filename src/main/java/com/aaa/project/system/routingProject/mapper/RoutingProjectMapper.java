package com.aaa.project.system.routingProject.mapper;

import com.aaa.project.system.routingProject.domain.RoutingProject;
import java.util.List;	

/**
 * 巡检资源项目 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface RoutingProjectMapper 
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
     * 删除巡检资源项目
     * 
     * @param routingProjectId 巡检资源项目ID
     * @return 结果
     */
	public int deleteRoutingProjectById(Integer routingProjectId);
	
	/**
     * 批量删除巡检资源项目
     * 
     * @param routingProjectIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteRoutingProjectByIds(String[] routingProjectIds);

	/**
	 * 查询不在指定id中的巡检资源项目
	 * @param ids 需要排除的数据ID
	 * @return
	 */
	public List<RoutingProject> selectRoutingProjectListNotIn(String ids);
	
}