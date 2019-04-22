package com.aaa.project.system.stagnation.mapper;

import com.aaa.project.system.stagnation.domain.Stagnation;
import java.util.List;	

/**
 * 驻点 数据层
 * 
 * @author aaa
 * @date 2019-04-22
 */
public interface StagnationMapper 
{
	/**
     * 查询驻点信息
     * 
     * @param stagnationId 驻点ID
     * @return 驻点信息
     */
	public Stagnation selectStagnationById(Integer stagnationId);
	
	/**
     * 查询驻点列表
     * 
     * @param stagnation 驻点信息
     * @return 驻点集合
     */
	public List<Stagnation> selectStagnationList(Stagnation stagnation);
	
	/**
     * 新增驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	public int insertStagnation(Stagnation stagnation);
	
	/**
     * 修改驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	public int updateStagnation(Stagnation stagnation);
	
	/**
     * 删除驻点
     * 
     * @param stagnationId 驻点ID
     * @return 结果
     */
	public int deleteStagnationById(Integer stagnationId);
	
	/**
     * 批量删除驻点
     * 
     * @param stagnationIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteStagnationByIds(String[] stagnationIds);
	
}