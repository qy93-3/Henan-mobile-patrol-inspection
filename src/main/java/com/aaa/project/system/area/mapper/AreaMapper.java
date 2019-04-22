package com.aaa.project.system.area.mapper;

import com.aaa.project.system.area.domain.Area;
import java.util.List;	

/**
 * 地区 数据层
 * 
 * @author aaa
 * @date 2019-04-22
 */
public interface AreaMapper 
{
	/**
     * 查询地区信息
     * 
     * @param areaId 地区ID
     * @return 地区信息
     */
	public Area selectAreaById(Integer areaId);
	
	/**
     * 查询地区列表
     * 
     * @param area 地区信息
     * @return 地区集合
     */
	public List<Area> selectAreaList(Area area);
	
	/**
     * 新增地区
     * 
     * @param area 地区信息
     * @return 结果
     */
	public int insertArea(Area area);
	
	/**
     * 修改地区
     * 
     * @param area 地区信息
     * @return 结果
     */
	public int updateArea(Area area);
	
	/**
     * 删除地区
     * 
     * @param areaId 地区ID
     * @return 结果
     */
	public int deleteAreaById(Integer areaId);
	
	/**
     * 批量删除地区
     * 
     * @param areaIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAreaByIds(String[] areaIds);
	
}