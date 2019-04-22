package com.aaa.project.system.planMonth.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检资源月计划表 tbl_plan_month
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class PlanMonth extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 月计划编号 */
	private Integer monthPlanId;
	/** 月计划名称 */
	private String monthPlanName;
	/** 月计划类型 */
	private String monthPlanType;
	/** 月计划月份 */
	private Integer monthPlanMonth;
	/** 月计划年份 */
	private Integer monthPlanYear;
	/** 月计划驻点编号 */
	private Integer monthPlanStagnation;
	/** 月计划计划资源数 */
	private Integer monthPlanResources;
	/** 月计划状态 */
	private Integer monthPlanStatus;
	/** 地域级别 */
	private Integer areaLevel;
	/** 月计划已巡检资源数 */
	private Integer monthRoutingResources;

	public void setMonthPlanId(Integer monthPlanId) 
	{
		this.monthPlanId = monthPlanId;
	}

	public Integer getMonthPlanId() 
	{
		return monthPlanId;
	}
	public void setMonthPlanName(String monthPlanName) 
	{
		this.monthPlanName = monthPlanName;
	}

	public String getMonthPlanName() 
	{
		return monthPlanName;
	}
	public void setMonthPlanType(String monthPlanType) 
	{
		this.monthPlanType = monthPlanType;
	}

	public String getMonthPlanType() 
	{
		return monthPlanType;
	}
	public void setMonthPlanMonth(Integer monthPlanMonth) 
	{
		this.monthPlanMonth = monthPlanMonth;
	}

	public Integer getMonthPlanMonth() 
	{
		return monthPlanMonth;
	}
	public void setMonthPlanYear(Integer monthPlanYear) 
	{
		this.monthPlanYear = monthPlanYear;
	}

	public Integer getMonthPlanYear() 
	{
		return monthPlanYear;
	}
	public void setMonthPlanStagnation(Integer monthPlanStagnation) 
	{
		this.monthPlanStagnation = monthPlanStagnation;
	}

	public Integer getMonthPlanStagnation() 
	{
		return monthPlanStagnation;
	}
	public void setMonthPlanResources(Integer monthPlanResources) 
	{
		this.monthPlanResources = monthPlanResources;
	}

	public Integer getMonthPlanResources() 
	{
		return monthPlanResources;
	}
	public void setMonthPlanStatus(Integer monthPlanStatus) 
	{
		this.monthPlanStatus = monthPlanStatus;
	}

	public Integer getMonthPlanStatus() 
	{
		return monthPlanStatus;
	}
	public void setAreaLevel(Integer areaLevel) 
	{
		this.areaLevel = areaLevel;
	}

	public Integer getAreaLevel() 
	{
		return areaLevel;
	}
	public void setMonthRoutingResources(Integer monthRoutingResources) 
	{
		this.monthRoutingResources = monthRoutingResources;
	}

	public Integer getMonthRoutingResources() 
	{
		return monthRoutingResources;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("monthPlanId", getMonthPlanId())
            .append("monthPlanName", getMonthPlanName())
            .append("monthPlanType", getMonthPlanType())
            .append("monthPlanMonth", getMonthPlanMonth())
            .append("monthPlanYear", getMonthPlanYear())
            .append("monthPlanStagnation", getMonthPlanStagnation())
            .append("monthPlanResources", getMonthPlanResources())
            .append("monthPlanStatus", getMonthPlanStatus())
            .append("areaLevel", getAreaLevel())
            .append("monthRoutingResources", getMonthRoutingResources())
            .toString();
    }
}
