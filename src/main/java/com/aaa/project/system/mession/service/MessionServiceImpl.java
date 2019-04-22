package com.aaa.project.system.mession.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.mession.mapper.MessionMapper;
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.mession.service.IMessionService;
import com.aaa.common.support.Convert;

/**
 * 日计划分配任务 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class MessionServiceImpl implements IMessionService 
{
	@Autowired
	private MessionMapper messionMapper;

	/**
     * 查询日计划分配任务信息
     * 
     * @param messionId 日计划分配任务ID
     * @return 日计划分配任务信息
     */
    @Override
	public Mession selectMessionById(Integer messionId)
	{
	    return messionMapper.selectMessionById(messionId);
	}
	
	/**
     * 查询日计划分配任务列表
     * 
     * @param mession 日计划分配任务信息
     * @return 日计划分配任务集合
     */
	@Override
	public List<Mession> selectMessionList(Mession mession)
	{
	    return messionMapper.selectMessionList(mession);
	}
	
    /**
     * 新增日计划分配任务
     * 
     * @param mession 日计划分配任务信息
     * @return 结果
     */
	@Override
	public int insertMession(Mession mession)
	{
	    return messionMapper.insertMession(mession);
	}
	
	/**
     * 修改日计划分配任务
     * 
     * @param mession 日计划分配任务信息
     * @return 结果
     */
	@Override
	public int updateMession(Mession mession)
	{
	    return messionMapper.updateMession(mession);
	}

	/**
     * 删除日计划分配任务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMessionByIds(String ids)
	{
		return messionMapper.deleteMessionByIds(Convert.toStrArray(ids));
	}
	
}
