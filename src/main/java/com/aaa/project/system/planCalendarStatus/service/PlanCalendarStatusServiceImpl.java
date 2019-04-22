package com.aaa.project.system.planCalendarStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planCalendarStatus.mapper.PlanCalendarStatusMapper;
import com.aaa.project.system.planCalendarStatus.domain.PlanCalendarStatus;
import com.aaa.project.system.planCalendarStatus.service.IPlanCalendarStatusService;
import com.aaa.common.support.Convert;

/**
 * 日历每日状态(日历上显示) 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class PlanCalendarStatusServiceImpl implements IPlanCalendarStatusService 
{
	@Autowired
	private PlanCalendarStatusMapper planCalendarStatusMapper;

	/**
     * 查询日历每日状态(日历上显示)信息
     * 
     * @param monthStatusId 日历每日状态(日历上显示)ID
     * @return 日历每日状态(日历上显示)信息
     */
    @Override
	public PlanCalendarStatus selectPlanCalendarStatusById(Integer monthStatusId)
	{
	    return planCalendarStatusMapper.selectPlanCalendarStatusById(monthStatusId);
	}
	
	/**
     * 查询日历每日状态(日历上显示)列表
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 日历每日状态(日历上显示)集合
     */
	@Override
	public List<PlanCalendarStatus> selectPlanCalendarStatusList(PlanCalendarStatus planCalendarStatus)
	{
	    return planCalendarStatusMapper.selectPlanCalendarStatusList(planCalendarStatus);
	}
	
    /**
     * 新增日历每日状态(日历上显示)
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 结果
     */
	@Override
	public int insertPlanCalendarStatus(PlanCalendarStatus planCalendarStatus)
	{
	    return planCalendarStatusMapper.insertPlanCalendarStatus(planCalendarStatus);
	}
	
	/**
     * 修改日历每日状态(日历上显示)
     * 
     * @param planCalendarStatus 日历每日状态(日历上显示)信息
     * @return 结果
     */
	@Override
	public int updatePlanCalendarStatus(PlanCalendarStatus planCalendarStatus)
	{
	    return planCalendarStatusMapper.updatePlanCalendarStatus(planCalendarStatus);
	}

	/**
     * 删除日历每日状态(日历上显示)对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanCalendarStatusByIds(String ids)
	{
		return planCalendarStatusMapper.deletePlanCalendarStatusByIds(Convert.toStrArray(ids));
	}
	
}
