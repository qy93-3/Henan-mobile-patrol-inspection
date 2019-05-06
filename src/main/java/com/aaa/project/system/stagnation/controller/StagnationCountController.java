package com.aaa.project.system.stagnation.controller;

import com.aaa.project.system.stagnation.domain.Stagnation;
import com.aaa.project.system.stagnation.service.IStagnationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    private String prefix = "system/stagnation";

    @Autowired
    IStagnationService stagnationService;


    @RequestMapping("/stagnationPC")
    @ResponseBody
    public List<Stagnation> stagnationPC(){
        return stagnationService.findAllPSCount();
    }
}
