package com.aaa.project.system.systemRole.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 登录角色权限表 tbl_system_role
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class SystemRole extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 权限编号 */
	private Integer roleId;
	/** 权限名称 */
	private String roleName;
	/** 权限删除状态 */
	private Boolean roleDeleted;
	/** 地域级别 */
	private Integer areaLevel;

	public void setRoleId(Integer roleId) 
	{
		this.roleId = roleId;
	}

	public Integer getRoleId() 
	{
		return roleId;
	}
	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}

	public String getRoleName() 
	{
		return roleName;
	}
	public void setRoleDeleted(Boolean roleDeleted) 
	{
		this.roleDeleted = roleDeleted;
	}

	public Boolean getRoleDeleted() 
	{
		return roleDeleted;
	}
	public void setAreaLevel(Integer areaLevel) 
	{
		this.areaLevel = areaLevel;
	}

	public Integer getAreaLevel() 
	{
		return areaLevel;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("roleDeleted", getRoleDeleted())
            .append("areaLevel", getAreaLevel())
            .toString();
    }
}
