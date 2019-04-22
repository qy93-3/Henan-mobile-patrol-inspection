package com.aaa.project.system.change.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 月计划变更申请表 tbl_change
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Change extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 申请变更编号 */
	private Integer changeId;
	/** 申请变更原因 */
	private String changeReason;
	/** 申请变更状态 */
	private Boolean changeStatus;
	/** 变更月计划编号 */
	private Integer changeMonthPlan;
	/** 提交申请人员编号 */
	private Integer userId;

	public void setChangeId(Integer changeId) 
	{
		this.changeId = changeId;
	}

	public Integer getChangeId() 
	{
		return changeId;
	}
	public void setChangeReason(String changeReason) 
	{
		this.changeReason = changeReason;
	}

	public String getChangeReason() 
	{
		return changeReason;
	}
	public void setChangeStatus(Boolean changeStatus) 
	{
		this.changeStatus = changeStatus;
	}

	public Boolean getChangeStatus() 
	{
		return changeStatus;
	}
	public void setChangeMonthPlan(Integer changeMonthPlan) 
	{
		this.changeMonthPlan = changeMonthPlan;
	}

	public Integer getChangeMonthPlan() 
	{
		return changeMonthPlan;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("changeId", getChangeId())
            .append("changeReason", getChangeReason())
            .append("changeStatus", getChangeStatus())
            .append("changeMonthPlan", getChangeMonthPlan())
            .append("userId", getUserId())
            .toString();
    }
}
