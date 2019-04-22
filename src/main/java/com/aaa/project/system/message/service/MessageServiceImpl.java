package com.aaa.project.system.message.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.message.mapper.MessageMapper;
import com.aaa.project.system.message.domain.Message;
import com.aaa.project.system.message.service.IMessageService;
import com.aaa.common.support.Convert;

/**
 * 公告 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class MessageServiceImpl implements IMessageService 
{
	@Autowired
	private MessageMapper messageMapper;

	/**
     * 查询公告信息
     * 
     * @param messageId 公告ID
     * @return 公告信息
     */
    @Override
	public Message selectMessageById(Integer messageId)
	{
	    return messageMapper.selectMessageById(messageId);
	}
	
	/**
     * 查询公告列表
     * 
     * @param message 公告信息
     * @return 公告集合
     */
	@Override
	public List<Message> selectMessageList(Message message)
	{
	    return messageMapper.selectMessageList(message);
	}
	
    /**
     * 新增公告
     * 
     * @param message 公告信息
     * @return 结果
     */
	@Override
	public int insertMessage(Message message)
	{
	    return messageMapper.insertMessage(message);
	}
	
	/**
     * 修改公告
     * 
     * @param message 公告信息
     * @return 结果
     */
	@Override
	public int updateMessage(Message message)
	{
	    return messageMapper.updateMessage(message);
	}

	/**
     * 删除公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMessageByIds(String ids)
	{
		return messageMapper.deleteMessageByIds(Convert.toStrArray(ids));
	}
	
}
