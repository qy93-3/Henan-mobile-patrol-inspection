package com.aaa.project.system.messionStatus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 任务状态表 tbl_mession_status
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class MessionStatus extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 任务状态编号 */
	private Integer messionStatusId;
	/** 任务状态名称 */
	private String messionStatusName;

	public void setMessionStatusId(Integer messionStatusId) 
	{
		this.messionStatusId = messionStatusId;
	}

	public Integer getMessionStatusId() 
	{
		return messionStatusId;
	}
	public void setMessionStatusName(String messionStatusName) 
	{
		this.messionStatusName = messionStatusName;
	}

	public String getMessionStatusName() 
	{
		return messionStatusName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messionStatusId", getMessionStatusId())
            .append("messionStatusName", getMessionStatusName())
            .toString();
    }
}
