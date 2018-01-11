package top.linjt.java_learning.new_year.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("newYear")
public class IndexController {

	@RequestMapping("index")
	@ResponseBody
	public ModelAndView index(){
		ModelAndView model=new ModelAndView("newYear/newYear");
		return model;
	}
	
}
