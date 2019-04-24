package com.aaa.project.system.site.service;

import com.aaa.common.support.Convert;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点 服务层实现
 *
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class SiteServiceImpl implements ISiteService {
    @Autowired
    private SiteMapper siteMapper;

    /**
     * 查询站点信息
     *
     * @param siteId 站点ID
     * @return 站点信息
     */
    @Override
    public Site selectSiteById(Long siteId) {
        return siteMapper.selectSiteById(siteId);
    }

    /**
     * 查询站点列表
     *
     * @param site 站点信息
     * @return 站点集合
     */
    @Override
    public List<Site> selectSiteList(Site site) {
        return siteMapper.selectSiteList(site);
    }

    /**
     * 查询有驻点的站点列表
     *
     * @param site 站点信息
     * @return
     */
    @Override
    public List<Site> selectSiteListHasDis(Site site) {
        return siteMapper.selectSiteListHasDis(site);
    }

    /**
     * 新增站点
     *
     * @param site 站点信息
     * @return 结果
     */
    @Override
    public int insertSite(Site site) {
        return siteMapper.insertSite(site);
    }

    /**
     * 修改站点
     *
     * @param site 站点信息
     * @return 结果
     */
    @Override
    public int updateSite(Site site) {
        return siteMapper.updateSite(site);
    }

    /**
     * 释放资源
     *
     * @param site
     * @return
     */
    @Override
    public int cancelDistribute(Site site) {
        return siteMapper.cancelDistribute(site);
    }

    /**
     * 删除站点对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteByIds(String ids) {
        return siteMapper.deleteSiteByIds(Convert.toStrArray(ids));
    }

}
