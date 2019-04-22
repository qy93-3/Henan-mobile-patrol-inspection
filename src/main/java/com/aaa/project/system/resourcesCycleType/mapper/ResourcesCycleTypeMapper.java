package com.aaa.project.system.resourcesCycleType.mapper;

import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import java.util.List;	

/**
 * 资源巡检周期类型月份 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ResourcesCycleTypeMapper 
{
	/**
     * 查询资源巡检周期类型月份信息
     * 
     * @param resourcesCycleId 资源巡检周期类型月份ID
     * @return 资源巡检周期类型月份信息
     */
	public ResourcesCycleType selectResourcesCycleTypeById(Integer resourcesCycleId);
	
	/**
     * 查询资源巡检周期类型月份列表
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 资源巡检周期类型月份集合
     */
	public List<ResourcesCycleType> selectResourcesCycleTypeList(ResourcesCycleType resourcesCycleType);
	
	/**
     * 新增资源巡检周期类型月份
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 结果
     */
	public int insertResourcesCycleType(ResourcesCycleType resourcesCycleType);
	
	/**
     * 修改资源巡检周期类型月份
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 结果
     */
	public int updateResourcesCycleType(ResourcesCycleType resourcesCycleType);
	
	/**
     * 删除资源巡检周期类型月份
     * 
     * @param resourcesCycleId 资源巡检周期类型月份ID
     * @return 结果
     */
	public int deleteResourcesCycleTypeById(Integer resourcesCycleId);
	
	/**
     * 批量删除资源巡检周期类型月份
     * 
     * @param resourcesCycleIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteResourcesCycleTypeByIds(String[] resourcesCycleIds);
	
}