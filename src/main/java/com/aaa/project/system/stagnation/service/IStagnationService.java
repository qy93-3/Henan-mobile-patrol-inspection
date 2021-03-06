package com.aaa.project.system.stagnation.service;

import com.aaa.project.system.stagnation.domain.Stagnation;
import java.util.List;

/**
 * 驻点 服务层
 * 
 * @author aaa
 * @date 2019-04-22
 */
public interface IStagnationService 
{
	/**
     * 查询驻点信息
     * 
     * @param stagnationId 驻点ID
     * @return 驻点信息
     */
	public Stagnation selectStagnationById(Integer stagnationId);
	
	/**
     * 查询驻点列表
     * 
     * @param stagnation 驻点信息
     * @return 驻点集合
     */
	public List<Stagnation> selectStagnationList(Stagnation stagnation);
	
	/**
     * 新增驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	public int insertStagnation(Stagnation stagnation);
	
	/**
     * 修改驻点
     * 
     * @param stagnation 驻点信息
     * @return 结果
     */
	public int updateStagnation(Stagnation stagnation);
		
	/**
     * 删除驻点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStagnationByIds(String ids);


	/**
	 * @Author ryy
	 * @Description 查询所有省公司所有所属资源点数
	 * @Date 2019/5/5 15:46
	 * @Param []
	 * @return java.util.List<com.aaa.project.system.stagnation.domain.Stagnation>
	 **/
	List<Stagnation> findAllPSCount();

	/**
	 * @Author ryy
	 * @Description 查询所有的省公司
	 * @Date 2019/5/5 19:51
	 * @Param []
	 * @return java.util.List<com.aaa.project.system.stagnation.domain.Stagnation>
	 **/
	List<Stagnation> findAllPS();
}
