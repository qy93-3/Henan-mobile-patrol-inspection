package com.aaa.project.system.area.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 地区表 tbl_area
 * 
 * @author aaa
 * @date 2019-04-22
 */
public class Area extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 地区代码 */
	private Integer areaId;
	/** 地区名称 */
	private String areaName;
	/** 市代码 */
	private Integer father;

	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}
	public void setAreaName(String areaName) 
	{
		this.areaName = areaName;
	}

	public String getAreaName() 
	{
		return areaName;
	}
	public void setFather(Integer father) 
	{
		this.father = father;
	}

	public Integer getFather() 
	{
		return father;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaId", getAreaId())
            .append("areaName", getAreaName())
            .append("father", getFather())
            .toString();
    }
}
