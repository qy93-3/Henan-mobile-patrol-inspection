package com.aaa.project.system.resourcesCycleType.service;

import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import java.util.List;

/**
 * 资源巡检周期类型月份 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IResourcesCycleTypeService 
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
     * 删除资源巡检周期类型月份信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteResourcesCycleTypeByIds(String ids);
	
}
