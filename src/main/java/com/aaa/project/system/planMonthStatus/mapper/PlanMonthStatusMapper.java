package com.aaa.project.system.planMonthStatus.mapper;

import com.aaa.project.system.planMonthStatus.domain.PlanMonthStatus;
import java.util.List;	

/**
 * 巡检资源月计划状态 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface PlanMonthStatusMapper 
{
	/**
     * 查询巡检资源月计划状态信息
     * 
     * @param monthStatusId 巡检资源月计划状态ID
     * @return 巡检资源月计划状态信息
     */
	public PlanMonthStatus selectPlanMonthStatusById(Integer monthStatusId);
	
	/**
     * 查询巡检资源月计划状态列表
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 巡检资源月计划状态集合
     */
	public List<PlanMonthStatus> selectPlanMonthStatusList(PlanMonthStatus planMonthStatus);
	
	/**
     * 新增巡检资源月计划状态
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 结果
     */
	public int insertPlanMonthStatus(PlanMonthStatus planMonthStatus);
	
	/**
     * 修改巡检资源月计划状态
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 结果
     */
	public int updatePlanMonthStatus(PlanMonthStatus planMonthStatus);
	
	/**
     * 删除巡检资源月计划状态
     * 
     * @param monthStatusId 巡检资源月计划状态ID
     * @return 结果
     */
	public int deletePlanMonthStatusById(Integer monthStatusId);
	
	/**
     * 批量删除巡检资源月计划状态
     * 
     * @param monthStatusIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanMonthStatusByIds(String[] monthStatusIds);
	
}