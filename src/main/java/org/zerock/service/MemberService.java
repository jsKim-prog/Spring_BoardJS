package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	
	//create : 회원등록(객체->int->boolean)
	public boolean register(MemberVO member);
	//read_one1: 로그인(email, pw->객체)
	public MemberVO login(MemberVO member);
	//read_one2: 회원정보 가져오기(nickname->객체)
	public MemberVO get(String ninckname);	
	//read_one3: 회원유무, 닉네임 중복 검증(nickname->int)
	public int dupleCheck(String ninckname);	
	//update : 회원정보 변경(객체->int->boolean)
	public boolean modify(MemberVO member);	
	//delete : 회원탈퇴(객체->int->boolean)
	public boolean remove(MemberVO member);	
}
