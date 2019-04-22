package com.aaa.project.system.city.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.city.mapper.CityMapper;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.common.support.Convert;

/**
 * 市 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class CityServiceImpl implements ICityService 
{
	@Autowired
	private CityMapper cityMapper;

	/**
     * 查询市信息
     * 
     * @param id 市ID
     * @return 市信息
     */
    @Override
	public City selectCityById(Integer id)
	{
	    return cityMapper.selectCityById(id);
	}
	
	/**
     * 查询市列表
     * 
     * @param city 市信息
     * @return 市集合
     */
	@Override
	public List<City> selectCityList(City city)
	{
	    return cityMapper.selectCityList(city);
	}
	
    /**
     * 新增市
     * 
     * @param city 市信息
     * @return 结果
     */
	@Override
	public int insertCity(City city)
	{
	    return cityMapper.insertCity(city);
	}
	
	/**
     * 修改市
     * 
     * @param city 市信息
     * @return 结果
     */
	@Override
	public int updateCity(City city)
	{
	    return cityMapper.updateCity(city);
	}

	/**
     * 删除市对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCityByIds(String ids)
	{
		return cityMapper.deleteCityByIds(Convert.toStrArray(ids));
	}
	
}
