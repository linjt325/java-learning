package top.linjt.java_learning.mybatis.weChat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import top.linjt.java_learning.mybatis.weChat.pojo.CommandBean;

public interface CommandMapper {

	
	List<CommandBean> queryCommandList(@Param("command")String command,@Param("description")String description);
}
