package com.aaa.project.system.areaLevel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 地域级别(省市县)表 tbl_area_level
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class AreaLevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 地域级别编号 */
	private Integer areaLevelId;
	/** 地域级别名称 */
	private String areaLevelName;

	public void setAreaLevelId(Integer areaLevelId) 
	{
		this.areaLevelId = areaLevelId;
	}

	public Integer getAreaLevelId() 
	{
		return areaLevelId;
	}
	public void setAreaLevelName(String areaLevelName) 
	{
		this.areaLevelName = areaLevelName;
	}

	public String getAreaLevelName() 
	{
		return areaLevelName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("areaLevelId", getAreaLevelId())
            .append("areaLevelName", getAreaLevelName())
            .toString();
    }
}
