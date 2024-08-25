package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
	private int tno; //티켓 넘버
	private String owner; //티켓 주인
	private String grade; //티켓 랭크(S, R, ..)

}
