package top.linjt.java_learning.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import java.util.Map;

import top.linjt.java_learning.jdbc.DBUtil;
import top.linjt.java_learning.jdbc.pojo.Goddess;

public class GoddessDao {
	

	public void addGoddess(Goddess goddess) throws SQLException{
		Connection conn = DBUtil.getConnection();
		
		String sql= "insert into imooc_goddess ("
				+ "user_name, sex, age, birthday, email, mobile, create_user, create_date, update_user, update_date, isdel)"
				+ "values ("
				+ "?,?,?,?,?,?,?,current_date(),?,current_date(),?"
				+ ")";
			
		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setString(1, goddess.getUser_name());
		statement.setInt(2, goddess.getSex());
		statement.setInt(3, goddess.getAge());
		statement.setDate(4, new Date(goddess.getBirthday().getTime()));
		statement.setString(5, goddess.getEmail());
		statement.setString(6, goddess.getMobile());
		statement.setString(7, goddess.getCreate_user());
		statement.setString(8, goddess.getUpdate_user());
		statement.setInt(9, 0);
		statement.execute();
		
		
		
	}
	
	public void delGoddess(int id) throws SQLException{
		
		Connection conn = DBUtil.getConnection();
		String sql = "delete from imooc_goddess where id = ? ";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}
	
	public void updateGoddess(Goddess goddess) throws SQLException{
		Connection conn = DBUtil.getConnection();

		String sql= "update  imooc_goddess "
				+ "set "
				+ "user_name = ?, sex = ?, age = ?, birthday = ?, email = ?, mobile = ?, update_user = ?, update_date = ?, isdel = ? "
				+ " where id = ?";
			
		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setString(1, goddess.getUser_name());
		statement.setInt(2, goddess.getSex());
		statement.setInt(3, goddess.getAge());
		statement.setDate(4, new Date(goddess.getBirthday().getTime()));
		statement.setString(5, goddess.getEmail());
		statement.setString(6, goddess.getMobile());
		statement.setString(7, goddess.getCreate_user());
		statement.setString(8, goddess.getUpdate_user());
		statement.setInt(9, 0);
		statement.setInt(10, goddess.getId());
		statement.execute();
	}
	
	
	/**
	 * 查询单个数据
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Goddess get(int id) throws SQLException{
		
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement statement = conn.prepareStatement("select id, user_name ,age , sex, birthday , email , mobile , "
				+ "create_user , create_date , update_user , update_date ,isdel  from imooc_goddess where id = ? ");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		if(rs.next()){
			return  loadData(rs);
		}
		return null;
	}
	
	/**
	 * 查询所有数据
	 * @return
	 * @throws SQLException
	 */
	public List<Goddess> query(List<Map<String, Object>> param) throws SQLException{
		List<Goddess> resultList = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		StringBuilder sb =new StringBuilder("select * from imooc_goddess where 1=1 ");
		if(param!=null&&param.size()>0){
			for(int i = 0; i<param.size(); i++){
				Map<String,Object> map =param.get(i);
				sb.append("AND " + map.get("name") + " " + map.get("relation") + " " + map.get("value"));
			}
		}
		PreparedStatement statement = conn.prepareStatement(sb.toString());
		
		ResultSet rs = statement.executeQuery();
		System.out.println("query: "+sb.toString());
		while(rs.next()){
			Goddess goddess =loadData(rs);
			resultList.add(goddess);
		}
		
		return resultList;
		
	}
	/**
	 * 调用无参存储过程
	 * @throws SQLException
	 */
	public List<Goddess> queryByProcedure_nofilter() throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_nofilter()");
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		ResultSet rs = cs.getResultSet();
		List<Goddess> list = new ArrayList<>();
		while(rs.next()){
			Goddess g =	loadData(rs);
			list.add(g);
		}
		return list;
	}
	
	/**
	 * 调用带参数存储过程
	 * @throws SQLException
	 */
	public List<Goddess> queryByProcedure_withfilter(String sp_name) throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_filter(?)");
		cs.setString(1, sp_name);
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		ResultSet rs = cs.getResultSet();
		List<Goddess> list = new ArrayList<>();
		while(rs.next()){
			Goddess g =	loadData(rs);
			list.add(g);
		}
		return list;
	}
	
	/**
	 * 调用出参的存储过程
	 * @return
	 * @throws SQLException
	 */
	public int queryByProcedure_count() throws SQLException{
//		1. 获取连接
		Connection conn = DBUtil.getConnection();
//		2. 获得 CallableStatement
		CallableStatement cs = conn.prepareCall("call sp_select_count(?)");
		//注册出参的类型
		cs.registerOutParameter(1, Types.INTEGER);
//		3.执行存储过程
		cs.execute();
//		4. 处理返回的结果:a.结果集;b.出参
		//采用出参的方式获取结果
		return cs.getInt(1);
	}
	
	private Goddess loadData(ResultSet rs) throws SQLException{
		Goddess goddess = new Goddess();
		goddess.setId(rs.getInt("id"));
		goddess.setAge(rs.getInt("age"));
		goddess.setSex(rs.getInt("sex"));
		goddess.setUser_name(rs.getString("user_name"));
		goddess.setBirthday(rs.getDate("birthday"));
		goddess.setEmail(rs.getString("email"));
		goddess.setMobile(rs.getString("mobile"));
		goddess.setCreate_user(rs.getString("create_user"));
		goddess.setCreate_date(rs.getDate("create_date"));
		goddess.setUpdate_user(rs.getString("update_user"));
		goddess.setUpdate_date(rs.getDate("update_date"));
		goddess.setIsdel(rs.getInt("isdel"));
		return goddess;
	}
}
