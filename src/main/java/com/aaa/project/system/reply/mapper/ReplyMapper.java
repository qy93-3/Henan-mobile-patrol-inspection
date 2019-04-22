package com.aaa.project.system.reply.mapper;

import com.aaa.project.system.reply.domain.Reply;
import java.util.List;	

/**
 * 巡检回复 数据层
 * 
 * @author aaa
 * @date 2019-04-20
 */
public interface ReplyMapper 
{
	/**
     * 查询巡检回复信息
     * 
     * @param replyId 巡检回复ID
     * @return 巡检回复信息
     */
	public Reply selectReplyById(Integer replyId);
	
	/**
     * 查询巡检回复列表
     * 
     * @param reply 巡检回复信息
     * @return 巡检回复集合
     */
	public List<Reply> selectReplyList(Reply reply);
	
	/**
     * 新增巡检回复
     * 
     * @param reply 巡检回复信息
     * @return 结果
     */
	public int insertReply(Reply reply);
	
	/**
     * 修改巡检回复
     * 
     * @param reply 巡检回复信息
     * @return 结果
     */
	public int updateReply(Reply reply);
	
	/**
     * 删除巡检回复
     * 
     * @param replyId 巡检回复ID
     * @return 结果
     */
	public int deleteReplyById(Integer replyId);
	
	/**
     * 批量删除巡检回复
     * 
     * @param replyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteReplyByIds(String[] replyIds);
	
}