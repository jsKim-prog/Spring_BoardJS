package org.zerock.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVO {
	
	private String ninckname;
	private String pw;
	private String email;
	private String phone;
	private String birth;
	private Date regdate;
}
