package com.gugu.models.admin;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <br>
 * <b>description：</b>Member, 用户信息表 Model实体类<br>
 * <b>author：</b>Daniel<br>
 * <b>datetime：</b> 2015-06-25 19:34:00 <br>
 * <b>copyright：<b>copyright(C) 2015, gugu151.com<br>
 */
public class Member implements Serializable {

	private static final long serialVersionUID = -2015062519340031685L;
	
	
		private java.lang.Integer id;//   id 主键标识	private java.lang.String username;//   用户名称	private java.lang.String userpassword;//   用户密码	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getUsername() {	    return this.username;	}	public void setUsername(java.lang.String username) {	    this.username=username;	}	public java.lang.String getUserpassword() {	    return this.userpassword;	}	public void setUserpassword(java.lang.String userpassword) {	    this.userpassword=userpassword;	}
}




