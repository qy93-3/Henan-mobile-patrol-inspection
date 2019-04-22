package com.aaa.project.system.reply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 巡检回复表 tbl_reply
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Reply extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 回复编号 */
	private Integer replyId;
	/** 任务编号 */
	private Integer messionId;
	/** 巡检项目编号 */
	private Integer routingProjectId;
	/** 是否有安全隐患 */
	private Boolean potentialSafetyHazard;
	/** 回复站点编号 */
	private Long replySiteId;
	/** 回复资源点编号 */
	private Long replyResourceId;
	/** 上传图片 */
	private String picture;
	/** 巡检人员编号 */
	private Integer routingPersonId;

	public void setReplyId(Integer replyId) 
	{
		this.replyId = replyId;
	}

	public Integer getReplyId() 
	{
		return replyId;
	}
	public void setMessionId(Integer messionId) 
	{
		this.messionId = messionId;
	}

	public Integer getMessionId() 
	{
		return messionId;
	}
	public void setRoutingProjectId(Integer routingProjectId) 
	{
		this.routingProjectId = routingProjectId;
	}

	public Integer getRoutingProjectId() 
	{
		return routingProjectId;
	}
	public void setPotentialSafetyHazard(Boolean potentialSafetyHazard) 
	{
		this.potentialSafetyHazard = potentialSafetyHazard;
	}

	public Boolean getPotentialSafetyHazard() 
	{
		return potentialSafetyHazard;
	}
	public void setReplySiteId(Long replySiteId) 
	{
		this.replySiteId = replySiteId;
	}

	public Long getReplySiteId() 
	{
		return replySiteId;
	}
	public void setReplyResourceId(Long replyResourceId) 
	{
		this.replyResourceId = replyResourceId;
	}

	public Long getReplyResourceId() 
	{
		return replyResourceId;
	}
	public void setPicture(String picture) 
	{
		this.picture = picture;
	}

	public String getPicture() 
	{
		return picture;
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
            .append("replyId", getReplyId())
            .append("messionId", getMessionId())
            .append("routingProjectId", getRoutingProjectId())
            .append("potentialSafetyHazard", getPotentialSafetyHazard())
            .append("replySiteId", getReplySiteId())
            .append("replyResourceId", getReplyResourceId())
            .append("picture", getPicture())
            .append("routingPersonId", getRoutingPersonId())
            .toString();
    }
}
