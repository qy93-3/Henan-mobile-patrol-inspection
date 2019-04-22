package com.aaa.project.system.messionStatus.service;

import com.aaa.project.system.messionStatus.domain.MessionStatus;
import java.util.List;

/**
 * 任务状态 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IMessionStatusService 
{
	/**
     * 查询任务状态信息
     * 
     * @param messionStatusId 任务状态ID
     * @return 任务状态信息
     */
	public MessionStatus selectMessionStatusById(Integer messionStatusId);
	
	/**
     * 查询任务状态列表
     * 
     * @param messionStatus 任务状态信息
     * @return 任务状态集合
     */
	public List<MessionStatus> selectMessionStatusList(MessionStatus messionStatus);
	
	/**
     * 新增任务状态
     * 
     * @param messionStatus 任务状态信息
     * @return 结果
     */
	public int insertMessionStatus(MessionStatus messionStatus);
	
	/**
     * 修改任务状态
     * 
     * @param messionStatus 任务状态信息
     * @return 结果
     */
	public int updateMessionStatus(MessionStatus messionStatus);
		
	/**
     * 删除任务状态信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMessionStatusByIds(String ids);
	
}
