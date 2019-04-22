package com.aaa.project.system.change.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.change.mapper.ChangeMapper;
import com.aaa.project.system.change.domain.Change;
import com.aaa.project.system.change.service.IChangeService;
import com.aaa.common.support.Convert;

/**
 * 月计划变更申请 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class ChangeServiceImpl implements IChangeService 
{
	@Autowired
	private ChangeMapper changeMapper;

	/**
     * 查询月计划变更申请信息
     * 
     * @param changeId 月计划变更申请ID
     * @return 月计划变更申请信息
     */
    @Override
	public Change selectChangeById(Integer changeId)
	{
	    return changeMapper.selectChangeById(changeId);
	}
	
	/**
     * 查询月计划变更申请列表
     * 
     * @param change 月计划变更申请信息
     * @return 月计划变更申请集合
     */
	@Override
	public List<Change> selectChangeList(Change change)
	{
	    return changeMapper.selectChangeList(change);
	}
	
    /**
     * 新增月计划变更申请
     * 
     * @param change 月计划变更申请信息
     * @return 结果
     */
	@Override
	public int insertChange(Change change)
	{
	    return changeMapper.insertChange(change);
	}
	
	/**
     * 修改月计划变更申请
     * 
     * @param change 月计划变更申请信息
     * @return 结果
     */
	@Override
	public int updateChange(Change change)
	{
	    return changeMapper.updateChange(change);
	}

	/**
     * 删除月计划变更申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteChangeByIds(String ids)
	{
		return changeMapper.deleteChangeByIds(Convert.toStrArray(ids));
	}
	
}
