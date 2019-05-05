package com.aaa.project.system.mession.mapper;

import com.aaa.project.system.mession.domain.Mession;
import java.util.List;	

/**
 * 日计划分配任务 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface MessionMapper 
{
	/**
     * 查询日计划分配任务信息
     * 
     * @param messionId 日计划分配任务ID
     * @return 日计划分配任务信息
     */
	public Mession selectMessionById(Integer messionId);
	
	/**
     * 查询日计划分配任务列表
     * 
     * @param mession 日计划分配任务信息
     * @return 日计划分配任务集合
     */
	public List<Mession> selectMessionList(Mession mession);

	public Mession selectMession(Mession mession);
	
	/**
     * 新增日计划分配任务
     * 
     * @param mession 日计划分配任务信息
     * @return 结果
     */
	public int insertMession(Mession mession);
	
	/**
     * 修改日计划分配任务
     * 
     * @param mession 日计划分配任务信息
     * @return 结果
     */
	public int updateMession(Mession mession);
	
	/**
     * 删除日计划分配任务
     * 
     * @param messionId 日计划分配任务ID
     * @return 结果
     */
	public int deleteMessionById(Integer messionId);
	
	/**
     * 批量删除日计划分配任务
     * 
     * @param messionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMessionByIds(String[] messionIds);
	
}