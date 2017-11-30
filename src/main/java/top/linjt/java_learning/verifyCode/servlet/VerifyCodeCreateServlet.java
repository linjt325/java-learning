package top.linjt.java_learning.verifyCode.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.linjt.java_learning_util.util.verifyCode.VerifyCodeUtils;


public class VerifyCodeCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyCodeCreateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		VerifyCodeUtils verifyCodeUtils = new VerifyCodeUtils(110, 40);
		String path=request.getServletContext().getRealPath("/");
		File dir = new File(path+File.separator+"Images/verifyCode");
        String verifyCode = verifyCodeUtils.generateVerifyCode(4);
        File file = new File(dir, verifyCode + ".jpg");
        try {
			verifyCodeUtils.outputImage(file, verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        PrintWriter out = response.getWriter();
        out.write(verifyCode);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
