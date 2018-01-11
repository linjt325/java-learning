package top.linjt.java_learning.servletRefresh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -146360052568888755L;

	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
		String info ="ip: "+request.getRemoteHost()+" \nport: "+request.getRemotePort()+" \nuser: "+request.getRemoteUser()
					+"\nURI: "+request.getRequestURI();
		System.out.println(info);
		// 设置刷新自动加载的事件间隔为 5 秒
	      response.setIntHeader("Refresh", 5);
	      
	      // 设置响应内容类型
	      response.setContentType("text/html;charset=utf-8");
	  
	      // 获取当前的时间
	      Calendar calendar = new GregorianCalendar();
	      String am_pm;
	      int hour = calendar.get(Calendar.HOUR);
	      int minute = calendar.get(Calendar.MINUTE);
	      int second = calendar.get(Calendar.SECOND);
	      if(calendar.get(Calendar.AM_PM) == 0)
	        am_pm = "AM";
	      else
	        am_pm = "PM";
	  
	      String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
	     
	      PrintWriter out = response.getWriter();
	      String title = "使用 Servlet 自动刷新页面";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	        "<html>\n" +
	        "<head><title>" + title + "</title></head>\n"+
	        "<body bgcolor=\"#f0f0f0\">\n" +
	        "<h1 align=\"center\">" + title + "</h1>\n" +
	        "<p>当前时间是：" + CT + "</p>\n"
	        );
	      out.println(info);
	}
}
