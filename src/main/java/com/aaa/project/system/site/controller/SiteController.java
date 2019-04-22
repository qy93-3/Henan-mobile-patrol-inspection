package com.aaa.project.system.site.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 站点 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/site")
public class SiteController extends BaseController
{
    private String prefix = "system/site";
	
	@Autowired
	private ISiteService siteService;
	
	@RequiresPermissions("system:site:view")
	@GetMapping()
	public String site()
	{
	    return prefix + "/site";
	}
	
	/**
	 * 查询站点列表
	 */
	@RequiresPermissions("system:site:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Site site)
	{
		startPage();
        List<Site> list = siteService.selectSiteList(site);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出站点列表
	 */
	@RequiresPermissions("system:site:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Site site)
    {
    	List<Site> list = siteService.selectSiteList(site);
        ExcelUtil<Site> util = new ExcelUtil<Site>(Site.class);
        return util.exportExcel(list, "site");
    }
	
	/**
	 * 新增站点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存站点
	 */
	@RequiresPermissions("system:site:add")
	@Log(title = "站点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Site site)
	{		
		return toAjax(siteService.insertSite(site));
	}

	/**
	 * 修改站点
	 */
	@GetMapping("/edit/{siteId}")
	public String edit(@PathVariable("siteId") Long siteId, ModelMap mmap)
	{
		Site site = siteService.selectSiteById(siteId);
		mmap.put("site", site);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存站点
	 */
	@RequiresPermissions("system:site:edit")
	@Log(title = "站点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Site site)
	{		
		return toAjax(siteService.updateSite(site));
	}
	
	/**
	 * 删除站点
	 */
	@RequiresPermissions("system:site:remove")
	@Log(title = "站点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(siteService.deleteSiteByIds(ids));
	}
	
}
