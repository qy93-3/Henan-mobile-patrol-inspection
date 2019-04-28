package com.aaa.project.system.planCalendar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 每日计划分配(显示日历)表 tbl_plan_calendar
 * 
 * @author aaa
 * @date 2019-04-24
 */
public class PlanCalendar extends BaseEntity implements Cloneable
{
	private static final long serialVersionUID = 1L;
	
	/** 日历编号 */
	private Integer calendarId;
	/** 日历月份 */
	private Integer calendarMonth;
	/** 日历每日状态 */
	private Integer calendarStatus;
	/** 日历年 */
	private Integer calendarYear;
	/** 日历天 */
	private Integer calendarDay;
	/** 每日日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date calendarDate;
	/** 月计划编号 */
	private Integer monthPlanId;
	/** 日资源数 */
	private Integer calendarDayResources;

	public void setCalendarId(Integer calendarId) 
	{
		this.calendarId = calendarId;
	}

	public Integer getCalendarId() 
	{
		return calendarId;
	}
	public void setCalendarMonth(Integer calendarMonth) 
	{
		this.calendarMonth = calendarMonth;
	}

	public Integer getCalendarMonth() 
	{
		return calendarMonth;
	}
	public void setCalendarStatus(Integer calendarStatus) 
	{
		this.calendarStatus = calendarStatus;
	}

	public Integer getCalendarStatus() 
	{
		return calendarStatus;
	}
	public void setCalendarYear(Integer calendarYear) 
	{
		this.calendarYear = calendarYear;
	}

	public Integer getCalendarYear() 
	{
		return calendarYear;
	}
	public void setCalendarDay(Integer calendarDay) 
	{
		this.calendarDay = calendarDay;
	}

	public Integer getCalendarDay() 
	{
		return calendarDay;
	}
	public void setCalendarDate(Date calendarDate) 
	{
		this.calendarDate = calendarDate;
	}

	public Date getCalendarDate() 
	{
		return calendarDate;
	}
	public void setMonthPlanId(Integer monthPlanId) 
	{
		this.monthPlanId = monthPlanId;
	}

	public Integer getMonthPlanId() 
	{
		return monthPlanId;
	}
	public void setCalendarDayResources(Integer calendarDayResources) 
	{
		this.calendarDayResources = calendarDayResources;
	}

	public Integer getCalendarDayResources() 
	{
		return calendarDayResources;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("calendarId", getCalendarId())
            .append("calendarMonth", getCalendarMonth())
            .append("calendarStatus", getCalendarStatus())
            .append("calendarYear", getCalendarYear())
            .append("calendarDay", getCalendarDay())
            .append("calendarDate", getCalendarDate())
            .append("monthPlanId", getMonthPlanId())
            .append("calendarDayResources", getCalendarDayResources())
            .toString();
    }

	public PlanCalendar(Integer calendarMonth, Integer calendarStatus, Integer calendarYear, Integer calendarDay, Date calendarDate, Integer monthPlanId, Integer calendarDayResources) {
		this.calendarMonth = calendarMonth;
		this.calendarStatus = calendarStatus;
		this.calendarYear = calendarYear;
		this.calendarDay = calendarDay;
		this.calendarDate = calendarDate;
		this.monthPlanId = monthPlanId;
		this.calendarDayResources = calendarDayResources;
	}

	public PlanCalendar() {
	}

	@Override
	public Object clone() {
		PlanCalendar planCalendar = null;
		try {
			planCalendar = (PlanCalendar) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return planCalendar;
	}
}
