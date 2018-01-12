package top.linjt.java_learning.jdbc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import top.linjt.java_learning.jdbc.dao.GoddessDao;
import top.linjt.java_learning.jdbc.pojo.Goddess;

public class GoddessController {
	
	private GoddessDao dao;
	



	public static void main(String[] args) throws SQLException {
		
		GoddessController controller =new GoddessController();
		
//		controller.del();
//		controller.get();
//		controller.update();
//		controller.add();
		controller.query();
	}
	
	
	public GoddessController() {
		super();
		this.dao = new GoddessDao();
	}
	 
	
	public void add(Goddess goddess) throws SQLException{
//		Goddess goddess =new Goddess();
//		goddess.setAge(18);
//		goddess.setUser_name("阿花");
//		goddess.setSex(1);
//		goddess.setBirthday(Timestamp.valueOf("1993-03-03 00:00:00"));
//		goddess.setCreate_user("admin");
		dao.addGoddess(goddess);
	}
	
	public void del(int id) throws SQLException{
		dao.delGoddess(id);
	}
	
	public void update(Goddess g) throws SQLException{
//		Goddess g = dao.get(3);
//		g.setAge(55);
//		g.setEmail("097097@qq.com");
		dao.updateGoddess(g);
	}
	
	public Goddess get(int id ) throws SQLException{
		Goddess g = dao.get(id);
		return g;
	}
	
	/**
	 * 全部查询
	 * @throws SQLException 
	 */
	public List<Goddess> query() throws SQLException{
		List<Goddess> list = dao.query(null);
		return list;
	}
	/**
	 *  条件查询
	 * @throws SQLException
	 */
	public List<Goddess> query(List<Map<String , Object>> params) throws SQLException{
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "user_name");
//		map.put("relation", "like ");
//		map.put("value", "'%花%'");
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("name", "mobile");
//		map1.put("relation", "like ");
//		map1.put("value", "'%321%'");
//		
//		List<Map<String , Object>> params =new ArrayList<Map<String,Object>>();
//		params.add(map);
//		params.add(map1);
		List<Goddess> list = dao.query(params);
		if(list!=null){
			for (Goddess goddess : list){
				System.out.println(goddess);
			}
		}
		return list;
	}
}
