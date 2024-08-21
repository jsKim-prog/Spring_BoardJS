package org.zerock.mapper;

import java.util.List;


import org.zerock.domain.BoardVO;

public interface BoardMapper {
	//mybatis를 활용하여 interface의 추상메서드 + xml로 sql 처리
	
	//Test : interface의 자체적인 추상메서드 활용 시
    //@Select("select * from tbl_board where bno > 0") // where bno>0 : bno가 pk이므로 자동 인덱싱이 되어 있어 검색이 빠르다.
	public List<BoardVO> getList(); //BoardVO 형태의 List로 리턴
	
	//create1 : 객체를 받아 db에 입력(DB에서 번호 생성)
	public void insert(BoardVO board);
	//create2 : 객체를 받아 db에 입력(번호 생성 후 DB 입력)
	public void insertSelectKey(BoardVO board);
	
	//read_all : 전체 리스트를 받아 읽어온다. ==test와 동일
	
	//read_one : bno를 받아 객체로 출력한다.
	public BoardVO read(Long bno);
	
	//update : 객체를 받아 수정된 내용으로 update 하고 결과를 int로 받는다.
	public int update(BoardVO board);
	
	//delete : bno를 받아 객체를 삭제하고, 결과를 int로 알려준다.
	public int delete(Long bno);

}
