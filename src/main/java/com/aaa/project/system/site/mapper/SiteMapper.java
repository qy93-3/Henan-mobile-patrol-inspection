package com.aaa.project.system.site.mapper;

import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.site.domain.Site;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 站点 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface SiteMapper 
{
	/**
     * 查询站点信息
     * 
     * @param siteId 站点ID
     * @return 站点信息
     */
	public Site selectSiteById(Long siteId);
	
	/**
     * 查询站点列表
     * 
     * @param site 站点信息
     * @return 站点集合
     */
	public List<Site> selectSiteList(Site site);

	/**
	 * 查询有驻点的站点列表
	 * @param site 站点信息
	 * @return 站点集合
	 */
	public List<Site> selectSiteListHasDis(Site site);
	
	/**
     * 新增站点
     * 
     * @param site 站点信息
     * @return 结果
     */
	public int insertSite(Site site);
	
	/**
     * 修改站点
     * 
     * @param site 站点信息
     * @return 结果
     */
	public int updateSite(Site site);

	/**
	 * 释放日计划资源
	 * @param site
	 * @return
	 */
	public int relaseResources(Site site);

	/**
	 * 释放资源
	 * @param site
	 * @return
	 */
	public int cancelDistribute(Site site);
	

	/**
     * 批量删除站点
     * 
     * @param siteIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSiteByIds(String[] siteIds);

	/**
	 * 查询有驻点并且日期差大于周期的站点
	 * @param nowDate
	 * @param stagnationId
	 * @return
	 */
	public List<Site> selectSiteHasDate(@Param("nowDate")Date nowDate,@Param("siteStagantionCompany")int stagnationId);
	
}