package top.linjt.java_learning.mybatis.weChat.pojo;


/**
 * 与message 表对应的实体类
* Title: MessageBean
* Description:
* @author XxX
* @date 2018年1月31日下午5:26:32
* @since 
*/
public class MessageBean {
	private int id;

	private String command;

	private String description;

	private String content;

	
	
	public MessageBean() {
		super();
	}

	public MessageBean(String command, String description, String content) {
		super();
		this.command = command;
		this.description = description;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommandBean [id=" + id + ", command=" + command
				+ ", description=" + description + ", content=" + content + "]";
	}

	
}
