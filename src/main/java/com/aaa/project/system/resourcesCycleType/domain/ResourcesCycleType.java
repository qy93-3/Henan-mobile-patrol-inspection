package com.aaa.project.system.resourcesCycleType.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 资源巡检周期类型月份表 tbl_resources_cycle_type
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class ResourcesCycleType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 资源巡检周期编号 */
	private Integer resourcesCycleId;
	/** 资源巡检周期名称 */
	private String resourcesCycleName;
	/** 资源巡检周期月份 */
	private Integer resourcesCycleMonth;

	public void setResourcesCycleId(Integer resourcesCycleId) 
	{
		this.resourcesCycleId = resourcesCycleId;
	}

	public Integer getResourcesCycleId() 
	{
		return resourcesCycleId;
	}
	public void setResourcesCycleName(String resourcesCycleName) 
	{
		this.resourcesCycleName = resourcesCycleName;
	}

	public String getResourcesCycleName() 
	{
		return resourcesCycleName;
	}
	public void setResourcesCycleMonth(Integer resourcesCycleMonth) 
	{
		this.resourcesCycleMonth = resourcesCycleMonth;
	}

	public Integer getResourcesCycleMonth() 
	{
		return resourcesCycleMonth;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resourcesCycleId", getResourcesCycleId())
            .append("resourcesCycleName", getResourcesCycleName())
            .append("resourcesCycleMonth", getResourcesCycleMonth())
            .toString();
    }
}
