package top.linjt.java_learning.mybatis.weChat.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.linjt.java_learning.mybatis.weChat.pojo.CommandBean;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;
import top.linjt.java_learning.mybatis.weChat.service.CommandService;
import top.linjt.java_learning.mybatis.weChat.service.MessageService;

/**
 * 自动回复指令相关操作
* Title: CommandController
* Description:
* @author XxX
* @date 2018年1月31日下午1:21:17
* @since 
*/
@Controller
@RequestMapping("/weChat/command")
public class CommandController {

	@Autowired
	private CommandService commandService;
	
	/**
	 * 条件查询
	 * @param command
	 * @param description
	 * @return List<CommandBean>
	 */
	@RequestMapping("query")
	@ResponseBody
	public List<CommandBean> query(String command , String description){
		
		System.out.println(command + "-" + description);
		
		return commandService.query(command,description);
	}
	
}
