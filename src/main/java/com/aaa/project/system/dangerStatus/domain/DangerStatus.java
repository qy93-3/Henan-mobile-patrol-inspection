package com.aaa.project.system.dangerStatus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检资源隐患解决状态表 tbl_danger_status
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class DangerStatus extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 隐患状态编号 */
	private Integer dangerHandleId;
	/** 隐患状态名称 */
	private String dangerHandleName;

	public void setDangerHandleId(Integer dangerHandleId) 
	{
		this.dangerHandleId = dangerHandleId;
	}

	public Integer getDangerHandleId() 
	{
		return dangerHandleId;
	}
	public void setDangerHandleName(String dangerHandleName) 
	{
		this.dangerHandleName = dangerHandleName;
	}

	public String getDangerHandleName() 
	{
		return dangerHandleName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerHandleId", getDangerHandleId())
            .append("dangerHandleName", getDangerHandleName())
            .toString();
    }
}
