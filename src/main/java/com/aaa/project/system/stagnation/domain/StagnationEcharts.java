package com.aaa.project.system.stagnation.domain;

import java.util.List;

/**
 * @ClassNameStagnationEcharts
 * @Description
 * @Author ryy
 * @Date2019/5/6 21:23
 * @Version V1.0
 **/
public class StagnationEcharts {
    private List<Integer> monthResources;
    private List<Integer> monthFinishedResources;

    public List<Integer> getMonthResources() {
        return monthResources;
    }

    public void setMonthResources(List<Integer> monthResources) {
        this.monthResources = monthResources;
    }

    public List<Integer> getMonthFinishedResources() {
        return monthFinishedResources;
    }

    public void setMonthFinishedResources(List<Integer> monthFinishedResources) {
        this.monthFinishedResources = monthFinishedResources;
    }

    @Override
    public String toString() {
        return "StagnationEcharts{" +
                "monthResources=" + monthResources +
                ", monthFinishedResources=" + monthFinishedResources +
                '}';
    }
}
