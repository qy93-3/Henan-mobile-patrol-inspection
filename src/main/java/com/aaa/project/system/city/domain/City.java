package com.aaa.project.system.city.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 市表 tbl_city
 * 
 * @author aaa
 * @date 2019-04-22
 */
public class City extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 市代码 */
	private Integer cityId;
	/** 市名称 */
	private String cityName;

	public void setCityId(Integer cityId) 
	{
		this.cityId = cityId;
	}

	public Integer getCityId() 
	{
		return cityId;
	}
	public void setCityName(String cityName) 
	{
		this.cityName = cityName;
	}

	public String getCityName() 
	{
		return cityName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cityId", getCityId())
            .append("cityName", getCityName())
            .toString();
    }
}
