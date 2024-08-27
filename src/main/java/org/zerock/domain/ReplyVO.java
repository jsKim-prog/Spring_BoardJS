package org.zerock.domain;

import lombok.Data;

@Data
public class ReplyVO { //댓글용 영속계층
	
	private Long rno; //댓글번호(pk)
	private Long bno; //보드번호(fk)
	private String reply; //댓글
	private String replyer; //작성자
	private String replyDate; //작성일
	private String updateDate; //수정일


}
