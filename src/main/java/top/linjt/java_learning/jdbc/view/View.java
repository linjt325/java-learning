package top.linjt.java_learning.jdbc.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import top.linjt.java_learning.jdbc.controller.GoddessController;
import top.linjt.java_learning.jdbc.pojo.Goddess;

public class View {

	private static final String CONTEXT= "Welcome!\r\n"
			+ "[Main/M]:主菜单 \r\n"
			+ "[Query/Q]: 查看全部信息\r\n"
			+ "[Get/G]:查看某个信息\r\n"
			+ "[Add/A] :添加\r\n"
			+ "[Update/U]: 更新信息\r\n"
			+ "[Delete/D]:删除\r\n"
			+ "[Search/S]:查询\r\n"
			+ "[Exit/E]:退出\r\n"
			+ "[Break/B]:退出当前功能返回主菜单\r\n";
	
	
	
	
	public static void main(String[] args) {
		GoddessController controller = new GoddessController();
		Goddess g = new Goddess();
		
		System.out.println(CONTEXT);
		
		
		Scanner scan = new Scanner(System.in);
//		标记
		Operation previous = null;
		int step = 0;
		while(true){
			String input = scan.next();
			System.out.println("您的输入为: "+ input);
		
			if(null==previous&&(input.toUpperCase().equals(Operation.EXIT.toString())||input.toUpperCase().equals(Operation.EXIT.getShortName()))){
				System.out.println("退出!");
				break;
			}else if(input.toUpperCase().equals(Operation.ADD.toString())
					||input.toUpperCase().equals(Operation.ADD.getShortName())
					||Operation.ADD.equals(previous)){
				previous = Operation.ADD;
				if(step==0){
					System.out.println("请输入姓名:");
				}else if (step==1){
					g.setUser_name(input);
					System.out.println("请输入年龄:");
				}else if (step==2){
					g.setAge(Integer.valueOf(input));
					System.out.println("请输入生日:(日期格式为yyyy-MM-dd)");
				}else if (step==3){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date birth = sdf.parse(input);
						g.setBirthday(birth);
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("日期格式不正确! 请重新输入!");
						step=2;
					}
					System.out.println("请输入邮箱");
				}else if (step==4){
					g.setEmail(input);
					System.out.println("请输入手机号");
				}else if (step==5){
					g.setMobile(input);
					try {
						controller.add(g);
						System.out.println("新增成功!");
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("新增失败!");
					}
					previous = null;
					step = 0;
				}
				if(Operation.ADD.equals(previous)){
					step++;
				}
			}else if(null==previous&&(input.toUpperCase().equals(Operation.QUERY.toString())||input.toUpperCase().equals(Operation.QUERY.getShortName()))){
				List<Goddess> list = null;
				try {
					list = controller.query();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(list!=null && list.size()>0){
					for(Goddess goddess : list){
						System.out.println(goddess.toString());
					}
				}else{
					System.out.println("没有查询到用户信息!");
				}
			}
			
			
		}
	}
}
