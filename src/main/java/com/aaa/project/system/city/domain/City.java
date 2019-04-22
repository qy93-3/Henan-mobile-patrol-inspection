package com.aaa.project.system.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 市表 tbl_city
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class City extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 市编号 */
	private Integer id;
	/** 市代码 */
	private String cityId;
	/** 市名称 */
	private String city;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCityId(String cityId) 
	{
		this.cityId = cityId;
	}

	public String getCityId() 
	{
		return cityId;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cityId", getCityId())
            .append("city", getCity())
            .toString();
    }
}
