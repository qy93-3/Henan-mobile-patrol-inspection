package com.aaa.project.system.planDayStatus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检资源日计划状态表 tbl_plan_day_status
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class PlanDayStatus extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 日计划状态编号 */
	private Integer dayStatusId;
	/** 日计划状态名称 */
	private String dayStatusName;

	public void setDayStatusId(Integer dayStatusId) 
	{
		this.dayStatusId = dayStatusId;
	}

	public Integer getDayStatusId() 
	{
		return dayStatusId;
	}
	public void setDayStatusName(String dayStatusName) 
	{
		this.dayStatusName = dayStatusName;
	}

	public String getDayStatusName() 
	{
		return dayStatusName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dayStatusId", getDayStatusId())
            .append("dayStatusName", getDayStatusName())
            .toString();
    }
}
