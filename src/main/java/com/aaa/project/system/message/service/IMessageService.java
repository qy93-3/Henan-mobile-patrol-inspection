package com.aaa.project.system.message.service;

import com.aaa.project.system.message.domain.Message;
import java.util.List;

/**
 * 公告 服务层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface IMessageService 
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
     * 删除公告信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMessageByIds(String ids);
	
}
