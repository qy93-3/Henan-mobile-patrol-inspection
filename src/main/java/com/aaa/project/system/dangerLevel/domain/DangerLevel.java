package com.aaa.project.system.dangerLevel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检资源隐患严重程度表 tbl_danger_level
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class DangerLevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 严重程度编号 */
	private Integer dangerLevelId;
	/** 严重程度名称 */
	private String dangerLevelName;

	public void setDangerLevelId(Integer dangerLevelId) 
	{
		this.dangerLevelId = dangerLevelId;
	}

	public Integer getDangerLevelId() 
	{
		return dangerLevelId;
	}
	public void setDangerLevelName(String dangerLevelName) 
	{
		this.dangerLevelName = dangerLevelName;
	}

	public String getDangerLevelName() 
	{
		return dangerLevelName;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerLevelId", getDangerLevelId())
            .append("dangerLevelName", getDangerLevelName())
            .toString();
    }
}
