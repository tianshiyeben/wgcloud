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
	private  String wgToken = "";
	private  Double memWarnVal = 98d;
	private  Double cpuWarnVal = 98d;


	public String getAdmindPwd() {
		return admindPwd;
	}

	public void setAdmindPwd(String admindPwd) {
		this.admindPwd = admindPwd;
	}

	public String getWgToken() {
		return wgToken;
	}

	public void setWgToken(String wgToken) {
		this.wgToken = wgToken;
	}

	public Double getMemWarnVal() {
		if(memWarnVal==null){
			return 98d;
		}
		return memWarnVal;
	}

	public void setMemWarnVal(Double memWarnVal) {
		this.memWarnVal = memWarnVal;
	}

	public Double getCpuWarnVal() {
		if(cpuWarnVal==null){
			return 98d;
		}
		return cpuWarnVal;
	}

	public void setCpuWarnVal(Double cpuWarnVal) {
		this.cpuWarnVal = cpuWarnVal;
	}
}
