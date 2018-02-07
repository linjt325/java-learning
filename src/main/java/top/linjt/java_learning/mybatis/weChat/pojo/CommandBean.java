package top.linjt.java_learning.mybatis.weChat.pojo;

import java.util.List;


/**
 * 与command  表对应的实体类
* Title: CommandBean
* Description:
* @author XxX
* @date 2018年1月31日下午5:26:32
* @since 
*/
public class CommandBean {
	private Integer id;

	private String name;

	private String description;
	
	private  List<CommandContentBean> contentList ;//= new ArrayList<CommandContentBean>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CommandContentBean> getContentList() {
		return contentList;
	}

	public void setContentList(List<CommandContentBean> contentList) {
		this.contentList = contentList;
	}

	@Override
	public String toString() {
		return "CommandBean [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}




	
}
