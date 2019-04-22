package com.aaa.project.system.planMonthStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planMonthStatus.mapper.PlanMonthStatusMapper;
import com.aaa.project.system.planMonthStatus.domain.PlanMonthStatus;
import com.aaa.project.system.planMonthStatus.service.IPlanMonthStatusService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源月计划状态 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class PlanMonthStatusServiceImpl implements IPlanMonthStatusService 
{
	@Autowired
	private PlanMonthStatusMapper planMonthStatusMapper;

	/**
     * 查询巡检资源月计划状态信息
     * 
     * @param monthStatusId 巡检资源月计划状态ID
     * @return 巡检资源月计划状态信息
     */
    @Override
	public PlanMonthStatus selectPlanMonthStatusById(Integer monthStatusId)
	{
	    return planMonthStatusMapper.selectPlanMonthStatusById(monthStatusId);
	}
	
	/**
     * 查询巡检资源月计划状态列表
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 巡检资源月计划状态集合
     */
	@Override
	public List<PlanMonthStatus> selectPlanMonthStatusList(PlanMonthStatus planMonthStatus)
	{
	    return planMonthStatusMapper.selectPlanMonthStatusList(planMonthStatus);
	}
	
    /**
     * 新增巡检资源月计划状态
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 结果
     */
	@Override
	public int insertPlanMonthStatus(PlanMonthStatus planMonthStatus)
	{
	    return planMonthStatusMapper.insertPlanMonthStatus(planMonthStatus);
	}
	
	/**
     * 修改巡检资源月计划状态
     * 
     * @param planMonthStatus 巡检资源月计划状态信息
     * @return 结果
     */
	@Override
	public int updatePlanMonthStatus(PlanMonthStatus planMonthStatus)
	{
	    return planMonthStatusMapper.updatePlanMonthStatus(planMonthStatus);
	}

	/**
     * 删除巡检资源月计划状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanMonthStatusByIds(String ids)
	{
		return planMonthStatusMapper.deletePlanMonthStatusByIds(Convert.toStrArray(ids));
	}
	
}
