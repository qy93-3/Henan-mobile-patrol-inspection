package com.aaa.project.system.check.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 月计划审核表 tbl_check
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Check extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 审核计划编号 */
	private Integer checkId;
	/** 审核计划内容 */
	private String checkContent;
	/** 审核月度计划编号 */
	private Integer checkMonthPlan;
	/** 审核结果 */
	private Boolean checkResult;

	public void setCheckId(Integer checkId) 
	{
		this.checkId = checkId;
	}

	public Integer getCheckId() 
	{
		return checkId;
	}
	public void setCheckContent(String checkContent) 
	{
		this.checkContent = checkContent;
	}

	public String getCheckContent() 
	{
		return checkContent;
	}
	public void setCheckMonthPlan(Integer checkMonthPlan) 
	{
		this.checkMonthPlan = checkMonthPlan;
	}

	public Integer getCheckMonthPlan() 
	{
		return checkMonthPlan;
	}
	public void setCheckResult(Boolean checkResult) 
	{
		this.checkResult = checkResult;
	}

	public Boolean getCheckResult() 
	{
		return checkResult;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("checkId", getCheckId())
            .append("checkContent", getCheckContent())
            .append("checkMonthPlan", getCheckMonthPlan())
            .append("checkResult", getCheckResult())
            .toString();
    }
}
