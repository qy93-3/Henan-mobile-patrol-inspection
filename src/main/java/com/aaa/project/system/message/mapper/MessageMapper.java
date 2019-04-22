package com.aaa.project.system.message.mapper;

import com.aaa.project.system.message.domain.Message;
import java.util.List;	

/**
 * 公告 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface MessageMapper 
{
	/**
     * 查询公告信息
     * 
     * @param messageId 公告ID
     * @return 公告信息
     */
	public Message selectMessageById(Integer messageId);
	
	/**
     * 查询公告列表
     * 
     * @param message 公告信息
     * @return 公告集合
     */
	public List<Message> selectMessageList(Message message);
	
	/**
     * 新增公告
     * 
     * @param message 公告信息
     * @return 结果
     */
	public int insertMessage(Message message);
	
	/**
     * 修改公告
     * 
     * @param message 公告信息
     * @return 结果
     */
	public int updateMessage(Message message);
	
	/**
     * 删除公告
     * 
     * @param messageId 公告ID
     * @return 结果
     */
	public int deleteMessageById(Integer messageId);
	
	/**
     * 批量删除公告
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMessageByIds(String[] messageIds);
	
}