package com.aaa.project.system.planMonth.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.planMonth.mapper.PlanMonthMapper;
import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源月计划 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class PlanMonthServiceImpl implements IPlanMonthService 
{
	@Autowired
	private PlanMonthMapper planMonthMapper;

	/**
     * 查询巡检资源月计划信息
     * 
     * @param monthPlanId 巡检资源月计划ID
     * @return 巡检资源月计划信息
     */
    @Override
	public PlanMonth selectPlanMonthById(Integer monthPlanId)
	{
	    return planMonthMapper.selectPlanMonthById(monthPlanId);
	}
	
	/**
     * 查询巡检资源月计划列表
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 巡检资源月计划集合
     */
	@Override
	public List<PlanMonth> selectPlanMonthList(PlanMonth planMonth)
	{
	    return planMonthMapper.selectPlanMonthList(planMonth);
	}
	
    /**
     * 新增巡检资源月计划
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 结果
     */
	@Override
	public int insertPlanMonth(PlanMonth planMonth)
	{
	    return planMonthMapper.insertPlanMonth(planMonth);
	}
	
	/**
     * 修改巡检资源月计划
     * 
     * @param planMonth 巡检资源月计划信息
     * @return 结果
     */
	@Override
	public int updatePlanMonth(PlanMonth planMonth)
	{
	    return planMonthMapper.updatePlanMonth(planMonth);
	}

	/**
     * 删除巡检资源月计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePlanMonthByIds(String ids)
	{
		return planMonthMapper.deletePlanMonthByIds(Convert.toStrArray(ids));
	}

	/**
	 * @Author ryy
	 * @Description 根据省公司id获取省公司每月资源点数
	 * @Date 2019/5/6 10:04
	 * @Param [stagnationId]
	 * @return java.util.List<com.aaa.project.system.planMonth.domain.PlanMonth>
	 **/
	@Override
	public List<PlanMonth> findByStagnationId(Integer stagnationId) {
		return planMonthMapper.findByStagnationId(stagnationId);
	}

	@Override
	public List<PlanMonth> findByFinishedStagnationId(Integer stagnationId) {
		return planMonthMapper.findByFinishedStagnationId(stagnationId);
	}
}
