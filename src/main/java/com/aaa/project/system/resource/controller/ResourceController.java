package com.aaa.project.system.resource.controller;

import java.util.List;
import java.util.Map;

import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
import com.aaa.project.system.resourcesCycleType.domain.ResourcesCycleType;
import com.aaa.project.system.resourcesCycleType.service.IResourcesCycleTypeService;
import com.aaa.project.system.site.domain.Site;
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
     * @param flag 判断选项卡选择的是'资源点分配'还是'驻点资源'，默认是前者
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
     * 查询资源点列表
     * @param flag 判断选项卡选择的是'资源点分配'还是'驻点资源'，默认是前者
     * @param resourceCity 下拉框选择的城市
     * @param resourceArea 下拉框选择的地区
     * @param stagantion 下拉框选择的驻点
     * @param resource
     * @param map
     * @return
     */
    @RequiresPermissions("system:resource:list")
    @PostMapping("/list/{flag}")
    @ResponseBody
    public TableDataInfo list(@PathVariable("flag") String flag, @RequestParam(required = false) Integer resourceCity,
                              @RequestParam(required = false) Integer resourceArea,Integer stagantion, Resource resource, Map<String, Object> map) {
        startPage();
        List<Resource> list = null;
        //判断地区编号是市还是区县
        if (resourceArea != null) {
            if (resourceArea != 0)
                resource.setResourceAreaId(resourceArea);//如果Area不为空且不为0('请选择')，则设置查询条件中resourceAreaId为Area
            else if (resourceCity != 0) {
                resource.setResourceAreaId(resourceCity);//如果Area为0但City不为0('请选择')，则设置查询条件中resourceAreaId为City
            }
        }

        if (stagantion!=null&&stagantion!=0)
            resource.setResourceStagantionCompany(stagantion);//如果驻点不为空且不为0('请选择')，则设置为查询条件
        //判断所需的查询条件
        if ("{0}".equals(flag)) {
            startPage();
            list = resourceService.selectResourceList(resource);
        } else {
            startPage();
            list = resourceService.selectResourceListHasDis(resource);
        }
        //获取资源点列表后进行表格内字段的设置
        for (Resource resource1 : list) {
            //设置表格中数据库没有的列名
            Integer resourceAreaId1 = resource1.getResourceAreaId();
            //若该资源有对应的驻点，则分配完成，否则分配未完成
            if (resource1.getResourceStagantionCompany() != null) {
                //设置表格内显示的分配状态
                resource1.setDistributeStatus("分配已完成");
                //设置表格内显示的驻点名称
                Integer resourceStagantionCompany = resource1.getResourceStagantionCompany();
                Stagnation stagnation = stagnationService.selectStagnationById(resourceStagantionCompany);
                resource1.setStagantionCompanyName(stagnation.getStagnationPname());
            } else
                resource1.setDistributeStatus("分配未完成");
            int i = Integer.parseInt(resourceAreaId1.toString().substring(0, 1));
            if (i == 4) {
                //若资源点地区编号以4开头(市级编号)，则设置表格内显示的地区名称为对应市的名称
                resource1.setAreaName(cityService.selectCityById(resource1.getResourceAreaId()).getCityName());
            } else {
                //若资源点地区编号不以4开头，则设置表格内显示的地区名称为对应县的名称
                Integer resourceAreaId = resource1.getResourceAreaId();
                resource1.setAreaName(areaService.selectAreaById(resourceAreaId).getAreaName());
            }
            if(resource1.getResourceCycle()!=null) {
                //当资源点巡检周期不为空时，设置其表格内显示的巡检周期的名称
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
            //将resource的驻点公司设置为stagantion
            resource.setResourceStagantionCompany(stagantion);
            i += resourceService.updateResource(resource);
        }
        return toAjax(i);
    }
    /**
     * 释放资源点
     */
    @RequiresPermissions("system:resource:relase")
    @Log(title = "资源点", businessType = BusinessType.DELETE)
    @PostMapping("/cancelDistribute")
    @ResponseBody
    public AjaxResult cancelDistribute(String ids) {
        int i = 0;
        for (String id : ids.split(",")) {
            Resource resource = resourceService.selectResourceById(Long.valueOf(id).longValue());
            //取消站点和驻点的关联
            i += resourceService.cancelDistribute(resource);
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
    @RequiresPermissions("system:resource:setCycle")
    @PostMapping("/setCycle")
    @ResponseBody
    public AjaxResult setCycle(int cycle,String ids){
        int i = 0;
        for (String id : ids.split(",")) {
            //根据ids中的每一个id获取对应的资源点，并设置对应的巡检周期
            Resource resource= resourceService.selectResourceById(Long.valueOf(id).longValue());
            resource.setResourceCycle(cycle);
            i += resourceService.updateResource(resource);
        }
        return toAjax(i);
    }
}
