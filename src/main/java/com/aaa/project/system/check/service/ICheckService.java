package com.aaa.project.system.check.service;

import com.aaa.project.system.check.domain.Check;
import java.util.List;

/**
 * 月计划审核 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ICheckService 
{
	/**
     * 查询月计划审核信息
     * 
     * @param checkId 月计划审核ID
     * @return 月计划审核信息
     */
	public Check selectCheckById(Integer checkId);
	
	/**
     * 查询月计划审核列表
     * 
     * @param check 月计划审核信息
     * @return 月计划审核集合
     */
	public List<Check> selectCheckList(Check check);
	
	/**
     * 新增月计划审核
     * 
     * @param check 月计划审核信息
     * @return 结果
     */
	public int insertCheck(Check check);
	
	/**
     * 修改月计划审核
     * 
     * @param check 月计划审核信息
     * @return 结果
     */
	public int updateCheck(Check check);
		
	/**
     * 删除月计划审核信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCheckByIds(String ids);
	
}
