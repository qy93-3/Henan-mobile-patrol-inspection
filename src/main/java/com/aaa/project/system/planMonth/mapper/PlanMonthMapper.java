package com.aaa.project.system.planMonth.mapper;

import com.aaa.project.system.planMonth.domain.PlanMonth;
import java.util.List;	

/**
 * 巡检资源月计划 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface PlanMonthMapper 
{
	/**
     * 查询巡检资源月计划信息
     * 
     * @param monthPlanId 巡检资源月计划ID
     * @return 巡检资源月计划信息
     */
	public PlanMonth selectPlanMonthById(Integer monthPlanId);
	
	/**
     * 查询巡检资源月计划列表
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 巡检资源月计划集合
     */
	public List<PlanMonth> selectPlanMonthList(PlanMonth planMonth);
	
	/**
     * 新增巡检资源月计划
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 结果
     */
	public int insertPlanMonth(PlanMonth planMonth);
	
	/**
     * 修改巡检资源月计划
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 结果
     */
	public int updatePlanMonth(PlanMonth planMonth);
	
	/**
     * 删除巡检资源月计划
     * 
     * @param monthPlanId 巡检资源月计划ID
     * @return 结果
     */
	public int deletePlanMonthById(Integer monthPlanId);
	
	/**
     * 批量删除巡检资源月计划
     * 
     * @param monthPlanIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanMonthByIds(String[] monthPlanIds);
	
}