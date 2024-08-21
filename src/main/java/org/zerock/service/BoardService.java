package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

//비즈니스 로직을 처리하는 용도, 여러 객체를 믹스하여 구현
public interface BoardService {
	//게시판의 C, R(ALL, ONE), U, D를 기본으로 설정
	//mapper는 DB용어를 많이 사용하고, service는 실제용어를 많이 사용함(클라이언트의 요구 반영)
	
	//create : 게시글 등록
	public void register(BoardVO board);
	//read_all : 게시글의 전체 내용을 리스트로 출력
	public List<BoardVO> getList();
	//read_one : 한 개의 게시글 가져오기
	public BoardVO get(Long bno);
	//update : 게시글 수정
	public boolean modify(BoardVO board);
	//delete : 한 개의 게시글 삭제
	public boolean remove(Long bno);

}
