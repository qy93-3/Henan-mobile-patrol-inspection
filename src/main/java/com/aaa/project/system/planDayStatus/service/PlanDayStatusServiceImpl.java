package com.aaa.project.system.planDayStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planDayStatus.mapper.PlanDayStatusMapper;
import com.aaa.project.system.planDayStatus.domain.PlanDayStatus;
import com.aaa.project.system.planDayStatus.service.IPlanDayStatusService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源日计划状态 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class PlanDayStatusServiceImpl implements IPlanDayStatusService 
{
	@Autowired
	private PlanDayStatusMapper planDayStatusMapper;

	/**
     * 查询巡检资源日计划状态信息
     * 
     * @param dayStatusId 巡检资源日计划状态ID
     * @return 巡检资源日计划状态信息
     */
    @Override
	public PlanDayStatus selectPlanDayStatusById(Integer dayStatusId)
	{
	    return planDayStatusMapper.selectPlanDayStatusById(dayStatusId);
	}
	
	/**
     * 查询巡检资源日计划状态列表
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 巡检资源日计划状态集合
     */
	@Override
	public List<PlanDayStatus> selectPlanDayStatusList(PlanDayStatus planDayStatus)
	{
	    return planDayStatusMapper.selectPlanDayStatusList(planDayStatus);
	}
	
    /**
     * 新增巡检资源日计划状态
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 结果
     */
	@Override
	public int insertPlanDayStatus(PlanDayStatus planDayStatus)
	{
	    return planDayStatusMapper.insertPlanDayStatus(planDayStatus);
	}
	
	/**
     * 修改巡检资源日计划状态
     * 
     * @param planDayStatus 巡检资源日计划状态信息
     * @return 结果
     */
	@Override
	public int updatePlanDayStatus(PlanDayStatus planDayStatus)
	{
	    return planDayStatusMapper.updatePlanDayStatus(planDayStatus);
	}

	/**
     * 删除巡检资源日计划状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanDayStatusByIds(String ids)
	{
		return planDayStatusMapper.deletePlanDayStatusByIds(Convert.toStrArray(ids));
	}
	
}
