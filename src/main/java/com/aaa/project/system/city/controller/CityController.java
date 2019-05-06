package com.aaa.project.system.city.controller;

import java.util.List;

import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

	@Autowired
	private IAreaService areaService;

	@Autowired
	private IStagnationService stagnationService;

	/**
	 * 下拉列表获取市对应的县区
	 *
	 * @param locationId
	 * @return
	 */
	@PostMapping("/getLocation")
	@ResponseBody
	public List getLocation(@RequestParam("areaLevel") String areaLevel, @RequestParam("locationId") Integer locationId) {
		Area area = new Area();
		area.setFather(locationId);
		List<Area> data = areaService.selectAreaList(area);
		return data;
	}

	/**
	 * 下拉列表获取对应的驻点
	 *
	 * @param areaLevel
	 * @param locationId
	 * @return
	 */
	@PostMapping("/getStagantion")
	@ResponseBody
	public List getStagantion(@RequestParam("areaLevel") String areaLevel, @RequestParam("locationId") Integer locationId) {
		Stagnation stagnation = new Stagnation();
		stagnation.setAddressId(locationId);
		List<Stagnation> data = stagnationService.selectStagnationList(stagnation);
		if ("city".equals(areaLevel)) {
			//当下拉框为城市时，将该城市下所有的区县的驻点也添加进去
			Area area = new Area();
			area.setFather(locationId);
			List<Area> areas = areaService.selectAreaList(area);
			for (Area area1 : areas) {
				stagnation.setAddressId(area1.getAreaId());
				data.addAll(stagnationService.selectStagnationList(stagnation));
			}
		}
		return data;
	}
	
}
