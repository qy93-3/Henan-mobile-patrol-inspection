package com.aaa.project.system.stagnation.controller;

import java.util.List;

import com.aaa.project.system.area.domain.Area;
import com.aaa.project.system.area.service.IAreaService;
import com.aaa.project.system.city.domain.City;
import com.aaa.project.system.city.service.ICityService;
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
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 驻点 信息操作处理
 *
 * @author aaa
 * @date 2019-04-22
 */
@Controller
@RequestMapping("/system/stagnation")
public class StagnationController extends BaseController {
    private String prefix = "system/stagnation";

    @Autowired
    private IStagnationService stagnationService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IAreaService areaService;

    @RequiresPermissions("system:stagnation:view")
    @GetMapping()
    public String stagnation() {
        return prefix + "/stagnation";
    }

    /**
     * 查询驻点列表
     */
    @RequiresPermissions("system:stagnation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Stagnation stagnation) {
        startPage();
        List<Stagnation> list = stagnationService.selectStagnationList(stagnation);
        for (Stagnation stagnation1 : list) {
            if (stagnation1.getPID() != null)
                //设置表格内显示的上级驻点名称
                stagnation1.setFatherName(stagnationService.selectStagnationById(stagnation1.getPID()).getStagnationPname());
            //设置表格内显示的市县名称
            if (stagnation1.getAddressId() != null) {
                if (stagnation1.getAreaLevelId() == 2) {
                    stagnation1.setAreaName(cityService.selectCityById(stagnation1.getAddressId()).getCityName());
                    stagnation1.setLevelName("市级分公司");
                } else if (stagnation1.getAreaLevelId() == 3) {
                    stagnation1.setLevelName("县级分公司");
                    Area area = areaService.selectAreaById(stagnation1.getAddressId());
                    City city = cityService.selectCityById(area.getFather());
                    stagnation1.setAreaName(city.getCityName() + area.getAreaName());
                }
            } else {
                stagnation1.setLevelName("总公司");
            }
        }
        return getDataTable(list);
    }


    /**
     * 导出驻点列表
     */
    @RequiresPermissions("system:stagnation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Stagnation stagnation) {
        List<Stagnation> list = stagnationService.selectStagnationList(stagnation);
        ExcelUtil<Stagnation> util = new ExcelUtil<Stagnation>(Stagnation.class);
        return util.exportExcel(list, "stagnation");
    }

    /**
     * 新增驻点
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存驻点
     */
    @RequiresPermissions("system:stagnation:add")
    @Log(title = "驻点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Stagnation stagnation) {
        return toAjax(stagnationService.insertStagnation(stagnation));
    }

    /**
     * 修改驻点
     */
    @GetMapping("/edit/{stagnationId}")
    public String edit(@PathVariable("stagnationId") Integer stagnationId, ModelMap mmap) {
        Stagnation stagnation = stagnationService.selectStagnationById(stagnationId);
        mmap.put("stagnation", stagnation);
        return prefix + "/edit";
    }

    /**
     * 修改保存驻点
     */
    @RequiresPermissions("system:stagnation:edit")
    @Log(title = "驻点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Stagnation stagnation) {
        return toAjax(stagnationService.updateStagnation(stagnation));
    }

    /**
     * 删除驻点
     */
    @RequiresPermissions("system:stagnation:remove")
    @Log(title = "驻点", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(stagnationService.deleteStagnationByIds(ids));
    }

}
