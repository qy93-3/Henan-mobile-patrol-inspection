package com.aaa.project.system.planCalendarStatus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 日历每日状态(日历上显示)表 tbl_plan_calendar_status
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class PlanCalendarStatus extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 月度计划状态编号 */
	private Integer monthStatusId;
	/** 月度计划状态名称 */
	private String monthStatusName;
	/** 月度计划状态颜色 */
	private String monthStatusColor;

	public void setMonthStatusId(Integer monthStatusId) 
	{
		this.monthStatusId = monthStatusId;
	}

	public Integer getMonthStatusId() 
	{
		return monthStatusId;
	}
	public void setMonthStatusName(String monthStatusName) 
	{
		this.monthStatusName = monthStatusName;
	}

	public String getMonthStatusName() 
	{
		return monthStatusName;
	}
	public void setMonthStatusColor(String monthStatusColor) 
	{
		this.monthStatusColor = monthStatusColor;
	}

	public String getMonthStatusColor() 
	{
		return monthStatusColor;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("monthStatusId", getMonthStatusId())
            .append("monthStatusName", getMonthStatusName())
            .append("monthStatusColor", getMonthStatusColor())
            .toString();
    }
}
