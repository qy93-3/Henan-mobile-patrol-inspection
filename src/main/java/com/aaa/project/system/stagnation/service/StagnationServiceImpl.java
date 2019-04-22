package com.aaa.project.system.stagnation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.stagnation.mapper.StagnationMapper;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import com.aaa.common.support.Convert;

/**
 * 驻点 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class StagnationServiceImpl implements IStagnationService 
{
	@Autowired
	private StagnationMapper stagnationMapper;

	/**
     * 查询驻点信息
     * 
     * @param stagnationId 驻点ID
     * @return 驻点信息
     */
    @Override
	public Stagnation selectStagnationById(Integer stagnationId)
	{
	    return stagnationMapper.selectStagnationById(stagnationId);
	}
	
	/**
     * 查询驻点列表
     * 
     * @param stagnation 驻点信息
     * @return 驻点集合
     */
	@Override
	public List<Stagnation> selectStagnationList(Stagnation stagnation)
	{
	    return stagnationMapper.selectStagnationList(stagnation);
	}
	
    /**
     * 新增驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	@Override
	public int insertStagnation(Stagnation stagnation)
	{
	    return stagnationMapper.insertStagnation(stagnation);
	}
	
	/**
     * 修改驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	@Override
	public int updateStagnation(Stagnation stagnation)
	{
	    return stagnationMapper.updateStagnation(stagnation);
	}

	/**
     * 删除驻点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStagnationByIds(String ids)
	{
		return stagnationMapper.deleteStagnationByIds(Convert.toStrArray(ids));
	}
	
}
