package com.aaa.project.system.planCalendar.mapper;

import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import java.util.List;	

/**
 * 每日计划分配(显示日历) 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface PlanCalendarMapper 
{
	/**
     * 查询每日计划分配(显示日历)信息
     * 
     * @param calendarId 每日计划分配(显示日历)ID
     * @return 每日计划分配(显示日历)信息
     */
	public PlanCalendar selectPlanCalendarById(Integer calendarId);
	
	/**
     * 查询每日计划分配(显示日历)列表
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 每日计划分配(显示日历)集合
     */
	public List<PlanCalendar> selectPlanCalendarList(PlanCalendar planCalendar);
	
	/**
     * 新增每日计划分配(显示日历)
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 结果
     */
	public int insertPlanCalendar(PlanCalendar planCalendar);
	
	/**
     * 修改每日计划分配(显示日历)
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 结果
     */
	public int updatePlanCalendar(PlanCalendar planCalendar);
	
	/**
     * 删除每日计划分配(显示日历)
     * 
     * @param calendarId 每日计划分配(显示日历)ID
     * @return 结果
     */
	public int deletePlanCalendarById(Integer calendarId);
	
	/**
     * 批量删除每日计划分配(显示日历)
     * 
     * @param calendarIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanCalendarByIds(String[] calendarIds);
	
}