package com.aaa.project.system.site.mapper;

import com.aaa.project.system.site.domain.Site;
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
     * 删除站点
     * 
     * @param siteId 站点ID
     * @return 结果
     */
	public int deleteSiteById(Long siteId);
	
	/**
     * 批量删除站点
     * 
     * @param siteIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSiteByIds(String[] siteIds);
	
}