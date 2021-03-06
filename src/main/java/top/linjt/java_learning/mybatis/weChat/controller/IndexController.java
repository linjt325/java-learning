package top.linjt.java_learning.mybatis.weChat.controller;


import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.linjt.java_learning.mybatis.weChat.pojo.MessageBean;
import top.linjt.java_learning.mybatis.weChat.service.MessageService;

/**
 * mvc页面
* Title: IndexController
* Description:
* @author XxX
* @date 2018年2月1日上午11:45:26
* @since 
*/
@Controller(value="weChatController")
@RequestMapping("weChat")
public class IndexController {

    private MessageService messageService;


    @Autowired
    public IndexController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("index")
	@ResponseBody
	public ModelAndView index(String command , String description) {
		
		ModelAndView model = new ModelAndView("weChat/server/settings");
//		try {
//			model.addObject("message", messageService.query(command, description))
//				.addObject("command", command)
//				.addObject("description", description);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return model;
	}
	
	@RequestMapping("newMessage")
	@ResponseBody
	public ModelAndView newMessage(Integer id) {
		
		ModelAndView model = new ModelAndView("weChat/server/newMessage");
		if(id!=null){
			model.addObject("message", messageService.get(id));
		}
		return model;
	}
	
	@RequestMapping("chat")
	@ResponseBody
	public ModelAndView chat() {
		
		ModelAndView model = new ModelAndView("weChat/client/chat");
		return model;
	}

}
