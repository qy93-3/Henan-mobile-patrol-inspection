package com.aaa.project.system.danger.service;

import com.aaa.project.system.danger.domain.Danger;
import java.util.List;

/**
 * 巡检资源隐患 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IDangerService 
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
     * 删除巡检资源隐患信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDangerByIds(String ids);
	
}
