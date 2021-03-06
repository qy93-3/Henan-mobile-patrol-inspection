package com.aaa.project.system.dangerLevel.service;

import com.aaa.project.system.dangerLevel.domain.DangerLevel;
import java.util.List;

/**
 * 巡检资源隐患严重程度 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IDangerLevelService 
{
	/**
     * 查询巡检资源隐患严重程度信息
     * 
     * @param dangerLevelId 巡检资源隐患严重程度ID
     * @return 巡检资源隐患严重程度信息
     */
	public DangerLevel selectDangerLevelById(Integer dangerLevelId);
	
	/**
     * 查询巡检资源隐患严重程度列表
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 巡检资源隐患严重程度集合
     */
	public List<DangerLevel> selectDangerLevelList(DangerLevel dangerLevel);
	
	/**
     * 新增巡检资源隐患严重程度
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 结果
     */
	public int insertDangerLevel(DangerLevel dangerLevel);
	
	/**
     * 修改巡检资源隐患严重程度
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 结果
     */
	public int updateDangerLevel(DangerLevel dangerLevel);
		
	/**
     * 删除巡检资源隐患严重程度信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangerLevelByIds(String ids);
	
}
