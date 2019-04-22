package com.aaa.project.system.resource.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
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

    @Autowired
    private IResourcesCycleTypeService resourcesCycleTypeService;

    /**
     * 表格显示页面
     *
     * @param flag
     * @param map
     * @return
     */
    @RequiresPermissions("system:resource:view")
    @GetMapping("/{flag}")
    public String resource(@PathVariable("flag") int flag, Map<String, Object> map) {
        List<City> cities = cityService.selectCityList(null);
        map.put("cities", cities);
        map.put("flag", flag);
        return prefix + "/resource";
    }

    /**
     * 下拉列表获取市对应的县区
     *
     * @param locationId
     * @return
     */
    @RequiresPermissions("system:resource:getLocation")
    @PostMapping("/getLocation")
    @ResponseBody
    public List getLocation(@RequestParam("locationId") Integer locationId) {
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
    @RequiresPermissions("system:resource:getStagantion")
    @PostMapping("/getStagantion")
    @ResponseBody
    public List getStagantion(@RequestParam("areaLevel") String areaLevel, @RequestParam("locationId") Integer locationId) {
        Stagnation stagnation = new Stagnation();
        stagnation.setAddressId(locationId);
        List<Stagnation> data = stagnationService.selectStagnationList(stagnation);
        if ("city".equals(areaLevel)) {
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

    /**
     * 查询资源点列表
     */
    @RequiresPermissions("system:resource:list")
    @PostMapping("/list/{flag}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("flag") String flag, @RequestParam(required = false) Integer resourceCity,
                              @RequestParam(required = false) Integer resourceArea, Resource resource, Map<String, Object> map) {
        List<Resource> list = null;
        //判断地区编号是市还是区县
        int step = 0;
        if (resourceArea != null) {
            if (resourceArea != 0)
                resource.setResourceAreaId(resourceArea);
            else if (resourceCity != 0) {
                resource.setResourceAreaId(resourceCity);
                step = 1;
            }
        }

        //判断所需的查询条件
        if ("{0}".equals(flag)) {
            startPage();
            list = resourceService.selectResourceList(resource);
        } else {
            startPage();
            list = resourceService.selectResourceListHasDis(resource);
        }

        if (step == 1) {
            //地区编号是市，则列表中再添加所有该市下县和区的资源
            Area area = new Area();
            area.setFather(resourceCity);
            List<Area> areas = areaService.selectAreaList(area);
            for (Area area1 : areas) {
                resource.setResourceAreaId(area1.getAreaId());
                list.addAll(resourceService.selectResourceList(resource));
            }
        }

        for (Resource resource1 : list) {
            //设置表格中数据库没有的列名
            Integer resourceAreaId1 = resource1.getResourceAreaId();
            if (resource1.getResourceStagantionCompany() != null) {
                resource1.setDistributeStatus("分配已完成");
                Integer resourceStagantionCompany = resource1.getResourceStagantionCompany();
                Stagnation stagnation = stagnationService.selectStagnationById(resourceStagantionCompany);
                resource1.setStagantionCompanyName(stagnation.getStagnationPname());
            } else
                resource1.setDistributeStatus("分配未完成");
            int i = Integer.parseInt(resourceAreaId1.toString().substring(0, 1));
            if (i == 4) {
                resource1.setAreaName(cityService.selectCityById(resource1.getResourceAreaId()).getCityName());
            } else {
                Integer resourceAreaId = resource1.getResourceAreaId();
                resource1.setAreaName(areaService.selectAreaById(resourceAreaId).getAreaName());
            }
            if(resource1.getResourceCycle()!=null) {
                resource1.setCycleName(resourcesCycleTypeService.selectResourcesCycleTypeById(resource1.getResourceCycle()).getResourcesCycleName());
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
    public String add(Map<String, Object> map) {
        List<City> cities = cityService.selectCityList(null);
        map.put("cities",cities);
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
        Integer resourceAreaId = resource.getResourceAreaId();
        int i = Integer.parseInt(resourceAreaId.toString().substring(0, 1));
        if (i!=4){
            Area area = areaService.selectAreaById(resourceAreaId);
            mmap.put("resourceArea",area.getAreaName());
            mmap.put("resourceCity",cityService.selectCityById(area.getFather()).getCityName());
            System.out.println(area.getAreaName());
            System.out.println(cityService.selectCityById(area.getFather()).getCityName());
        }else{
            mmap.put("resourceCity",cityService.selectCityById(resourceAreaId).getCityName());
        }
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
    @RequiresPermissions("system:resource:distribute")
    @Log(title = "资源点", businessType = BusinessType.DELETE)
    @PostMapping("/distribute")
    @ResponseBody
    public AjaxResult distribute(String ids, Integer stagantion) {
        int i = 0;
        for (String id : ids.split(",")) {
            Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
            resource.setResourceStagantionCompany(stagantion);
            i += resourceService.updateResource(resource);
        }
        return toAjax(i);
    }
    /**
     * 分配资源点
     */
    @RequiresPermissions("system:resource:cancelDistribute")
    @Log(title = "资源点", businessType = BusinessType.DELETE)
    @PostMapping("/cancelDistribute")
    @ResponseBody
    public AjaxResult cancelDistribute(String ids) {
        int i = 0;
        for (String id : ids.split(",")) {
            Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
            i += resourceService.cancelDistribute(resource);
        }
        return toAjax(i);
    }

}
