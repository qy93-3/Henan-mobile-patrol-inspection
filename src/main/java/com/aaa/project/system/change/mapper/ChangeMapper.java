package com.aaa.project.system.change.mapper;

import com.aaa.project.system.change.domain.Change;
import java.util.List;	

/**
 * 月计划变更申请 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ChangeMapper 
{
	/**
     * 查询月计划变更申请信息
     * 
     * @param changeId 月计划变更申请ID
     * @return 月计划变更申请信息
     */
	public Change selectChangeById(Integer changeId);
	
	/**
     * 查询月计划变更申请列表
     * 
     * @param change 月计划变更申请信息
     * @return 月计划变更申请集合
     */
	public List<Change> selectChangeList(Change change);
	
	/**
     * 新增月计划变更申请
     * 
     * @param change 月计划变更申请信息
     * @return 结果
     */
	public int insertChange(Change change);
	
	/**
     * 修改月计划变更申请
     * 
     * @param change 月计划变更申请信息
     * @return 结果
     */
	public int updateChange(Change change);
	
	/**
     * 删除月计划变更申请
     * 
     * @param changeId 月计划变更申请ID
     * @return 结果
     */
	public int deleteChangeById(Integer changeId);
	
	/**
     * 批量删除月计划变更申请
     * 
     * @param changeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteChangeByIds(String[] changeIds);
	
}