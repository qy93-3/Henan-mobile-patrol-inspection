package com.aaa.project.system.dangerStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.dangerStatus.mapper.DangerStatusMapper;
import com.aaa.project.system.dangerStatus.domain.DangerStatus;
import com.aaa.project.system.dangerStatus.service.IDangerStatusService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源隐患解决状态 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class DangerStatusServiceImpl implements IDangerStatusService 
{
	@Autowired
	private DangerStatusMapper dangerStatusMapper;

	/**
     * 查询巡检资源隐患解决状态信息
     * 
     * @param dangerHandleId 巡检资源隐患解决状态ID
     * @return 巡检资源隐患解决状态信息
     */
    @Override
	public DangerStatus selectDangerStatusById(Integer dangerHandleId)
	{
	    return dangerStatusMapper.selectDangerStatusById(dangerHandleId);
	}
	
	/**
     * 查询巡检资源隐患解决状态列表
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 巡检资源隐患解决状态集合
     */
	@Override
	public List<DangerStatus> selectDangerStatusList(DangerStatus dangerStatus)
	{
	    return dangerStatusMapper.selectDangerStatusList(dangerStatus);
	}
	
    /**
     * 新增巡检资源隐患解决状态
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 结果
     */
	@Override
	public int insertDangerStatus(DangerStatus dangerStatus)
	{
	    return dangerStatusMapper.insertDangerStatus(dangerStatus);
	}
	
	/**
     * 修改巡检资源隐患解决状态
     * 
     * @param dangerStatus 巡检资源隐患解决状态信息
     * @return 结果
     */
	@Override
	public int updateDangerStatus(DangerStatus dangerStatus)
	{
	    return dangerStatusMapper.updateDangerStatus(dangerStatus);
	}

	/**
     * 删除巡检资源隐患解决状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangerStatusByIds(String ids)
	{
		return dangerStatusMapper.deleteDangerStatusByIds(Convert.toStrArray(ids));
	}
	
}
