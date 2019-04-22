package com.aaa.project.system.message.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.framework.web.domain.BaseEntity;

/**
 * 公告表 tbl_message
 * 
 * @author aaa
 * @date 2019-04-20
 */
public class Message extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 公告编号 */
	private Integer messageId;
	/** 公告标题 */
	private String messageTitle;
	/** 公告内容 */
	private String messageContent;
	/** 公告图片 */
	private String messagePicture;

	public void setMessageId(Integer messageId) 
	{
		this.messageId = messageId;
	}

	public Integer getMessageId() 
	{
		return messageId;
	}
	public void setMessageTitle(String messageTitle) 
	{
		this.messageTitle = messageTitle;
	}

	public String getMessageTitle() 
	{
		return messageTitle;
	}
	public void setMessageContent(String messageContent) 
	{
		this.messageContent = messageContent;
	}

	public String getMessageContent() 
	{
		return messageContent;
	}
	public void setMessagePicture(String messagePicture) 
	{
		this.messagePicture = messagePicture;
	}

	public String getMessagePicture() 
	{
		return messagePicture;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("messageTitle", getMessageTitle())
            .append("messageContent", getMessageContent())
            .append("messagePicture", getMessagePicture())
            .toString();
    }
}
