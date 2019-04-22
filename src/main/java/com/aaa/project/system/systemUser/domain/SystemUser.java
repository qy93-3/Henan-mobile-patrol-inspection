package com.aaa.project.system.systemUser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 用户表 tbl_system_user
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class SystemUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 用户编号 */
	private Integer userId;
	/** 用户姓名 */
	private String userName;
	/** 用户名 */
	private String userUsername;
	/** 用户密码 */
	private String userPassword;
	/** 用户手机号 */
	private String userPhone;
	/** 性别 */
	private Integer gender;
	/** 邮箱地址 */
	private String userEmail;
	/** 登录日期 */
	private Date loginDate;
	/** 编辑日期 */
	private Date editDate;
	/** 登录ip地址 */
	private String loginIp;
	/** 登录次数 */
	private Integer loginCount;
	/** 用户权限编号 */
	private Integer roleId;
	/** 地域级别 */
	private Integer areaLevel;
	/** 城市代码 */
	private Integer addressId;

	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setUserUsername(String userUsername) 
	{
		this.userUsername = userUsername;
	}

	public String getUserUsername() 
	{
		return userUsername;
	}
	public void setUserPassword(String userPassword) 
	{
		this.userPassword = userPassword;
	}

	public String getUserPassword() 
	{
		return userPassword;
	}
	public void setUserPhone(String userPhone) 
	{
		this.userPhone = userPhone;
	}

	public String getUserPhone() 
	{
		return userPhone;
	}
	public void setGender(Integer gender) 
	{
		this.gender = gender;
	}

	public Integer getGender() 
	{
		return gender;
	}
	public void setUserEmail(String userEmail) 
	{
		this.userEmail = userEmail;
	}

	public String getUserEmail() 
	{
		return userEmail;
	}
	public void setLoginDate(Date loginDate) 
	{
		this.loginDate = loginDate;
	}

	public Date getLoginDate() 
	{
		return loginDate;
	}
	public void setEditDate(Date editDate) 
	{
		this.editDate = editDate;
	}

	public Date getEditDate() 
	{
		return editDate;
	}
	public void setLoginIp(String loginIp) 
	{
		this.loginIp = loginIp;
	}

	public String getLoginIp() 
	{
		return loginIp;
	}
	public void setLoginCount(Integer loginCount) 
	{
		this.loginCount = loginCount;
	}

	public Integer getLoginCount() 
	{
		return loginCount;
	}
	public void setRoleId(Integer roleId) 
	{
		this.roleId = roleId;
	}

	public Integer getRoleId() 
	{
		return roleId;
	}
	public void setAreaLevel(Integer areaLevel) 
	{
		this.areaLevel = areaLevel;
	}

	public Integer getAreaLevel() 
	{
		return areaLevel;
	}
	public void setAddressId(Integer addressId) 
	{
		this.addressId = addressId;
	}

	public Integer getAddressId() 
	{
		return addressId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userUsername", getUserUsername())
            .append("userPassword", getUserPassword())
            .append("userPhone", getUserPhone())
            .append("gender", getGender())
            .append("userEmail", getUserEmail())
            .append("loginDate", getLoginDate())
            .append("editDate", getEditDate())
            .append("loginIp", getLoginIp())
            .append("loginCount", getLoginCount())
            .append("roleId", getRoleId())
            .append("areaLevel", getAreaLevel())
            .append("addressId", getAddressId())
            .toString();
    }
}
