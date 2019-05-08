package com.aaa.project.system.site.controller;

import java.util.List;
import java.util.Map;

import com.aaa.common.utils.security.ShiroUtils;
import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resourcesCycleType.service.IResourcesCycleTypeService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.site.domain.Site;
import com.aaa.project.system.site.service.ISiteService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired
	private ICityService cityService;

	@Autowired
	private IAreaService areaService;

	@Autowired
	private IStagnationService stagnationService;

	@Autowired
	private IResourcesCycleTypeService resourcesCycleTypeService;

	/**
	 * 表格显示页面
	 *
	 * @param flag
	 * @param map
	 * @return
	 */
	@RequiresPermissions("system:site:view")
	@GetMapping("/{flag}")
	public String site(@PathVariable("flag") int flag, Map<String, Object> map)
	{
		List<City> cities = cityService.selectCityList(null);
		map.put("cities", cities);
		map.put("flag", flag);
		return prefix + "/site";
	}

	/**
	 * 查询站点列表
	 * @param flag 判断选项卡选择的是'资源点分配'还是'驻点资源'，默认是前者
	 * @param siteCity 下拉框选择的城市
	 * @param siteArea 下拉框选择的地区
	 * @param stagantion 下拉框选择的驻点
	 * @param map
	 * @param site
	 * @return
	 */
	@RequiresPermissions("system:site:list")
	@PostMapping("/list/{flag}")
	@ResponseBody
	public TableDataInfo list(@PathVariable("flag") String flag, @RequestParam(required = false) Integer siteCity,
							  @RequestParam(required = false) Integer siteArea,
							  Integer stagantion,Map<String, Object> map,Site site){
		startPage();
		List<Site> list = null;
		//判断地区编号是市还是区县
		if (siteArea != null) {
			if (siteArea != 0)
				site.setSiteAreaId(siteArea);//如果Area不为空且不为0('请选择')，则设置查询条件中siteAreaId为Area
			else if (siteCity != 0) {
				site.setSiteAreaId(siteCity);//如果Area为0但City不为0('请选择')，则设置查询条件中siteAreaId为City
			}
		}
		if (stagantion!=null&&stagantion!=0)
			site.setSiteStagantionCompany(stagantion);//如果驻点不为空且不为0('请选择')，则设置为查询条件

		//判断所需的查询条件
		if ("{0}".equals(flag)) {
			list = siteService.selectSiteList(site);
		} else {
			list = siteService.selectSiteListHasDis(site);
		}

		//获取资源点列表后进行表格内字段的设置
		for (Site site1 : list) {
			//设置表格中数据库没有的列名
			Integer siteAreaId1 = site1.getSiteAreaId();
			//若该资源有对应的驻点，则分配完成，否则分配未完成
			if (site1.getSiteStagantionCompany() != null) {
				//设置表格内显示的分配状态
				site1.setDistributeStatus("分配已完成");
				//设置表格内显示的驻点名称
				Integer siteStagantionCompany = site1.getSiteStagantionCompany();
				Stagnation stagnation = stagnationService.selectStagnationById(siteStagantionCompany);
				site1.setStagantionCompanyName(stagnation.getStagnationPname());
			} else
				site1.setDistributeStatus("分配未完成");
			int i = Integer.parseInt(siteAreaId1.toString().substring(0, 1));
			if (i == 4) {
				//若站点地区编号以4开头(市级编号)，则设置表格内显示的地区名称为对应市的名称
				site1.setAreaName(cityService.selectCityById(site1.getSiteAreaId()).getCityName());
			} else {
				//若站点地区编号不以4开头，则设置表格内显示的地区名称为对应县的名称
				Integer siteAreaId = site1.getSiteAreaId();
				site1.setAreaName(areaService.selectAreaById(siteAreaId).getAreaName());
			}
			if(site1.getSiteCycle()!=null) {
				//当站点巡检周期不为空时，设置其表格内显示的巡检周期的名称
				site1.setCycleName(resourcesCycleTypeService.selectResourcesCycleTypeById(site1.getSiteCycle()).getResourcesCycleName());
			}
		}
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
		Integer siteAreaId = site.getSiteAreaId();
		int i = Integer.parseInt(siteAreaId.toString().substring(0, 1));
		if (i!=4){
			Area area = areaService.selectAreaById(siteAreaId);
			mmap.put("siteArea",area.getAreaName());
			mmap.put("siteCity",cityService.selectCityById(area.getFather()).getCityName());
		}else{
			mmap.put("siteCity",cityService.selectCityById(siteAreaId).getCityName());
		}
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

	/**
	 * 分配站点
	 */
	@RequiresPermissions("system:site:distribute")
	@Log(title = "站点", businessType = BusinessType.DELETE)
	@PostMapping("/distribute")
	@ResponseBody
	public AjaxResult distribute(String ids, Integer stagantion) {
		int i = 0;
		for (String id : ids.split(",")) {
			Site site = siteService.selectSiteById(Long.valueOf(id).longValue());
			//将site的驻点公司设置为stagantion
			site.setSiteStagantionCompany(stagantion);
			i += siteService.updateSite(site);
		}
		return toAjax(i);
	}
	/**
	 * 释放站点
	 */
	@RequiresPermissions("system:site:relase")
	@Log(title = "站点", businessType = BusinessType.DELETE)
	@PostMapping("/cancelDistribute")
	@ResponseBody
	public AjaxResult cancelDistribute(String ids) {
		int i = 0;
		for (String id : ids.split(",")) {
			Site site = siteService.selectSiteById(Long.valueOf(id).longValue());
			//取消站点和驻点的关联
			i += siteService.cancelDistribute(site);
		}
		return toAjax(i);
	}

	/**
	 * 跳转到设置巡检周期页面
	 * @param rows
	 * @param map
	 * @return
	 */
	@GetMapping("/toSetCycle")
	public String toSetCycle(@RequestParam Object rows,Map<String,Object> map){
		map.put("rows",rows);
		return prefix + "/setCycle";
	}

	/**
	 * 设置巡检周期
	 * @param cycle 巡检周期ID
	 * @param ids 所选资源ID
	 * @return
	 */
	@RequiresPermissions("system:site:setCycle")
	@PostMapping("/setCycle")
	@ResponseBody
	public AjaxResult setCycle(int cycle,String ids){
		int i = 0;
		for (String id : ids.split(",")) {
			//根据ids中的每一个id获取对应的站点，并设置对应的巡检周期
			Site site = siteService.selectSiteById(Long.valueOf(id).longValue());
			site.setSiteCycle(cycle);
			i += siteService.updateSite(site);
		}
		return toAjax(i);
	}
}
