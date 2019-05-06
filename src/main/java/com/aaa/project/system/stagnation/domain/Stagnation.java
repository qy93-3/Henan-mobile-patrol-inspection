package com.aaa.project.system.stagnation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 驻点表 tbl_stagnation
 * 
 * @author aaa
 * @date 2019-04-22
 */
public class Stagnation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 驻点代码 */
	private Integer stagnationId;
	/** 驻点名称 */
	private String stagnationPname;
	/** 城市代码 */
	private Integer addressId;
	/** 上级驻点代码 */
	private Integer pID;
	/** 驻点资源总数 */
	private Integer resourcesNumber;
	/** 地域级别编号 */
	private Integer areaLevelId;
	/** 省公司所属资源点数 */
	private Integer spCount;

	/** 上级驻点名称 */
	private String fatherName;
	/** 市县名称 */
	private String areaName;
	/** 驻点级别 */
	private String levelName;

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setStagnationId(Integer stagnationId)
	{
		this.stagnationId = stagnationId;
	}

	public Integer getStagnationId() 
	{
		return stagnationId;
	}
	public void setStagnationPname(String stagnationPname) 
	{
		this.stagnationPname = stagnationPname;
	}

	public String getStagnationPname() 
	{
		return stagnationPname;
	}
	public void setAddressId(Integer addressId) 
	{
		this.addressId = addressId;
	}

	public Integer getAddressId() 
	{
		return addressId;
	}
	public void setPID(Integer pID) 
	{
		this.pID = pID;
	}

	public Integer getPID() 
	{
		return pID;
	}
	public void setResourcesNumber(Integer resourcesNumber) 
	{
		this.resourcesNumber = resourcesNumber;
	}

	public Integer getResourcesNumber() 
	{
		return resourcesNumber;
	}
	public void setAreaLevelId(Integer areaLevelId) 
	{
		this.areaLevelId = areaLevelId;
	}

	public Integer getAreaLevelId() 
	{
		return areaLevelId;
	}

	public Integer getSpCount() {
		return spCount;
	}

	public void setSpCount(Integer spCount) {
		this.spCount = spCount;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stagnationId", getStagnationId())
            .append("stagnationPname", getStagnationPname())
            .append("addressId", getAddressId())
            .append("pID", getPID())
            .append("resourcesNumber", getResourcesNumber())
            .append("areaLevelId", getAreaLevelId())
            .toString();
    }
}
