package com.aaa.project.system.areaLevel.mapper;

import com.aaa.project.system.areaLevel.domain.AreaLevel;
import java.util.List;	

/**
 * 地域级别(省市县) 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface AreaLevelMapper 
{
	/**
     * 查询地域级别(省市县)信息
     * 
     * @param areaLevelId 地域级别(省市县)ID
     * @return 地域级别(省市县)信息
     */
	public AreaLevel selectAreaLevelById(Integer areaLevelId);
	
	/**
     * 查询地域级别(省市县)列表
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 地域级别(省市县)集合
     */
	public List<AreaLevel> selectAreaLevelList(AreaLevel areaLevel);
	
	/**
     * 新增地域级别(省市县)
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 结果
     */
	public int insertAreaLevel(AreaLevel areaLevel);
	
	/**
     * 修改地域级别(省市县)
     * 
     * @param areaLevel 地域级别(省市县)信息
     * @return 结果
     */
	public int updateAreaLevel(AreaLevel areaLevel);
	
	/**
     * 删除地域级别(省市县)
     * 
     * @param areaLevelId 地域级别(省市县)ID
     * @return 结果
     */
	public int deleteAreaLevelById(Integer areaLevelId);
	
	/**
     * 批量删除地域级别(省市县)
     * 
     * @param areaLevelIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAreaLevelByIds(String[] areaLevelIds);
	
}