package com.aaa.project.system.reply.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aaa.project.system.reply.mapper.ReplyMapper;
import com.aaa.project.system.reply.domain.Reply;
import com.aaa.project.system.reply.service.IReplyService;
import com.aaa.common.support.Convert;

/**
 * 巡检回复 服务层实现
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Service
public class ReplyServiceImpl implements IReplyService 
{
	@Autowired
	private ReplyMapper replyMapper;

	/**
     * 查询巡检回复信息
     * 
     * @param replyId 巡检回复ID
     * @return 巡检回复信息
     */
    @Override
	public Reply selectReplyById(Integer replyId)
	{
	    return replyMapper.selectReplyById(replyId);
	}
	
	/**
     * 查询巡检回复列表
     * 
     * @param reply 巡检回复信息
     * @return 巡检回复集合
     */
	@Override
	public List<Reply> selectReplyList(Reply reply)
	{
	    return replyMapper.selectReplyList(reply);
	}
	
    /**
     * 新增巡检回复
     * 
     * @param reply 巡检回复信息
     * @return 结果
     */
	@Override
	public int insertReply(Reply reply)
	{
	    return replyMapper.insertReply(reply);
	}
	
	/**
     * 修改巡检回复
     * 
     * @param reply 巡检回复信息
     * @return 结果
     */
	@Override
	public int updateReply(Reply reply)
	{
	    return replyMapper.updateReply(reply);
	}

	/**
     * 删除巡检回复对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReplyByIds(String ids)
	{
		return replyMapper.deleteReplyByIds(Convert.toStrArray(ids));
	}
	
}
