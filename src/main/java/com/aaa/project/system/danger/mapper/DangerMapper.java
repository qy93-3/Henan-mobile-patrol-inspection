package com.aaa.project.system.danger.mapper;

import com.aaa.project.system.danger.domain.Danger;
import java.util.List;	

/**
 * 巡检资源隐患 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface DangerMapper 
{
	/**
     * 查询巡检资源隐患信息
     * 
     * @param dangerId 巡检资源隐患ID
     * @return 巡检资源隐患信息
     */
	public Danger selectDangerById(Integer dangerId);
	
	/**
     * 查询巡检资源隐患列表
     * 
     * @param danger 巡检资源隐患信息
     * @return 巡检资源隐患集合
     */
	public List<Danger> selectDangerList(Danger danger);
	
	/**
     * 新增巡检资源隐患
     * 
     * @param danger 巡检资源隐患信息
     * @return 结果
     */
	public int insertDanger(Danger danger);
	
	/**
     * 修改巡检资源隐患
     * 
     * @param danger 巡检资源隐患信息
     * @return 结果
     */
	public int updateDanger(Danger danger);
	
	/**
     * 删除巡检资源隐患
     * 
     * @param dangerId 巡检资源隐患ID
     * @return 结果
     */
	public int deleteDangerById(Integer dangerId);
	
	/**
     * 批量删除巡检资源隐患
     * 
     * @param dangerIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangerByIds(String[] dangerIds);
	
}