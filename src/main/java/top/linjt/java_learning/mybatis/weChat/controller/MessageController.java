package top.linjt.java_learning.mybatis.weChat.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;
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
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/**
	 * 根据主键获取单个MessageBean 对象
	 * @param id
	 * @return MessageBean
	 */
	@RequestMapping("get")
	@ResponseBody
	public MessageBean get(int id){
		
		return messageService.get(id);
	}
	
	/**
	 * 查询所有
	 * @return List<MessageBean>
	 */
	@RequestMapping("list")
	@ResponseBody
	public List<MessageBean> list(){
		
		return messageService.list();
	}
	
	/**
	 * 条件查询
	 * @param command
	 * @param description
	 * @return List<MessageBean>
	 */
	@RequestMapping("query")
	@ResponseBody
	public List<MessageBean> query(String command , String description){
		
		System.out.println(command + "-" + description);
		try {
			return messageService.query(command,description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增
	 * @param MessageBean
	 * @return  
	 */
	@RequestMapping("insert")
	@ResponseBody
	public void insert (MessageBean message){
		
		messageService.insert(message);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return @RequestParam(value="id[]") 
	 */
	@RequestMapping("delete")
	@ResponseBody
	public boolean delete (int[] id){
		if(null!=id && id.length>0){
			int i = messageService.delete(id);
			return true;
		}
		return false;
	}
	
	
	/**
	 * 测试事务管理
	 */
	@RequestMapping("trantest")
	@ResponseBody
	public void trantest(){
		
		messageService.test();
	}
}
