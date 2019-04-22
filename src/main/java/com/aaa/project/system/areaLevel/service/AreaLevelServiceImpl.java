package com.aaa.project.system.areaLevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.areaLevel.mapper.AreaLevelMapper;
import com.aaa.project.system.areaLevel.domain.AreaLevel;
import com.aaa.project.system.areaLevel.service.IAreaLevelService;
import com.aaa.common.support.Convert;

/**
 * 地域级别(省市县) 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class AreaLevelServiceImpl implements IAreaLevelService 
{
	@Autowired
	private AreaLevelMapper areaLevelMapper;

	/**
     * 查询地域级别(省市县)信息
     * 
     * @param areaLevelId 地域级别(省市县)ID
     * @return 地域级别(省市县)信息
     */
    @Override
	public AreaLevel selectAreaLevelById(Integer areaLevelId)
	{
	    return areaLevelMapper.selectAreaLevelById(areaLevelId);
	}
	
	/**
     * 查询地域级别(省市县)列表
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 地域级别(省市县)集合
     */
	@Override
	public List<AreaLevel> selectAreaLevelList(AreaLevel areaLevel)
	{
	    return areaLevelMapper.selectAreaLevelList(areaLevel);
	}
	
    /**
     * 新增地域级别(省市县)
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 结果
     */
	@Override
	public int insertAreaLevel(AreaLevel areaLevel)
	{
	    return areaLevelMapper.insertAreaLevel(areaLevel);
	}
	
	/**
     * 修改地域级别(省市县)
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 结果
     */
	@Override
	public int updateAreaLevel(AreaLevel areaLevel)
	{
	    return areaLevelMapper.updateAreaLevel(areaLevel);
	}

	/**
     * 删除地域级别(省市县)对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAreaLevelByIds(String ids)
	{
		return areaLevelMapper.deleteAreaLevelByIds(Convert.toStrArray(ids));
	}
	
}
