package com.aaa.project.system.routingPeople.service;

import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import java.util.List;

/**
 * 巡检人员 服务层
 * 
 * @author aaa
 * @date 2019-04-22
 */
public interface IRoutingPeopleService 
{
	/**
     * 查询巡检人员信息
     * 
     * @param routingId 巡检人员ID
     * @return 巡检人员信息
     */
	public RoutingPeople selectRoutingPeopleById(Integer routingId);
	
	/**
     * 查询巡检人员列表
     * 
     * @param routingPeople 巡检人员信息
     * @return 巡检人员集合
     */
	public List<RoutingPeople> selectRoutingPeopleList(RoutingPeople routingPeople);
	
	/**
     * 新增巡检人员
     * 
     * @param routingPeople 巡检人员信息
     * @return 结果
     */
	public int insertRoutingPeople(RoutingPeople routingPeople);
	
	/**
     * 修改巡检人员
     * 
     * @param routingPeople 巡检人员信息
     * @return 结果
     */
	public int updateRoutingPeople(RoutingPeople routingPeople);
		
	/**
     * 删除巡检人员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRoutingPeopleByIds(String ids);
	
}
