package com.aaa.project.system.danger.domain;

import com.aaa.project.system.dangerLevel.domain.DangerLevel;
import com.aaa.project.system.dangerStatus.domain.DangerStatus;
import com.aaa.project.system.mession.domain.Mession;
import com.aaa.project.system.resource.domain.Resource;
import com.aaa.project.system.routingPeople.domain.RoutingPeople;
import com.aaa.project.system.routingProject.domain.RoutingProject;
import com.aaa.project.system.site.domain.Site;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 巡检资源隐患表 tbl_danger
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Danger extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 隐患ID */
	private Integer dangerId;
	/** 隐患名称 */
	private String dangerName;
	/** 任务编号 */
	private Integer messionId;
	/** 隐患站点编号 */
	private Long dangerSiteId;
	/** 隐患资源点编号 */
	private Long dangerResourceId;
	/** 巡检项目编号 */
	private Integer routingProjectId;
	/** 严重程度 */
	private Integer dangerLevel;
	/** 隐患状态 */
	private Integer dangerStatus;
	/** 隐患来源 */
	private String dangerSources;
	/** 问题描述 */
	private String dangerDescription;
	/** 解决办法 */
	private String dangerSolution;
	/** 发现日期 */
	private Date dangerDate;
	/** 隐患照片 */
	private String dangerPicture;
	/** 提交巡检人员编号 */
	private Integer routingPersonId;

	private Mession tmession;
	private Site tsite;
	private DangerLevel tdangerLevel;
	private DangerStatus tdangerStatus;
	private RoutingPeople troutingPeople;


	public void setDangerId(Integer dangerId) 
	{
		this.dangerId = dangerId;
	}

	public Integer getDangerId() 
	{
		return dangerId;
	}
	public void setDangerName(String dangerName) 
	{
		this.dangerName = dangerName;
	}

	public String getDangerName() 
	{
		return dangerName;
	}
	public void setMessionId(Integer messionId) 
	{
		this.messionId = messionId;
	}

	public Integer getMessionId() 
	{
		return messionId;
	}
	public void setDangerSiteId(Long dangerSiteId) 
	{
		this.dangerSiteId = dangerSiteId;
	}

	public Long getDangerSiteId() 
	{
		return dangerSiteId;
	}
	public void setDangerResourceId(Long dangerResourceId) 
	{
		this.dangerResourceId = dangerResourceId;
	}

	public Long getDangerResourceId() 
	{
		return dangerResourceId;
	}
	public void setRoutingProjectId(Integer routingProjectId) 
	{
		this.routingProjectId = routingProjectId;
	}

	public Integer getRoutingProjectId() 
	{
		return routingProjectId;
	}
	public void setDangerLevel(Integer dangerLevel) 
	{
		this.dangerLevel = dangerLevel;
	}

	public Integer getDangerLevel() 
	{
		return dangerLevel;
	}
	public void setDangerStatus(Integer dangerStatus) 
	{
		this.dangerStatus = dangerStatus;
	}

	public Integer getDangerStatus() 
	{
		return dangerStatus;
	}
	public void setDangerSources(String dangerSources) 
	{
		this.dangerSources = dangerSources;
	}

	public String getDangerSources() 
	{
		return dangerSources;
	}
	public void setDangerDescription(String dangerDescription) 
	{
		this.dangerDescription = dangerDescription;
	}

	public String getDangerDescription() 
	{
		return dangerDescription;
	}
	public void setDangerSolution(String dangerSolution) 
	{
		this.dangerSolution = dangerSolution;
	}

	public String getDangerSolution() 
	{
		return dangerSolution;
	}
	public void setDangerDate(Date dangerDate) 
	{
		this.dangerDate = dangerDate;
	}

	public Date getDangerDate() 
	{
		return dangerDate;
	}
	public void setDangerPicture(String dangerPicture) 
	{
		this.dangerPicture = dangerPicture;
	}

	public String getDangerPicture() 
	{
		return dangerPicture;
	}
	public void setRoutingPersonId(Integer routingPersonId) 
	{
		this.routingPersonId = routingPersonId;
	}

	public Integer getRoutingPersonId() 
	{
		return routingPersonId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerId", getDangerId())
            .append("dangerName", getDangerName())
            .append("messionId", getMessionId())
            .append("dangerSiteId", getDangerSiteId())
            .append("dangerResourceId", getDangerResourceId())
            .append("routingProjectId", getRoutingProjectId())
            .append("dangerLevel", getDangerLevel())
            .append("dangerStatus", getDangerStatus())
            .append("dangerSources", getDangerSources())
            .append("dangerDescription", getDangerDescription())
            .append("dangerSolution", getDangerSolution())
            .append("dangerDate", getDangerDate())
            .append("dangerPicture", getDangerPicture())
            .append("routingPersonId", getRoutingPersonId())
            .toString();
    }

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Mession getTmession() {
		return tmession;
	}

	public void setTmession(Mession tmession) {
		this.tmession = tmession;
	}

	public Site getTsite() {
		return tsite;
	}

	public void setTsite(Site tsite) {
		this.tsite = tsite;
	}

	public DangerLevel getTdangerLevel() {
		return tdangerLevel;
	}

	public void setTdangerLevel(DangerLevel tdangerLevel) {
		this.tdangerLevel = tdangerLevel;
	}

	public DangerStatus getTdangerStatus() {
		return tdangerStatus;
	}

	public void setTdangerStatus(DangerStatus tdangerStatus) {
		this.tdangerStatus = tdangerStatus;
	}

	public RoutingPeople getTroutingPeople() {
		return troutingPeople;
	}

	public void setTroutingPeople(RoutingPeople troutingPeople) {
		this.troutingPeople = troutingPeople;
	}
}
