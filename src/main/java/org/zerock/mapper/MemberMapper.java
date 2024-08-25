package org.zerock.mapper;

import org.zerock.domain.MemberVO;

public interface MemberMapper {
	//mabatis 활용 - 추상메서드 + xml로 sql 처리하기 위한 interface
	
	//추상메서드
	//create : 회원등록(객체-> int)
	public int insert(MemberVO member);
	//read_one1: 로그인(email, pw->객체) *매개변수는 최대 하나이므로 객체로 보낸다.
	public MemberVO selectMem(MemberVO member);
	//read_one2: 회원정보 가져오기(nickname->객체)
	public MemberVO readMem(String ninckname);
	//read_one3: 회원유무, 닉네임 중복 검증(nickname->int)
	public int readCheck(String ninckname); 
	//update : 회원정보 변경(객체-> int)
	public int update(MemberVO member);
	//delete : 회원탈퇴(객체-> int)
	public int delete(MemberVO member);
}
