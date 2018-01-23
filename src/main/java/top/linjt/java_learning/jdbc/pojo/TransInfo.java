package top.linjt.java_learning.jdbc.pojo;

import java.sql.Timestamp;

public class TransInfo {

	private int id ;
	
	private int source_id ; 

	private String source_account; 
	
	private int destination_id ; 

	private String destination_account; 

	private double amount;
	
	private Timestamp create_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSource_id() {
		return source_id;
	}

	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}

	public String getSource_account() {
		return source_account;
	}

	public void setSource_account(String source_account) {
		this.source_account = source_account;
	}

	public int getDestination_id() {
		return destination_id;
	}

	public void setDestination_id(int destination_id) {
		this.destination_id = destination_id;
	}

	public String getDestination_account() {
		return destination_account;
	}

	public void setDestination_account(String destination_account) {
		this.destination_account = destination_account;
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
		return "Trans_info [id=" + id + ", source_id=" + source_id
				+ ", source_account=" + source_account + ", destination_id="
				+ destination_id + ", destination_account="
				+ destination_account + ", amount=" + amount + ", create_time="
				+ create_time + "]";
	}
	
	
}
