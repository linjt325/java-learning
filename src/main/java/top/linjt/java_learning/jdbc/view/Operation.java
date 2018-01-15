package top.linjt.java_learning.jdbc.view;

public enum Operation {

	MAIN("M",0),QUERY("Q",1),GET("G",2),ADD("A",3),UPDATE("U",4),DELETE("D",5),SEARCH("S",6),EXIT("E",7),BREAK("B",8);
	
	private String shortName;
	
	private int order;

	private Operation(String shortName, int order) {
		this.shortName = shortName;
		this.order = order;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
	
}
