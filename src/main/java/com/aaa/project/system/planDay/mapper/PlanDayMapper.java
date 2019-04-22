package com.aaa.project.system.planDay.mapper;

import com.aaa.project.system.planDay.domain.PlanDay;
import java.util.List;	

/**
 * 巡检资源日计划 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface PlanDayMapper 
{
	/**
     * 查询巡检资源日计划信息
     * 
     * @param dayPlanId 巡检资源日计划ID
     * @return 巡检资源日计划信息
     */
	public PlanDay selectPlanDayById(Integer dayPlanId);
	
	/**
     * 查询巡检资源日计划列表
     * 
     * @param planDay 巡检资源日计划信息
     * @return 巡检资源日计划集合
     */
	public List<PlanDay> selectPlanDayList(PlanDay planDay);
	
	/**
     * 新增巡检资源日计划
     * 
     * @param planDay 巡检资源日计划信息
     * @return 结果
     */
	public int insertPlanDay(PlanDay planDay);
	
	/**
     * 修改巡检资源日计划
     * 
     * @param planDay 巡检资源日计划信息
     * @return 结果
     */
	public int updatePlanDay(PlanDay planDay);
	
	/**
     * 删除巡检资源日计划
     * 
     * @param dayPlanId 巡检资源日计划ID
     * @return 结果
     */
	public int deletePlanDayById(Integer dayPlanId);
	
	/**
     * 批量删除巡检资源日计划
     * 
     * @param dayPlanIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanDayByIds(String[] dayPlanIds);
	
}