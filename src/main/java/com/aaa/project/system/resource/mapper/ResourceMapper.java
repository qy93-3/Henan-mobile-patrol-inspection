package com.aaa.project.system.resource.mapper;

import com.aaa.project.system.resource.domain.Resource;
import java.util.List;	

/**
 * 资源点 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ResourceMapper 
{
	/**
     * 查询资源点信息
     * 
     * @param resourceId 资源点ID
     * @return 资源点信息
     */
	public Resource selectResourceById(Long resourceId);
	
	/**
     * 查询资源点列表
     * 
     * @param resource 资源点信息
     * @return 资源点集合
     */
	public List<Resource> selectResourceList(Resource resource);
	
	/**
     * 新增资源点
     * 
     * @param resource 资源点信息
     * @return 结果
     */
	public int insertResource(Resource resource);
	
	/**
     * 修改资源点
     * 
     * @param resource 资源点信息
     * @return 结果
     */
	public int updateResource(Resource resource);
	
	/**
     * 删除资源点
     * 
     * @param resourceId 资源点ID
     * @return 结果
     */
	public int deleteResourceById(Long resourceId);
	
	/**
     * 批量删除资源点
     * 
     * @param resourceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteResourceByIds(String[] resourceIds);
	
}