package com.aaa.project.system.planDayStatus.service;

import com.aaa.project.system.planDayStatus.domain.PlanDayStatus;
import java.util.List;

/**
 * 巡检资源日计划状态 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IPlanDayStatusService 
{
	/**
     * 查询巡检资源日计划状态信息
     * 
     * @param dayStatusId 巡检资源日计划状态ID
     * @return 巡检资源日计划状态信息
     */
	public PlanDayStatus selectPlanDayStatusById(Integer dayStatusId);
	
	/**
     * 查询巡检资源日计划状态列表
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 巡检资源日计划状态集合
     */
	public List<PlanDayStatus> selectPlanDayStatusList(PlanDayStatus planDayStatus);
	
	/**
     * 新增巡检资源日计划状态
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 结果
     */
	public int insertPlanDayStatus(PlanDayStatus planDayStatus);
	
	/**
     * 修改巡检资源日计划状态
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 结果
     */
	public int updatePlanDayStatus(PlanDayStatus planDayStatus);
		
	/**
     * 删除巡检资源日计划状态信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanDayStatusByIds(String ids);
	
}
