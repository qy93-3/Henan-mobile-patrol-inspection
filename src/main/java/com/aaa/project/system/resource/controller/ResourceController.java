package com.aaa.project.system.resource.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpSession;

/**
 * 资源点 信息操作处理
 *
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/resource")
public class ResourceController extends BaseController {
    private String prefix = "system/resource";

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IStagnationService stagnationService;

    /**
     * 表格显示页面
     * @param flag
     * @param map
     * @return
     */
    @RequiresPermissions("system:resource:view")
    @GetMapping("/{flag}")
    public String resource(@PathVariable("flag")int flag, Map<String, Object> map) {
        List<City> cities = cityService.selectCityList(null);
        map.put("cities", cities);
        map.put("flag",flag);
        return prefix + "/resource";
    }

    /**
     * 下拉列表获取市对应的县区
     * @param locationId
     * @return
     */
    @RequiresPermissions("system:resource:getLocation")
    @PostMapping("/getLocation")
    @ResponseBody
    public List getLocation(@RequestParam("locationId") String locationId) {
        Area area = new Area();
        area.setFather(locationId);
        List<Area> data = areaService.selectAreaList(area);
        return data;
    }

    /**
     * 下拉列表获取对应的驻点
     * @param areaLevel
     * @param locationId
     * @return
     */
    @RequiresPermissions("system:resource:getStagantion")
    @PostMapping("/getStagantion")
    @ResponseBody
    public List getStagantion(@RequestParam("areaLevel") String areaLevel,@RequestParam("locationId") String locationId) {
        Stagnation stagnation = new Stagnation();
        stagnation.setAddressId(locationId);
        List<Stagnation> data= stagnationService.selectStagnationList(stagnation);
        if ("city".equals(areaLevel)){
            Area area = new Area();
            area.setFather(locationId);
            List<Area> areas = areaService.selectAreaList(area);
            for (Area area1 : areas) {
                stagnation.setAddressId(area1.getAreaID());
                data.addAll(stagnationService.selectStagnationList(stagnation));
            }
        }
        return data;
    }

    /**
     * 查询资源点列表
     */
    @RequiresPermissions("system:resource:list")
    @PostMapping("/list/{flag}")
    @ResponseBody
    public TableDataInfo list(@PathVariable String flag,@RequestParam(required = false) Integer resourceCity, @RequestParam(required = false) Integer resourceArea, Resource resource, Map<String, Object> map) {
        int step=0;
        if (resourceArea != null) {
            if (resourceArea != 0)
                resource.setResourceAreaId(resourceArea);
            else if (resourceCity != 0) {
                resource.setResourceAreaId(resourceCity);
                step = 1;
            }
        }
        List<Resource> list=null;
        if("{0}".equals(flag)){
            startPage();
            list = resourceService.selectResourceList(resource);
        }else{
            startPage();
            list = resourceService.selectResourceListHasDis(resource);
        }
        if(step==1){
            Area area = new Area();
            area.setFather(resourceCity+"");
            List<Area> areas = areaService.selectAreaList(area);
            for (Area area1 : areas) {
                resource.setResourceAreaId(Integer.parseInt(area1.getAreaID()));
                list.addAll(resourceService.selectResourceList(resource));
            }
        }
        for (Resource resource1 : list) {
            Integer resourceAreaId1 = resource1.getResourceAreaId();
            if (resource1.getResourceStagantionCompany() != null) {
                resource1.setDistributeStatus("分配已完成");
                Stagnation stagnation = stagnationService.selectStagnationById(resource1.getResourceStagantionCompany());
                resource1.setStagantionCompanyName(stagnation.getStagnationPname());
            }
            else
                resource1.setDistributeStatus("分配未完成");
            int i = Integer.parseInt(resourceAreaId1.toString().substring(0, 1));
            if(i==4){
                resource1.setAreaName(cityService.selectCityByCode(resource1.getResourceAreaId()+"").getCity());
            }else{
                Integer resourceAreaId = resource1.getResourceAreaId();
                resource1.setAreaName(areaService.selectAreaByCode( resourceAreaId+ "").getArea());
            }
        }
        return getDataTable(list);
    }


    /**
     * 导出资源点列表
     */
    @RequiresPermissions("system:resource:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Resource resource) {
        List<Resource> list = resourceService.selectResourceList(resource);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        return util.exportExcel(list, "resource");
    }

    /**
     * 新增资源点
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存资源点
     */
    @RequiresPermissions("system:resource:add")
    @Log(title = "资源点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Resource resource) {
        return toAjax(resourceService.insertResource(resource));
    }

    /**
     * 修改资源点
     */
    @GetMapping("/edit/{resourceId}")
    public String edit(@PathVariable("resourceId") Long resourceId, ModelMap mmap) {
        Resource resource = resourceService.selectResourceById(resourceId);
        mmap.put("resource", resource);
        return prefix + "/edit";
    }

    /**
     * 修改保存资源点
     */
    @RequiresPermissions("system:resource:edit")
    @Log(title = "资源点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Resource resource) {
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除资源点
     */
    @RequiresPermissions("system:resource:remove")
    @Log(title = "资源点", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(resourceService.deleteResourceByIds(ids));
    }

    /**
     * 分配资源点
     */
    @RequiresPermissions("system:resource:remove")
    @Log(title = "资源点", businessType = BusinessType.DELETE)
    @PostMapping("/distribute")
    @ResponseBody
    public AjaxResult distribute(String ids,Integer stagantion) {
        int i=0;
        for (String id : ids.split(",")) {
            Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
            resource.setResourceStagantionCompany(stagantion);
            i += resourceService.updateResource(resource);
        }
        return toAjax(i);
    }

}
