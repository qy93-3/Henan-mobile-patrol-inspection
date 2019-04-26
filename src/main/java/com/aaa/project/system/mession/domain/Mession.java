package com.aaa.project.system.mession.domain;

import com.aaa.project.system.messionStatus.domain.MessionStatus;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.site.domain.Site;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 日计划分配任务表 tbl_mession
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Mession extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 任务表编号 */
	private Integer messionId;
	/** 巡检人员编号 */
	private Integer messionRoutingId;
	/** 任务日计划编号 */
	private Integer messionDayId;
	/** 任务站点编号 */
	private Long messionSiteId;
	/** 任务资源点编号 */
	private Long messionResourceId;
	/** 任务日期 */
	private Date messionDate;
	/** 任务状态 */
	private Integer messionStatus;
	/** 任务签到状态 */
	private Integer messionCheckStatus;
	/** 任务驻点编号 */
	private Integer messionStagnationId;
	/** 任务签到经度 */
	private String messionLongitude;
	/** 任务签到纬度 */
	private String messionLatitude;
	/** 站点*/
	private Site site;
	/**资源*/
	private Resource resource;

	private MessionStatus tblMessionStatus;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setMessionId(Integer messionId)
	{
		this.messionId = messionId;
	}

	public Integer getMessionId() 
	{
		return messionId;
	}
	public void setMessionRoutingId(Integer messionRoutingId) 
	{
		this.messionRoutingId = messionRoutingId;
	}

	public Integer getMessionRoutingId() 
	{
		return messionRoutingId;
	}
	public void setMessionDayId(Integer messionDayId) 
	{
		this.messionDayId = messionDayId;
	}

	public Integer getMessionDayId() 
	{
		return messionDayId;
	}
	public void setMessionSiteId(Long messionSiteId) 
	{
		this.messionSiteId = messionSiteId;
	}

	public Long getMessionSiteId() 
	{
		return messionSiteId;
	}
	public void setMessionResourceId(Long messionResourceId) 
	{
		this.messionResourceId = messionResourceId;
	}

	public Long getMessionResourceId() 
	{
		return messionResourceId;
	}
	public void setMessionDate(Date messionDate) 
	{
		this.messionDate = messionDate;
	}

	public Date getMessionDate() 
	{
		return messionDate;
	}
	public void setMessionStatus(Integer messionStatus) 
	{
		this.messionStatus = messionStatus;
	}

	public Integer getMessionStatus() 
	{
		return messionStatus;
	}
	public void setMessionCheckStatus(Integer messionCheckStatus) 
	{
		this.messionCheckStatus = messionCheckStatus;
	}

	public Integer getMessionCheckStatus() 
	{
		return messionCheckStatus;
	}
	public void setMessionStagnationId(Integer messionStagnationId) 
	{
		this.messionStagnationId = messionStagnationId;
	}

	public Integer getMessionStagnationId() 
	{
		return messionStagnationId;
	}
	public void setMessionLongitude(String messionLongitude) 
	{
		this.messionLongitude = messionLongitude;
	}

	public String getMessionLongitude() 
	{
		return messionLongitude;
	}
	public void setMessionLatitude(String messionLatitude) 
	{
		this.messionLatitude = messionLatitude;
	}

	public String getMessionLatitude() 
	{
		return messionLatitude;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messionId", getMessionId())
            .append("messionRoutingId", getMessionRoutingId())
            .append("messionDayId", getMessionDayId())
            .append("messionSiteId", getMessionSiteId())
            .append("messionResourceId", getMessionResourceId())
            .append("messionDate", getMessionDate())
            .append("messionStatus", getMessionStatus())
            .append("messionCheckStatus", getMessionCheckStatus())
            .append("messionStagnationId", getMessionStagnationId())
            .append("messionLongitude", getMessionLongitude())
            .append("messionLatitude", getMessionLatitude())
            .toString();
    }

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public MessionStatus getTblMessionStatus() {
		return tblMessionStatus;
	}

	public void setTblMessionStatus(MessionStatus tblMessionStatus) {
		this.tblMessionStatus = tblMessionStatus;
	}
}
