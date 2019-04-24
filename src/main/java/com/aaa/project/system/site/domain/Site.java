package com.aaa.project.system.site.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 站点表 tbl_site
 * 
 * @author aaa
 * @date 2019-04-23
 */
public class Site extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 站点编号 */
	private Long siteId;
	/** 站点名称 */
	private String siteName;
	/** 站点名称缩写 */
	private String siteNameAbbreviate;
	/** 站点地区编号 */
	private Integer siteAreaId;
	/** 站点经度 */
	private String siteLongitude;
	/** 站点维度 */
	private String siteLatitude;
	/** 站点类型 */
	private String siteType;
	/** 站点地址 */
	private String siteAddress;
	/** 站点传输业务级别 */
	private String siteServiceLevel;
	/** 站点生命周期状态 */
	private String siteStatus;
	/** 站点入网时间 */
	private Date siteInnetDate;
	/** 站点总层数 */
	private String siteFloorCount;
	/** 站点产权单位 */
	private String sitePropertyDepartment;
	/** 站点产权性质 */
	private String siteProperty;
	/** 站点使用单位 */
	private String siteUserDepartment;
	/** 站点物业联系人 */
	private String siteContacts;
	/** 站点物业联系电话 */
	private String siteContactsMobile;
	/** 站点覆盖类型 */
	private String siteOverlayType;
	/** 站点删除状态 */
	private Integer siteDeleted;
	/** 站点计划日 */
	private Integer sitePlanDay;
	/** 站点巡检周期 */
	private Integer siteCycle;
	/** 站点代维公司 */
	private Integer siteStagantionCompany;
	/** 上次巡检日期 */
	private Date siteLastDate;
	/** 资源类型 */
	private String resourceType;

	/** 分配状态 */
	private String distributeStatus;

	/** 驻点名称 */
	private String stagantionCompanyName;

	/** 地区 */
	private String areaName;

	/** 周期名称 */
	private String cycleName;

	public String getDistributeStatus() {
		return distributeStatus;
	}

	public void setDistributeStatus(String distributeStatus) {
		this.distributeStatus = distributeStatus;
	}

	public String getStagantionCompanyName() {
		return stagantionCompanyName;
	}

	public void setStagantionCompanyName(String stagantionCompanyName) {
		this.stagantionCompanyName = stagantionCompanyName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public void setSiteId(Long siteId)
	{
		this.siteId = siteId;
	}

	public Long getSiteId() 
	{
		return siteId;
	}
	public void setSiteName(String siteName) 
	{
		this.siteName = siteName;
	}

	public String getSiteName() 
	{
		return siteName;
	}
	public void setSiteNameAbbreviate(String siteNameAbbreviate) 
	{
		this.siteNameAbbreviate = siteNameAbbreviate;
	}

	public String getSiteNameAbbreviate() 
	{
		return siteNameAbbreviate;
	}
	public void setSiteAreaId(Integer siteAreaId) 
	{
		this.siteAreaId = siteAreaId;
	}

	public Integer getSiteAreaId() 
	{
		return siteAreaId;
	}
	public void setSiteLongitude(String siteLongitude) 
	{
		this.siteLongitude = siteLongitude;
	}

	public String getSiteLongitude() 
	{
		return siteLongitude;
	}
	public void setSiteLatitude(String siteLatitude) 
	{
		this.siteLatitude = siteLatitude;
	}

	public String getSiteLatitude() 
	{
		return siteLatitude;
	}
	public void setSiteType(String siteType) 
	{
		this.siteType = siteType;
	}

	public String getSiteType() 
	{
		return siteType;
	}
	public void setSiteAddress(String siteAddress) 
	{
		this.siteAddress = siteAddress;
	}

	public String getSiteAddress() 
	{
		return siteAddress;
	}
	public void setSiteServiceLevel(String siteServiceLevel) 
	{
		this.siteServiceLevel = siteServiceLevel;
	}

	public String getSiteServiceLevel() 
	{
		return siteServiceLevel;
	}
	public void setSiteStatus(String siteStatus) 
	{
		this.siteStatus = siteStatus;
	}

	public String getSiteStatus() 
	{
		return siteStatus;
	}
	public void setSiteInnetDate(Date siteInnetDate) 
	{
		this.siteInnetDate = siteInnetDate;
	}

	public Date getSiteInnetDate() 
	{
		return siteInnetDate;
	}
	public void setSiteFloorCount(String siteFloorCount) 
	{
		this.siteFloorCount = siteFloorCount;
	}

	public String getSiteFloorCount() 
	{
		return siteFloorCount;
	}
	public void setSitePropertyDepartment(String sitePropertyDepartment) 
	{
		this.sitePropertyDepartment = sitePropertyDepartment;
	}

	public String getSitePropertyDepartment() 
	{
		return sitePropertyDepartment;
	}
	public void setSiteProperty(String siteProperty) 
	{
		this.siteProperty = siteProperty;
	}

	public String getSiteProperty() 
	{
		return siteProperty;
	}
	public void setSiteUserDepartment(String siteUserDepartment) 
	{
		this.siteUserDepartment = siteUserDepartment;
	}

	public String getSiteUserDepartment() 
	{
		return siteUserDepartment;
	}
	public void setSiteContacts(String siteContacts) 
	{
		this.siteContacts = siteContacts;
	}

	public String getSiteContacts() 
	{
		return siteContacts;
	}
	public void setSiteContactsMobile(String siteContactsMobile) 
	{
		this.siteContactsMobile = siteContactsMobile;
	}

	public String getSiteContactsMobile() 
	{
		return siteContactsMobile;
	}
	public void setSiteOverlayType(String siteOverlayType) 
	{
		this.siteOverlayType = siteOverlayType;
	}

	public String getSiteOverlayType() 
	{
		return siteOverlayType;
	}
	public void setSiteDeleted(Integer siteDeleted) 
	{
		this.siteDeleted = siteDeleted;
	}

	public Integer getSiteDeleted() 
	{
		return siteDeleted;
	}
	public void setSitePlanDay(Integer sitePlanDay) 
	{
		this.sitePlanDay = sitePlanDay;
	}

	public Integer getSitePlanDay() 
	{
		return sitePlanDay;
	}
	public void setSiteCycle(Integer siteCycle) 
	{
		this.siteCycle = siteCycle;
	}

	public Integer getSiteCycle() 
	{
		return siteCycle;
	}
	public void setSiteStagantionCompany(Integer siteStagantionCompany) 
	{
		this.siteStagantionCompany = siteStagantionCompany;
	}

	public Integer getSiteStagantionCompany() 
	{
		return siteStagantionCompany;
	}
	public void setSiteLastDate(Date siteLastDate) 
	{
		this.siteLastDate = siteLastDate;
	}

	public Date getSiteLastDate() 
	{
		return siteLastDate;
	}
	public void setResourceType(String resourceType) 
	{
		this.resourceType = resourceType;
	}

	public String getResourceType() 
	{
		return resourceType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("siteId", getSiteId())
            .append("siteName", getSiteName())
            .append("siteNameAbbreviate", getSiteNameAbbreviate())
            .append("siteAreaId", getSiteAreaId())
            .append("siteLongitude", getSiteLongitude())
            .append("siteLatitude", getSiteLatitude())
            .append("siteType", getSiteType())
            .append("siteAddress", getSiteAddress())
            .append("siteServiceLevel", getSiteServiceLevel())
            .append("siteStatus", getSiteStatus())
            .append("siteInnetDate", getSiteInnetDate())
            .append("siteFloorCount", getSiteFloorCount())
            .append("sitePropertyDepartment", getSitePropertyDepartment())
            .append("siteProperty", getSiteProperty())
            .append("siteUserDepartment", getSiteUserDepartment())
            .append("siteContacts", getSiteContacts())
            .append("siteContactsMobile", getSiteContactsMobile())
            .append("siteOverlayType", getSiteOverlayType())
            .append("siteDeleted", getSiteDeleted())
            .append("sitePlanDay", getSitePlanDay())
            .append("siteCycle", getSiteCycle())
            .append("siteStagantionCompany", getSiteStagantionCompany())
            .append("siteLastDate", getSiteLastDate())
            .append("resourceType", getResourceType())
            .toString();
    }
}
