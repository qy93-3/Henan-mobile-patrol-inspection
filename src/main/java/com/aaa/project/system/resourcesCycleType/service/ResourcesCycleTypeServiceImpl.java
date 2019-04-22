package com.aaa.project.system.resourcesCycleType.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.resourcesCycleType.mapper.ResourcesCycleTypeMapper;
import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import com.aaa.project.system.resourcesCycleType.service.IResourcesCycleTypeService;
import com.aaa.common.support.Convert;

/**
 * 资源巡检周期类型月份 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class ResourcesCycleTypeServiceImpl implements IResourcesCycleTypeService 
{
	@Autowired
	private ResourcesCycleTypeMapper resourcesCycleTypeMapper;

	/**
     * 查询资源巡检周期类型月份信息
     * 
     * @param resourcesCycleId 资源巡检周期类型月份ID
     * @return 资源巡检周期类型月份信息
     */
    @Override
	public ResourcesCycleType selectResourcesCycleTypeById(Integer resourcesCycleId)
	{
	    return resourcesCycleTypeMapper.selectResourcesCycleTypeById(resourcesCycleId);
	}
	
	/**
     * 查询资源巡检周期类型月份列表
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 资源巡检周期类型月份集合
     */
	@Override
	public List<ResourcesCycleType> selectResourcesCycleTypeList(ResourcesCycleType resourcesCycleType)
	{
	    return resourcesCycleTypeMapper.selectResourcesCycleTypeList(resourcesCycleType);
	}
	
    /**
     * 新增资源巡检周期类型月份
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 结果
     */
	@Override
	public int insertResourcesCycleType(ResourcesCycleType resourcesCycleType)
	{
	    return resourcesCycleTypeMapper.insertResourcesCycleType(resourcesCycleType);
	}
	
	/**
     * 修改资源巡检周期类型月份
     * 
     * @param resourcesCycleType 资源巡检周期类型月份信息
     * @return 结果
     */
	@Override
	public int updateResourcesCycleType(ResourcesCycleType resourcesCycleType)
	{
	    return resourcesCycleTypeMapper.updateResourcesCycleType(resourcesCycleType);
	}

	/**
     * 删除资源巡检周期类型月份对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteResourcesCycleTypeByIds(String ids)
	{
		return resourcesCycleTypeMapper.deleteResourcesCycleTypeByIds(Convert.toStrArray(ids));
	}
	
}
