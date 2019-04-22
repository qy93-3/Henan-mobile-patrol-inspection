package com.aaa.project.system.routingPeople.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.routingPeople.mapper.RoutingPeopleMapper;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.aaa.common.support.Convert;

/**
 * 巡检人员 服务层实现
 * 
 * @author aaa
 * @date 2019-04-22
 */
@Service
public class RoutingPeopleServiceImpl implements IRoutingPeopleService 
{
	@Autowired
	private RoutingPeopleMapper routingPeopleMapper;

	/**
     * 查询巡检人员信息
     * 
     * @param routingId 巡检人员ID
     * @return 巡检人员信息
     */
    @Override
	public RoutingPeople selectRoutingPeopleById(Integer routingId)
	{
	    return routingPeopleMapper.selectRoutingPeopleById(routingId);
	}
	
	/**
     * 查询巡检人员列表
     * 
     * @param routingPeople 巡检人员信息
     * @return 巡检人员集合
     */
	@Override
	public List<RoutingPeople> selectRoutingPeopleList(RoutingPeople routingPeople)
	{
	    return routingPeopleMapper.selectRoutingPeopleList(routingPeople);
	}
	
    /**
     * 新增巡检人员
     * 
     * @param routingPeople 巡检人员信息
     * @return 结果
     */
	@Override
	public int insertRoutingPeople(RoutingPeople routingPeople)
	{
	    return routingPeopleMapper.insertRoutingPeople(routingPeople);
	}
	
	/**
     * 修改巡检人员
     * 
     * @param routingPeople 巡检人员信息
     * @return 结果
     */
	@Override
	public int updateRoutingPeople(RoutingPeople routingPeople)
	{
	    return routingPeopleMapper.updateRoutingPeople(routingPeople);
	}

	/**
     * 删除巡检人员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRoutingPeopleByIds(String ids)
	{
		return routingPeopleMapper.deleteRoutingPeopleByIds(Convert.toStrArray(ids));
	}
	
}
