package com.aaa.project.system.resource.mapper;

import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.site.domain.Site;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
	 * 释放资源
	 * @param resource
	 * @return
	 */
	public int cancelDistribute(Resource resource);

	/**
	 * 释放日计划资源
	 * @param resource
	 * @return
	 */
	public int relaseResources(Resource resource);

	/**
     * 批量删除资源点
     * 
     * @param resourceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteResourceByIds(String[] resourceIds);

	/**
	 * 查询有驻点并且日期差大于周期的资源点
	 * @param nowDate
	 * @param stagnationId
	 * @return
	 */
	public List<Resource> selectResourceHasDate(@Param("nowDate") Date nowDate, @Param("resourceStagantionCompany")int stagnationId);

}