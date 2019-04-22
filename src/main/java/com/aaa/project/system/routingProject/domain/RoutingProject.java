package com.aaa.project.system.routingProject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检资源项目表 tbl_routing_project
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class RoutingProject extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 巡检项目编号 */
	private Integer routingProjectId;
	/** 巡检项目名称 */
	private String routingProjectName;

	public void setRoutingProjectId(Integer routingProjectId) 
	{
		this.routingProjectId = routingProjectId;
	}

	public Integer getRoutingProjectId() 
	{
		return routingProjectId;
	}
	public void setRoutingProjectName(String routingProjectName) 
	{
		this.routingProjectName = routingProjectName;
	}

	public String getRoutingProjectName() 
	{
		return routingProjectName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routingProjectId", getRoutingProjectId())
            .append("routingProjectName", getRoutingProjectName())
            .toString();
    }
}
