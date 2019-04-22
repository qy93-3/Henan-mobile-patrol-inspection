package com.aaa.project.system.routingPeople.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检人员表 tbl_routing_people
 * 
 * @author aaa
 * @date 2019-04-22
 */
public class RoutingPeople extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 巡检人员编号 */
	private Integer routingId;
	/** 巡检人员姓名 */
	private String routingName;
	/** 巡检人员用户名 */
	private String routingUsername;
	/** 巡检人员密码 */
	private String routingPassword;
	/** 巡检人员身份证号 */
	private String routingIdcard;
	/** 巡检人员驻点公司编号 */
	private Integer stagnationId;
	/** 巡检人员积分 */
	private Integer routingPoint;

	public void setRoutingId(Integer routingId) 
	{
		this.routingId = routingId;
	}

	public Integer getRoutingId() 
	{
		return routingId;
	}
	public void setRoutingName(String routingName) 
	{
		this.routingName = routingName;
	}

	public String getRoutingName() 
	{
		return routingName;
	}
	public void setRoutingUsername(String routingUsername) 
	{
		this.routingUsername = routingUsername;
	}

	public String getRoutingUsername() 
	{
		return routingUsername;
	}
	public void setRoutingPassword(String routingPassword) 
	{
		this.routingPassword = routingPassword;
	}

	public String getRoutingPassword() 
	{
		return routingPassword;
	}
	public void setRoutingIdcard(String routingIdcard) 
	{
		this.routingIdcard = routingIdcard;
	}

	public String getRoutingIdcard() 
	{
		return routingIdcard;
	}
	public void setStagnationId(Integer stagnationId) 
	{
		this.stagnationId = stagnationId;
	}

	public Integer getStagnationId() 
	{
		return stagnationId;
	}
	public void setRoutingPoint(Integer routingPoint) 
	{
		this.routingPoint = routingPoint;
	}

	public Integer getRoutingPoint() 
	{
		return routingPoint;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("routingId", getRoutingId())
            .append("routingName", getRoutingName())
            .append("routingUsername", getRoutingUsername())
            .append("routingPassword", getRoutingPassword())
            .append("routingIdcard", getRoutingIdcard())
            .append("stagnationId", getStagnationId())
            .append("routingPoint", getRoutingPoint())
            .toString();
    }
}
