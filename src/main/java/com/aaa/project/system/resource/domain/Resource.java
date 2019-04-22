package com.aaa.project.system.resource.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 资源点表 tbl_resource
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Resource extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 资源点编号 */
	private Long resourceId;
	/** 资源点地区编号 */
	private Integer resourceAreaId;
	/** 资源点名称 */
	private String resourceName;
	/** 资源点经度 */
	private String resourceLongitude;
	/** 资源点纬度 */
	private String resourceLatitude;
	/** 资源点地址 */
	private String resourceAddress;
	/** 资源点属性 */
	private String resourceDotType;
	/** 资源点状态(在网) */
	private String resourceStatus;
	/** 资源点开始时间 */
	private Date resourceStartTime;
	/** 资源点维护的单位 */
	private String resourceMaintainUnit;
	/** 资源点维护单位联系电话 */
	private String resourceMaintainUnitTel;
	/** 资源点维护单位联系人 */
	private String resourceMaintainUnitName;
	/** 资源点安检员电话 */
	private String resourceSadegrareManTel;
	/** 资源点删除状态 */
	private Integer resourceDeleted;
	/** 资源点计划使用日期 */
	private Integer resourcePlanDay;
	/** 资源点周期 */
	private Integer resourceCycle;
	/** 资源点代维公司 */
	private Integer resourceStagantionCompany;
	/** 上次巡检日期 */
	private Date resourceLastDate;
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

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStagantionCompanyName() {
		return stagantionCompanyName;
	}

	public void setStagantionCompanyName(String stagantionCompanyName) {
		this.stagantionCompanyName = stagantionCompanyName;
	}

	public String getDistributeStatus() {
		return distributeStatus;
	}

	public void setDistributeStatus(String distributeStatus) {
		this.distributeStatus = distributeStatus;
	}

	public void setResourceId(Long resourceId)
	{
		this.resourceId = resourceId;
	}

	public Long getResourceId() 
	{
		return resourceId;
	}
	public void setResourceAreaId(Integer resourceAreaId) 
	{
		this.resourceAreaId = resourceAreaId;
	}

	public Integer getResourceAreaId() 
	{
		return resourceAreaId;
	}
	public void setResourceName(String resourceName) 
	{
		this.resourceName = resourceName;
	}

	public String getResourceName() 
	{
		return resourceName;
	}
	public void setResourceLongitude(String resourceLongitude) 
	{
		this.resourceLongitude = resourceLongitude;
	}

	public String getResourceLongitude() 
	{
		return resourceLongitude;
	}
	public void setResourceLatitude(String resourceLatitude) 
	{
		this.resourceLatitude = resourceLatitude;
	}

	public String getResourceLatitude() 
	{
		return resourceLatitude;
	}
	public void setResourceAddress(String resourceAddress) 
	{
		this.resourceAddress = resourceAddress;
	}

	public String getResourceAddress() 
	{
		return resourceAddress;
	}
	public void setResourceDotType(String resourceDotType) 
	{
		this.resourceDotType = resourceDotType;
	}

	public String getResourceDotType() 
	{
		return resourceDotType;
	}
	public void setResourceStatus(String resourceStatus) 
	{
		this.resourceStatus = resourceStatus;
	}

	public String getResourceStatus() 
	{
		return resourceStatus;
	}
	public void setResourceStartTime(Date resourceStartTime) 
	{
		this.resourceStartTime = resourceStartTime;
	}

	public Date getResourceStartTime() 
	{
		return resourceStartTime;
	}
	public void setResourceMaintainUnit(String resourceMaintainUnit) 
	{
		this.resourceMaintainUnit = resourceMaintainUnit;
	}

	public String getResourceMaintainUnit() 
	{
		return resourceMaintainUnit;
	}
	public void setResourceMaintainUnitTel(String resourceMaintainUnitTel) 
	{
		this.resourceMaintainUnitTel = resourceMaintainUnitTel;
	}

	public String getResourceMaintainUnitTel() 
	{
		return resourceMaintainUnitTel;
	}
	public void setResourceMaintainUnitName(String resourceMaintainUnitName) 
	{
		this.resourceMaintainUnitName = resourceMaintainUnitName;
	}

	public String getResourceMaintainUnitName() 
	{
		return resourceMaintainUnitName;
	}
	public void setResourceSadegrareManTel(String resourceSadegrareManTel) 
	{
		this.resourceSadegrareManTel = resourceSadegrareManTel;
	}

	public String getResourceSadegrareManTel() 
	{
		return resourceSadegrareManTel;
	}
	public void setResourceDeleted(Integer resourceDeleted) 
	{
		this.resourceDeleted = resourceDeleted;
	}

	public Integer getResourceDeleted() 
	{
		return resourceDeleted;
	}
	public void setResourcePlanDay(Integer resourcePlanDay) 
	{
		this.resourcePlanDay = resourcePlanDay;
	}

	public Integer getResourcePlanDay() 
	{
		return resourcePlanDay;
	}
	public void setResourceCycle(Integer resourceCycle) 
	{
		this.resourceCycle = resourceCycle;
	}

	public Integer getResourceCycle() 
	{
		return resourceCycle;
	}
	public void setResourceStagantionCompany(Integer resourceStagantionCompany) 
	{
		this.resourceStagantionCompany = resourceStagantionCompany;
	}

	public Integer getResourceStagantionCompany() 
	{
		return resourceStagantionCompany;
	}
	public void setResourceLastDate(Date resourceLastDate) 
	{
		this.resourceLastDate = resourceLastDate;
	}

	public Date getResourceLastDate() 
	{
		return resourceLastDate;
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
            .append("resourceId", getResourceId())
            .append("resourceAreaId", getResourceAreaId())
            .append("resourceName", getResourceName())
            .append("resourceLongitude", getResourceLongitude())
            .append("resourceLatitude", getResourceLatitude())
            .append("resourceAddress", getResourceAddress())
            .append("resourceDotType", getResourceDotType())
            .append("resourceStatus", getResourceStatus())
            .append("resourceStartTime", getResourceStartTime())
            .append("resourceMaintainUnit", getResourceMaintainUnit())
            .append("resourceMaintainUnitTel", getResourceMaintainUnitTel())
            .append("resourceMaintainUnitName", getResourceMaintainUnitName())
            .append("resourceSadegrareManTel", getResourceSadegrareManTel())
            .append("resourceDeleted", getResourceDeleted())
            .append("resourcePlanDay", getResourcePlanDay())
            .append("resourceCycle", getResourceCycle())
            .append("resourceStagantionCompany", getResourceStagantionCompany())
            .append("resourceLastDate", getResourceLastDate())
            .append("resourceType", getResourceType())
            .toString();
    }
}
