package com.aaa.project.system.resource.service;

import com.aaa.project.system.resource.domain.Resource;
import java.util.List;

/**
 * 资源点 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IResourceService 
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
     * 查询有驻点资源点列表
     *
     * @param resource 资源点信息
     * @return 资源点集合
     */
	public List<Resource> selectResourceListHasDis(Resource resource);

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
     * 删除资源点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteResourceByIds(String ids);

}
