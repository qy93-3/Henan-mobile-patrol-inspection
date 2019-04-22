package com.aaa.project.system.dangerStatus.mapper;

import com.aaa.project.system.dangerStatus.domain.DangerStatus;
import java.util.List;	

/**
 * 巡检资源隐患解决状态 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface DangerStatusMapper 
{
	/**
     * 查询巡检资源隐患解决状态信息
     * 
     * @param dangerHandleId 巡检资源隐患解决状态ID
     * @return 巡检资源隐患解决状态信息
     */
	public DangerStatus selectDangerStatusById(Integer dangerHandleId);
	
	/**
     * 查询巡检资源隐患解决状态列表
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 巡检资源隐患解决状态集合
     */
	public List<DangerStatus> selectDangerStatusList(DangerStatus dangerStatus);
	
	/**
     * 新增巡检资源隐患解决状态
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 结果
     */
	public int insertDangerStatus(DangerStatus dangerStatus);
	
	/**
     * 修改巡检资源隐患解决状态
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 结果
     */
	public int updateDangerStatus(DangerStatus dangerStatus);
	
	/**
     * 删除巡检资源隐患解决状态
     * 
     * @param dangerHandleId 巡检资源隐患解决状态ID
     * @return 结果
     */
	public int deleteDangerStatusById(Integer dangerHandleId);
	
	/**
     * 批量删除巡检资源隐患解决状态
     * 
     * @param dangerHandleIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangerStatusByIds(String[] dangerHandleIds);
	
}