package com.aaa.project.system.area.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 地区表 tbl_area
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Area extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 地区编号 */
	private Integer id;
	/** 地区代码 */
	private String areaID;
	/** 地区名称 */
	private String area;
	/** 市代码 */
	private String father;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAreaID(String areaID) 
	{
		this.areaID = areaID;
	}

	public String getAreaID() 
	{
		return areaID;
	}
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}
	public void setFather(String father) 
	{
		this.father = father;
	}

	public String getFather() 
	{
		return father;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaID", getAreaID())
            .append("area", getArea())
            .append("father", getFather())
            .toString();
    }
}
