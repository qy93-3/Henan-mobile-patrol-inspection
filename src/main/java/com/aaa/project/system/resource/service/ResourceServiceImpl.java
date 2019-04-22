package com.aaa.project.system.resource.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.resource.mapper.ResourceMapper;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.common.support.Convert;

/**
 * 资源点 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class ResourceServiceImpl implements IResourceService 
{
	@Autowired
	private ResourceMapper resourceMapper;

	/**
     * 查询资源点信息
     * 
     * @param resourceId 资源点ID
     * @return 资源点信息
     */
    @Override
	public Resource selectResourceById(Long resourceId)
	{
	    return resourceMapper.selectResourceById(resourceId);
	}
	
	/**
     * 查询资源点列表
     * 
     * @param resource 资源点信息
     * @return 资源点集合
     */
	@Override
	public List<Resource> selectResourceList(Resource resource)
	{
	    return resourceMapper.selectResourceList(resource);
	}
	
    /**
     * 新增资源点
     * 
     * @param resource 资源点信息
     * @return 结果
     */
	@Override
	public int insertResource(Resource resource)
	{
	    return resourceMapper.insertResource(resource);
	}
	
	/**
     * 修改资源点
     * 
     * @param resource 资源点信息
     * @return 结果
     */
	@Override
	public int updateResource(Resource resource)
	{
	    return resourceMapper.updateResource(resource);
	}

	/**
     * 删除资源点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteResourceByIds(String ids)
	{
		return resourceMapper.deleteResourceByIds(Convert.toStrArray(ids));
	}
	
}
