package com.aaa.project.system.dangerLevel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.dangerLevel.mapper.DangerLevelMapper;
import com.aaa.project.system.dangerLevel.domain.DangerLevel;
import com.aaa.project.system.dangerLevel.service.IDangerLevelService;
import com.aaa.common.support.Convert;

/**
 * 巡检资源隐患严重程度 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class DangerLevelServiceImpl implements IDangerLevelService 
{
	@Autowired
	private DangerLevelMapper dangerLevelMapper;

	/**
     * 查询巡检资源隐患严重程度信息
     * 
     * @param dangerLevelId 巡检资源隐患严重程度ID
     * @return 巡检资源隐患严重程度信息
     */
    @Override
	public DangerLevel selectDangerLevelById(Integer dangerLevelId)
	{
	    return dangerLevelMapper.selectDangerLevelById(dangerLevelId);
	}
	
	/**
     * 查询巡检资源隐患严重程度列表
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 巡检资源隐患严重程度集合
     */
	@Override
	public List<DangerLevel> selectDangerLevelList(DangerLevel dangerLevel)
	{
	    return dangerLevelMapper.selectDangerLevelList(dangerLevel);
	}
	
    /**
     * 新增巡检资源隐患严重程度
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 结果
     */
	@Override
	public int insertDangerLevel(DangerLevel dangerLevel)
	{
	    return dangerLevelMapper.insertDangerLevel(dangerLevel);
	}
	
	/**
     * 修改巡检资源隐患严重程度
     * 
     * @param dangerLevel 巡检资源隐患严重程度信息
     * @return 结果
     */
	@Override
	public int updateDangerLevel(DangerLevel dangerLevel)
	{
	    return dangerLevelMapper.updateDangerLevel(dangerLevel);
	}

	/**
     * 删除巡检资源隐患严重程度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDangerLevelByIds(String ids)
	{
		return dangerLevelMapper.deleteDangerLevelByIds(Convert.toStrArray(ids));
	}
	
}
