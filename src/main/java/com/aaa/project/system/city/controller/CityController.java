package com.aaa.project.system.city.controller;

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
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 市 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-22
 */
@Controller
@RequestMapping("/system/city")
public class CityController extends BaseController
{
    private String prefix = "system/city";
	
	@Autowired
	private ICityService cityService;
	
	@RequiresPermissions("system:city:view")
	@GetMapping()
	public String city()
	{
	    return prefix + "/city";
	}
	
	/**
	 * 查询市列表
	 */
	@RequiresPermissions("system:city:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(City city)
	{
		startPage();
        List<City> list = cityService.selectCityList(city);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出市列表
	 */
	@RequiresPermissions("system:city:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(City city)
    {
    	List<City> list = cityService.selectCityList(city);
        ExcelUtil<City> util = new ExcelUtil<City>(City.class);
        return util.exportExcel(list, "city");
    }
	
	/**
	 * 新增市
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存市
	 */
	@RequiresPermissions("system:city:add")
	@Log(title = "市", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(City city)
	{		
		return toAjax(cityService.insertCity(city));
	}

	/**
	 * 修改市
	 */
	@GetMapping("/edit/{cityId}")
	public String edit(@PathVariable("cityId") Integer cityId, ModelMap mmap)
	{
		City city = cityService.selectCityById(cityId);
		mmap.put("city", city);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存市
	 */
	@RequiresPermissions("system:city:edit")
	@Log(title = "市", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(City city)
	{		
		return toAjax(cityService.updateCity(city));
	}
	
	/**
	 * 删除市
	 */
	@RequiresPermissions("system:city:remove")
	@Log(title = "市", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(cityService.deleteCityByIds(ids));
	}
	
}
