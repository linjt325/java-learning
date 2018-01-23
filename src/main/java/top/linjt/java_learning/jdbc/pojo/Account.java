package top.linjt.java_learning.jdbc.pojo;

import java.sql.Timestamp;

public class Account {

	private int id ;
	
	private String account ; 
	
	private double amount;
	
	private Timestamp create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Account_info [id=" + id + ", account=" + account + ", amount="
				+ amount + ", create_time=" + create_time + "]";
	}
	
	
}
