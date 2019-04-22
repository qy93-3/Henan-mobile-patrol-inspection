package com.aaa.project.system.planCalendarStatus.service;

import com.aaa.project.system.planCalendarStatus.domain.PlanCalendarStatus;
import java.util.List;

/**
 * 日历每日状态(日历上显示) 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IPlanCalendarStatusService 
{
	/**
     * 查询日历每日状态(日历上显示)信息
     * 
     * @param monthStatusId 日历每日状态(日历上显示)ID
     * @return 日历每日状态(日历上显示)信息
     */
	public PlanCalendarStatus selectPlanCalendarStatusById(Integer monthStatusId);
	
	/**
     * 查询日历每日状态(日历上显示)列表
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 日历每日状态(日历上显示)集合
     */
	public List<PlanCalendarStatus> selectPlanCalendarStatusList(PlanCalendarStatus planCalendarStatus);
	
	/**
     * 新增日历每日状态(日历上显示)
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 结果
     */
	public int insertPlanCalendarStatus(PlanCalendarStatus planCalendarStatus);
	
	/**
     * 修改日历每日状态(日历上显示)
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 结果
     */
	public int updatePlanCalendarStatus(PlanCalendarStatus planCalendarStatus);
		
	/**
     * 删除日历每日状态(日历上显示)信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePlanCalendarStatusByIds(String ids);
	
}
