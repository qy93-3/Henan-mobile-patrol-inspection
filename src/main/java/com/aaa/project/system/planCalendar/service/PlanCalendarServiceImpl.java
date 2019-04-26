package com.aaa.project.system.planCalendar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planCalendar.mapper.PlanCalendarMapper;
import com.aaa.project.system.planCalendar.domain.PlanCalendar;
import com.aaa.project.system.planCalendar.service.IPlanCalendarService;
import com.aaa.common.support.Convert;

/**
 * 每日计划分配(显示日历) 服务层实现
 * 
 * @author aaa
 * @date 2019-04-24
 */
@Service
public class PlanCalendarServiceImpl implements IPlanCalendarService 
{
	@Autowired
	private PlanCalendarMapper planCalendarMapper;

	/**
     * 查询每日计划分配(显示日历)信息
     * 
     * @param calendarId 每日计划分配(显示日历)ID
     * @return 每日计划分配(显示日历)信息
     */
    @Override
	public PlanCalendar selectPlanCalendarById(Integer calendarId)
	{
	    return planCalendarMapper.selectPlanCalendarById(calendarId);
	}
	
	/**
     * 查询每日计划分配(显示日历)列表
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 每日计划分配(显示日历)集合
     */
	@Override
	public List<PlanCalendar> selectPlanCalendarList(PlanCalendar planCalendar)
	{
	    return planCalendarMapper.selectPlanCalendarList(planCalendar);
	}
	
    /**
     * 新增每日计划分配(显示日历)
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 结果
     */
	@Override
	public int insertPlanCalendar(PlanCalendar planCalendar)
	{
	    return planCalendarMapper.insertPlanCalendar(planCalendar);
	}
	
	/**
     * 修改每日计划分配(显示日历)
     * 
     * @param planCalendar 每日计划分配(显示日历)信息
     * @return 结果
     */
	@Override
	public int updatePlanCalendar(PlanCalendar planCalendar)
	{
	    return planCalendarMapper.updatePlanCalendar(planCalendar);
	}

	/**
     * 删除每日计划分配(显示日历)对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanCalendarByIds(String ids)
	{
		return planCalendarMapper.deletePlanCalendarByIds(Convert.toStrArray(ids));
	}
	
}
