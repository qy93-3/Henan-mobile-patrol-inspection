package com.aaa.project.system.city.service;

import com.aaa.project.system.city.domain.City;
import java.util.List;

/**
 * 市 服务层
 * 
 * @author aaa
 * @date 2019-04-22
 */
public interface ICityService 
{
	/**
     * 查询市信息
     * 
     * @param cityId 市ID
     * @return 市信息
     */
	public City selectCityById(Integer cityId);
	
	/**
     * 查询市列表
     * 
     * @param city 市信息
     * @return 市集合
     */
	public List<City> selectCityList(City city);
	
	/**
     * 新增市
     * 
     * @param city 市信息
     * @return 结果
     */
	public int insertCity(City city);
	
	/**
     * 修改市
     * 
     * @param city 市信息
     * @return 结果
     */
	public int updateCity(City city);
		
	/**
     * 删除市信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCityByIds(String ids);
	
}
