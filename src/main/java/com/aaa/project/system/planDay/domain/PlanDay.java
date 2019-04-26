package com.aaa.project.system.planDay.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 巡检资源日计划表 tbl_plan_day
 *
 * @author aaa
 * @date 2019-04-20
 */
public class PlanDay extends BaseEntity implements Cloneable {
    private static final long serialVersionUID = 1L;

    /**
     * 日计划编号
     */
    private Integer dayPlanId;
    /**
     * 日计划名称
     */
    private String dayPlanName;
    /**
     * 日计划日期
     */
    private Date dayPlanDate;
    /**
     * 日计划类型
     */
    private String dayPlanType;
    /**
     * 日计划站点编号
     */
    private Long dayPlanSite;
    /**
     * 日计划资源点编号
     */
    private Long dayPlanResource;
    /**
     * 日计划驻点编号
     */
    private Integer dayPlanStagnation;
    /**
     * 月计划编号
     */
    private Integer monthPlanId;
    /**
     * 日历编号
     */
    private Integer calendarPlanId;
    /**
     * 日计划状态
     */
    private Integer dayPlanStatus;

    private String resourcesName;

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName;
    }

    public void setDayPlanId(Integer dayPlanId) {
        this.dayPlanId = dayPlanId;
    }

    public Integer getDayPlanId() {
        return dayPlanId;
    }

    public void setDayPlanName(String dayPlanName) {
        this.dayPlanName = dayPlanName;
    }

    public String getDayPlanName() {
        return dayPlanName;
    }

    public void setDayPlanDate(Date dayPlanDate) {
        this.dayPlanDate = dayPlanDate;
    }

    public Date getDayPlanDate() {
        return dayPlanDate;
    }

    public void setDayPlanType(String dayPlanType) {
        this.dayPlanType = dayPlanType;
    }

    public String getDayPlanType() {
        return dayPlanType;
    }

    public void setDayPlanSite(Long dayPlanSite) {
        this.dayPlanSite = dayPlanSite;
    }

    public Long getDayPlanSite() {
        return dayPlanSite;
    }

    public void setDayPlanResource(Long dayPlanResource) {
        this.dayPlanResource = dayPlanResource;
    }

    public Long getDayPlanResource() {
        return dayPlanResource;
    }

    public void setDayPlanStagnation(Integer dayPlanStagnation) {
        this.dayPlanStagnation = dayPlanStagnation;
    }

    public Integer getDayPlanStagnation() {
        return dayPlanStagnation;
    }

    public void setMonthPlanId(Integer monthPlanId) {
        this.monthPlanId = monthPlanId;
    }

    public Integer getMonthPlanId() {
        return monthPlanId;
    }

    public void setCalendarPlanId(Integer calendarPlanId) {
        this.calendarPlanId = calendarPlanId;
    }

    public Integer getCalendarPlanId() {
        return calendarPlanId;
    }

    public void setDayPlanStatus(Integer dayPlanStatus) {
        this.dayPlanStatus = dayPlanStatus;
    }

    public Integer getDayPlanStatus() {
        return dayPlanStatus;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dayPlanId", getDayPlanId())
                .append("dayPlanName", getDayPlanName())
                .append("dayPlanDate", getDayPlanDate())
                .append("dayPlanType", getDayPlanType())
                .append("dayPlanSite", getDayPlanSite())
                .append("dayPlanResource", getDayPlanResource())
                .append("dayPlanStagnation", getDayPlanStagnation())
                .append("monthPlanId", getMonthPlanId())
                .append("calendarPlanId", getCalendarPlanId())
                .append("dayPlanStatus", getDayPlanStatus())
                .toString();
    }

    @Override
    public Object clone(){
        PlanDay planDay = null;
        try {
            planDay = (PlanDay) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return planDay;
    }
}
