package com.aaa.project.system.planDay.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planDay.mapper.PlanDayMapper;
import com.aaa.project.system.planDay.domain.PlanDay;
import com.aaa.project.system.planDay.service.IPlanDayService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源日计划 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class PlanDayServiceImpl implements IPlanDayService 
{
	@Autowired
	private PlanDayMapper planDayMapper;

	/**
     * 查询巡检资源日计划信息
     * 
     * @param dayPlanId 巡检资源日计划ID
     * @return 巡检资源日计划信息
     */
    @Override
	public PlanDay selectPlanDayById(Integer dayPlanId)
	{
	    return planDayMapper.selectPlanDayById(dayPlanId);
	}
	
	/**
     * 查询巡检资源日计划列表
     * 
     * @param planDay 巡检资源日计划信息
     * @return 巡检资源日计划集合
     */
	@Override
	public List<PlanDay> selectPlanDayList(PlanDay planDay)
	{
	    return planDayMapper.selectPlanDayList(planDay);
	}
	
    /**
     * 新增巡检资源日计划
     * 
     * @param planDay 巡检资源日计划信息
     * @return 结果
     */
	@Override
	public int insertPlanDay(PlanDay planDay)
	{
	    return planDayMapper.insertPlanDay(planDay);
	}
	
	/**
     * 修改巡检资源日计划
     * 
     * @param planDay 巡检资源日计划信息
     * @return 结果
     */
	@Override
	public int updatePlanDay(PlanDay planDay)
	{
	    return planDayMapper.updatePlanDay(planDay);
	}

	/**
     * 删除巡检资源日计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanDayByIds(String ids)
	{
		return planDayMapper.deletePlanDayByIds(Convert.toStrArray(ids));
	}
	
}
