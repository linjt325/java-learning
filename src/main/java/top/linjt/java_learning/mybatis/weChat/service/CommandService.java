package top.linjt.java_learning.mybatis.weChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.linjt.java_learning.mybatis.weChat.mapper.CommandMapper;
import top.linjt.java_learning.mybatis.weChat.pojo.CommandBean;
import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;

/**
 * 与指令表相关的操作
* Title: CommandService
* Description:
* @author XxX
* @date 2018年2月7日下午3:02:56
* @since 
*/
@Service
public class CommandService {

	@Autowired
	private CommandMapper commandMapper;
	
	public List<CommandBean> query(String command, String description){
		
		List<CommandBean> bean = commandMapper.queryCommandList(command!=null?command.trim():null, description!=null?description.trim():null);
		return bean;
		
	}
}
