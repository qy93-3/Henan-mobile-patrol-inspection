package com.aaa.project.system.messionStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.messionStatus.mapper.MessionStatusMapper;
import com.aaa.project.system.messionStatus.domain.MessionStatus;
import com.aaa.project.system.messionStatus.service.IMessionStatusService;
import com.aaa.common.support.Convert;

/**
 * 任务状态 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class MessionStatusServiceImpl implements IMessionStatusService 
{
	@Autowired
	private MessionStatusMapper messionStatusMapper;

	/**
     * 查询任务状态信息
     * 
     * @param messionStatusId 任务状态ID
     * @return 任务状态信息
     */
    @Override
	public MessionStatus selectMessionStatusById(Integer messionStatusId)
	{
	    return messionStatusMapper.selectMessionStatusById(messionStatusId);
	}
	
	/**
     * 查询任务状态列表
     * 
     * @param messionStatus 任务状态信息
     * @return 任务状态集合
     */
	@Override
	public List<MessionStatus> selectMessionStatusList(MessionStatus messionStatus)
	{
	    return messionStatusMapper.selectMessionStatusList(messionStatus);
	}
	
    /**
     * 新增任务状态
     * 
     * @param messionStatus 任务状态信息
     * @return 结果
     */
	@Override
	public int insertMessionStatus(MessionStatus messionStatus)
	{
	    return messionStatusMapper.insertMessionStatus(messionStatus);
	}
	
	/**
     * 修改任务状态
     * 
     * @param messionStatus 任务状态信息
     * @return 结果
     */
	@Override
	public int updateMessionStatus(MessionStatus messionStatus)
	{
	    return messionStatusMapper.updateMessionStatus(messionStatus);
	}

	/**
     * 删除任务状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMessionStatusByIds(String ids)
	{
		return messionStatusMapper.deleteMessionStatusByIds(Convert.toStrArray(ids));
	}
	
}
