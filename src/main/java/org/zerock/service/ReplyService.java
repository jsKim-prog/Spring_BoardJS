package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyService { //댓글서비스용 interface : 구현클래스 ReplyServiceImpl
	
	//C : 등록-insert(in : 객체 -> out : int)
	public int register(ReplyVO reply);
	
	//R : 상세보기-read(in : rno -> out : 객체)
	public ReplyVO get(Long rno);
	
	//U : 수정 -update(in : 객체(reply+rno) -> out : int)
	public int modify(ReplyVO reply);
	
	//D : 삭제 - delete(in : rno -> out : int)
	public int remove(Long rno);
	
	//댓글 리스트 
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
