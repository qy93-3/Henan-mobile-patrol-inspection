package com.aaa.project.system.reply.controller;

import java.util.List;

import com.aaa.project.system.resource.service.IResourceService;
import com.aaa.project.system.routingProject.service.IRoutingProjectService;
import com.aaa.project.system.site.service.ISiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.reply.domain.Reply;
import com.aaa.project.system.reply.service.IReplyService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;

/**
 * 巡检回复 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/reply")
public class ReplyController extends BaseController
{
    private String prefix = "system/reply";
	
	@Autowired
	private IReplyService replyService;

	@Autowired
	private IRoutingProjectService routingProjectService;

	@Autowired
	private ISiteService siteService;

	@Autowired
	private IResourceService resourceService;
	
	@RequiresPermissions("system:reply:view")
	@GetMapping()
	public String reply()
	{
	    return prefix + "/reply";
	}
	
	/**
	 * 查询巡检回复列表
	 */
	@RequiresPermissions("system:reply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Reply reply)
	{
		startPage();
        List<Reply> list = replyService.selectReplyList(reply);
		for (Reply reply1 : list) {
			//设置表格显示的巡检项目名称
			reply1.setRoutingProject(routingProjectService.selectRoutingProjectById(reply1.getRoutingProjectId()).getRoutingProjectName());
			//设置表格显示的隐患状态
			reply1.setSafety(reply1.getPotentialSafetyHazard()==false?"否":"是");
			if (reply1.getReplyResourceId()==null){
				//若回复的资源点为空，则资源类型是站点
				reply1.setResourcesName(siteService.selectSiteById(reply1.getReplySiteId()).getSiteName());
			}else{
				reply1.setResourcesName(resourceService.selectResourceById(reply1.getReplyResourceId()).getResourceName());
			}
		}
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检回复列表
	 */
	@RequiresPermissions("system:reply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Reply reply)
    {
    	List<Reply> list = replyService.selectReplyList(reply);
        ExcelUtil<Reply> util = new ExcelUtil<Reply>(Reply.class);
        return util.exportExcel(list, "reply");
    }
	
	/**
	 * 新增巡检回复
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检回复
	 */
	@RequiresPermissions("system:reply:add")
	@Log(title = "巡检回复", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Reply reply)
	{		
		return toAjax(replyService.insertReply(reply));
	}

	/**
	 * 修改巡检回复
	 */
	@GetMapping("/edit/{replyId}")
	public String edit(@PathVariable("replyId") Integer replyId, ModelMap mmap)
	{
		Reply reply = replyService.selectReplyById(replyId);
		mmap.put("reply", reply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检回复
	 */
	@RequiresPermissions("system:reply:edit")
	@Log(title = "巡检回复", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Reply reply)
	{		
		return toAjax(replyService.updateReply(reply));
	}
	
	/**
	 * 删除巡检回复
	 */
	@RequiresPermissions("system:reply:remove")
	@Log(title = "巡检回复", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(replyService.deleteReplyByIds(ids));
	}
	
}
