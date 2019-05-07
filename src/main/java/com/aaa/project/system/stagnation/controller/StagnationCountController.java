package com.aaa.project.system.stagnation.controller;

import com.aaa.project.system.planMonth.domain.PlanMonth;
import com.aaa.project.system.planMonth.service.IPlanMonthService;
import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.domain.StagnationEcharts;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassNameStagnationCountController
 * @Description 统计报表
 * @Author ryy
 * @Date2019/5/5 15:52
 * @Version V1.0
 **/
@Controller
@RequestMapping("/system/stagnationCount")
public class StagnationCountController {

    @Autowired
    IStagnationService stagnationService;
    @Autowired
    IPlanMonthService planMonthService;

    /**
     * @Author ryy
     * @Description 省公司总资源点统计
     * @Date 2019/5/6 10:06
     * @Param []
     * @return java.util.List<com.aaa.project.system.stagnation.domain.Stagnation>
     **/
    @RequestMapping("/stagnationPC")
    @ResponseBody
    public List<Stagnation> stagnationPC(){
        return stagnationService.findAllPSCount();
    }
    /**
     * @Author ryy
     * @Description 省公司月资源点统计
     * @Date 2019/5/6 15:30
     * @Param [stagnationId]
     * @return java.util.List<java.lang.Integer>
     **/
    @RequestMapping("/stagnationByMonth")
    @ResponseBody
    public  StagnationEcharts stagnationByMonth(@RequestParam(value = "stagnationId", required = false) String stagnationId){
        StagnationEcharts stagnationEcharts = new StagnationEcharts();
        List<Integer> monthResources = new ArrayList<>();
        List<Integer> monthFinishedResources = new ArrayList<>();
        //当stagnationId为null设置默认显示省公司图表
        if (stagnationId==null||stagnationId==""){//21344  20776 20661
            //设置默认展示的图表
            List<PlanMonth> planMonths = planMonthService.findByStagnationId(20661);
            for (PlanMonth pm:planMonths) {
                monthResources.add(pm.getStagnationByMonth());
            }
            List<PlanMonth> planFinishedMonths = planMonthService.findByFinishedStagnationId(20661);
            for (PlanMonth pm: planFinishedMonths) {
                monthFinishedResources.add(pm.getStagnationByMonth());
            }
            //当不为null时，根据stagnationId查询结果显示图表
        }else{
            List<PlanMonth> planMonths = planMonthService.findByStagnationId(Integer.parseInt(stagnationId));
            for (PlanMonth pm:planMonths) {
                monthResources.add(pm.getStagnationByMonth());
            }
            List<PlanMonth> planFinishedMonths = planMonthService.findByFinishedStagnationId(Integer.parseInt(stagnationId));
            for (PlanMonth pm: planFinishedMonths) {
                monthFinishedResources.add(pm.getStagnationByMonth());
            }
        }

        stagnationEcharts.setMonthResources(monthResources);
        System.out.println(stagnationEcharts.getMonthResources().toString());
        stagnationEcharts.setMonthFinishedResources(monthFinishedResources);
        System.out.println(stagnationEcharts.getMonthFinishedResources().toString());

        return stagnationEcharts;
    }

}
