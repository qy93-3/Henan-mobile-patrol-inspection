package com.aaa.project.system.message.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.aaa.common.exception.file.FileNameLengthLimitExceededException;
import com.aaa.common.utils.file.FileUploadUtils;
import com.aaa.common.utils.file.FileUtils;
import com.aaa.framework.config.DouDouConfig;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.aaa.framework.aspectj.lang.annotation.Log;
import com.aaa.framework.aspectj.lang.enums.BusinessType;
import com.aaa.project.system.message.domain.Message;
import com.aaa.project.system.message.service.IMessageService;
import com.aaa.framework.web.controller.BaseController;
import com.aaa.framework.web.page.TableDataInfo;
import com.aaa.framework.web.domain.AjaxResult;
import com.aaa.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公告 信息操作处理
 * 
 * @author aaa
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/system/message")
public class MessageController extends BaseController
{
    private String prefix = "system/message";
	
	@Autowired
	private IMessageService messageService;
	
	@RequiresPermissions("system:message:view")
	@GetMapping()
	public String message()
	{
	    return prefix + "/message";
	}
	
	/**
	 * 查询公告列表
	 */
	@RequiresPermissions("system:message:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Message message)
	{
		startPage();
        List<Message> list = messageService.selectMessageList(message);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出公告列表
	 */
	@RequiresPermissions("system:message:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Message message)
    {
    	List<Message> list = messageService.selectMessageList(message);
        ExcelUtil<Message> util = new ExcelUtil<Message>(Message.class);
        return util.exportExcel(list, "message");
    }
	
	/**
	 * 新增公告
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公告
	 */
	@RequiresPermissions("system:message:add")
	@Log(title = "公告", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestParam MultipartFile file,@RequestParam String messageTitle,@RequestParam String messageContent) throws IOException {
		Message message = new Message();
		message.setMessageTitle(messageTitle);
		message.setMessageContent(messageContent);
		String avatarPath = DouDouConfig.getAvatarPath();
		String fileName = FileUploadUtils.upload(avatarPath, file);
		message.setMessagePicture(fileName);
		return toAjax(messageService.insertMessage(message));
	}


	/**
	 * 修改公告
	 */
	@GetMapping("/edit/{messageId}")
	public String edit(@PathVariable("messageId") Integer messageId, ModelMap mmap)
	{
		Message message = messageService.selectMessageById(messageId);
		mmap.put("message", message);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公告
	 */
	@RequiresPermissions("system:message:edit")
	@Log(title = "公告", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Message message)
	{		
		return toAjax(messageService.updateMessage(message));
	}
	
	/**
	 * 删除公告
	 */
	@RequiresPermissions("system:message:remove")
	@Log(title = "公告", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(messageService.deleteMessageByIds(ids));
	}
	
}
