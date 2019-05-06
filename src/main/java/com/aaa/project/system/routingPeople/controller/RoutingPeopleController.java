package com.aaa.project.system.routingPeople.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingPeople.service.IRoutingPeopleService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检人员 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-22
 */
@Controller
@RequestMapping("/system/routingPeople")
public class RoutingPeopleController extends BaseController
{
    private String prefix = "system/routingPeople";
	
	@Autowired
	private IRoutingPeopleService routingPeopleService;

	@Autowired
	private IStagnationService stagnationService;

	@Autowired
	private ICityService cityService;
	
	@RequiresPermissions("system:routingPeople:view")
	@GetMapping()
	public String routingPeople(Map<String, Object> map)
	{
	    map.put("cities", cityService.selectCityList(null));
		return prefix + "/routingPeople";
	}
	
	/**
	 * 查询巡检人员列表
	 */
	@RequiresPermissions("system:routingPeople:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(RoutingPeople routingPeople, @RequestParam(required = false) Integer stagantion)
	{
		if (stagantion!=null&&stagantion!=0)
			routingPeople.setStagnationId(stagantion);
		startPage();
        List<RoutingPeople> list = routingPeopleService.selectRoutingPeopleList(routingPeople);
		for (RoutingPeople people : list) {
			//设置表格内显示的驻点名称
			people.setStagnationName(stagnationService.selectStagnationById(people.getStagnationId()).getStagnationPname());
		}
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检人员列表
	 */
	@RequiresPermissions("system:routingPeople:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RoutingPeople routingPeople)
    {
    	List<RoutingPeople> list = routingPeopleService.selectRoutingPeopleList(routingPeople);
        ExcelUtil<RoutingPeople> util = new ExcelUtil<RoutingPeople>(RoutingPeople.class);
        return util.exportExcel(list, "routingPeople");
    }
	
	/**
	 * 新增巡检人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检人员
	 */
	@RequiresPermissions("system:routingPeople:add")
	@Log(title = "巡检人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(RoutingPeople routingPeople)
	{		
		return toAjax(routingPeopleService.insertRoutingPeople(routingPeople));
	}

	/**
	 * 修改巡检人员
	 */
	@GetMapping("/edit/{routingId}")
	public String edit(@PathVariable("routingId") Integer routingId, ModelMap mmap)
	{
		RoutingPeople routingPeople = routingPeopleService.selectRoutingPeopleById(routingId);
		mmap.put("routingPeople", routingPeople);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检人员
	 */
	@RequiresPermissions("system:routingPeople:edit")
	@Log(title = "巡检人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(RoutingPeople routingPeople)
	{		
		return toAjax(routingPeopleService.updateRoutingPeople(routingPeople));
	}
	
	/**
	 * 删除巡检人员
	 */
	@RequiresPermissions("system:routingPeople:remove")
	@Log(title = "巡检人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(routingPeopleService.deleteRoutingPeopleByIds(ids));
	}
	
}
