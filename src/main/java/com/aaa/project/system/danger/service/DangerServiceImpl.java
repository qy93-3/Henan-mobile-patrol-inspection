package com.aaa.project.system.danger.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.danger.mapper.DangerMapper;
import com.aaa.project.system.danger.domain.Danger;
import com.aaa.project.system.danger.service.IDangerService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源隐患 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class DangerServiceImpl implements IDangerService 
{
	@Autowired
	private DangerMapper dangerMapper;

	/**
     * 查询巡检资源隐患信息
     * 
     * @param dangerId 巡检资源隐患ID
     * @return 巡检资源隐患信息
     */
    @Override
	public Danger selectDangerById(Integer dangerId)
	{
	    return dangerMapper.selectDangerById(dangerId);
	}
	
	/**
     * 查询巡检资源隐患列表
     * 
     * @param danger 巡检资源隐患信息
     * @return 巡检资源隐患集合
     */
	@Override
	public List<Danger> selectDangerList(Danger danger)
	{
	    return dangerMapper.selectDangerList(danger);
	}
	
    /**
     * 新增巡检资源隐患
     * 
     * @param danger 巡检资源隐患信息
     * @return 结果
     */
	@Override
	public int insertDanger(Danger danger)
	{
	    return dangerMapper.insertDanger(danger);
	}
	
	/**
     * 修改巡检资源隐患
     * 
     * @param danger 巡检资源隐患信息
     * @return 结果
     */
	@Override
	public int updateDanger(Danger danger)
	{
	    return dangerMapper.updateDanger(danger);
	}

	/**
     * 删除巡检资源隐患对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangerByIds(String ids)
	{
		return dangerMapper.deleteDangerByIds(Convert.toStrArray(ids));
	}
	
}
