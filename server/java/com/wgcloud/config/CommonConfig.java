package com.wgcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "base")
public class CommonConfig {



	//admin管理员密码
	private  String admindPwd= "111111";


	public String getAdmindPwd() {
		return admindPwd;
	}

	public void setAdmindPwd(String admindPwd) {
		this.admindPwd = admindPwd;
	}

}
