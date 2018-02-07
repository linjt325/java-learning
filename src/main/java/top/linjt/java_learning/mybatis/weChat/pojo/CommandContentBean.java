package top.linjt.java_learning.mybatis.weChat.pojo;


/**
 * 与commandConten 表对应的实体类
* Title: CommandContenBean
* Description:
* @author XxX
* @date 2018年1月31日下午5:26:32
* @since 
*/
public class CommandContentBean {
	private Integer id;

	private Integer commandId;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommandId() {
		return commandId;
	}

	public void setCommandId(Integer commandId) {
		this.commandId = commandId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommandContentBean [id=" + id + ", commandId=" + commandId
				+ ", content=" + content + "]";
	}





	
}
