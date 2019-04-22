package com.aaa.project.system.check.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.check.mapper.CheckMapper;
import com.aaa.project.system.check.domain.Check;
import com.aaa.project.system.check.service.ICheckService;
import com.aaa.common.support.Convert;

/**
 * 月计划审核 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class CheckServiceImpl implements ICheckService 
{
	@Autowired
	private CheckMapper checkMapper;

	/**
     * 查询月计划审核信息
     * 
     * @param checkId 月计划审核ID
     * @return 月计划审核信息
     */
    @Override
	public Check selectCheckById(Integer checkId)
	{
	    return checkMapper.selectCheckById(checkId);
	}
	
	/**
     * 查询月计划审核列表
     * 
     * @param check 月计划审核信息
     * @return 月计划审核集合
     */
	@Override
	public List<Check> selectCheckList(Check check)
	{
	    return checkMapper.selectCheckList(check);
	}
	
    /**
     * 新增月计划审核
     * 
     * @param check 月计划审核信息
     * @return 结果
     */
	@Override
	public int insertCheck(Check check)
	{
	    return checkMapper.insertCheck(check);
	}
	
	/**
     * 修改月计划审核
     * 
     * @param check 月计划审核信息
     * @return 结果
     */
	@Override
	public int updateCheck(Check check)
	{
	    return checkMapper.updateCheck(check);
	}

	/**
     * 删除月计划审核对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCheckByIds(String ids)
	{
		return checkMapper.deleteCheckByIds(Convert.toStrArray(ids));
	}
	
}
