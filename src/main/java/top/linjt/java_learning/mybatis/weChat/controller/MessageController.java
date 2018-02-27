package top.linjt.java_learning.mybatis.weChat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.linjt.java_learning.entity.Page;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;
import top.linjt.java_learning.mybatis.weChat.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * 自动回复指令相关操作
* Title: CommandController
* Description:
* @author XxX
* @date 2018年1月31日下午1:21:17
* @since 
*/
@Controller
@RequestMapping("/weChat/message")
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
	 * 条件查询 分页
	 * @param command
	 * @param description
	 * @return List<MessageBean>
	 */
	@RequestMapping("queryByPage")
	@ResponseBody
	public Page<MessageBean> queryByPage(String command , String description,int page,int rows){
		
		Page<MessageBean> pageBean = new Page<MessageBean>();
		pageBean.setPageNumber(page);
		pageBean.setPageSize(rows);
		try {
			 messageService.queryByPage(command,description,pageBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageBean;
	}
	
	/**
	 * 新增
	 * @param message
	 * @return  
	 */
	@RequestMapping("insert")
	@ResponseBody
	public void insert (MessageBean message){
		
		messageService.insert(message);
	}
	
	/**
	 * 新增
	 * @param message
	 * @return  
	 */
	@RequestMapping("insertBatch")
	@ResponseBody
	public void insert (@RequestBody List<MessageBean> message){
		
		messageService.insertBatch(message);
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
			messageService.delete(id);
			return true;
		}
		return false;
	}
	
	/**
	 * 更新
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public boolean update (MessageBean message){
		if(null!=message&&message.getId()!=null){
			messageService.update(message);
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
