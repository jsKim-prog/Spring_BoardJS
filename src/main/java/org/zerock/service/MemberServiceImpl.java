package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	// MemberService 구현 클래스
	private MemberMapper mapp;

	@Override
	public boolean register(MemberVO member) {
		// 객체를 받아 결과를 int로 받고 성공여부를 boolean으로 리턴
		int result = mapp.insert(member);
		log.info("MemberServiceImpl.register() 메서드 실행");
		log.info("register 결과 : " + result);
		return result == 1;
	}
	
	@Override
	public MemberVO login(MemberVO member) {
		// 로그인 정보 받아 객체 리턴
		log.info("MemberServiceImpl.login() 메서드 실행");
		return mapp.selectMem(member);
	}
	

	@Override
	public MemberVO get(String ninckname) {
		// ninckname을 받아 객체로 리턴
		log.info("MemberServiceImpl.get() 메서드 실행");
		return mapp.readMem(ninckname);
	}

	@Override
	public int dupleCheck(String ninckname) {
		// ninckname을 받아 db상의 개수(int) 리턴
		log.info("MemberServiceImpl.dupleCheck() 메서드 실행");
		int checkRst = mapp.readCheck(ninckname);
		return checkRst;
	}

	@Override
	public boolean modify(MemberVO member) {
		// 객체를 받아 결과 int로 성공여부(boolean) 리턴
		log.info("MemberServiceImpl.modify() 메서드 실행");
		return mapp.update(member) == 1;
	}

	@Override
	public boolean remove(MemberVO member) {
		// 객체를 받아 결과 int로 성공여부(boolean) 리턴
		log.info("MemberServiceImpl.remove() 메서드 실행");
		return mapp.delete(member) == 1;
	}


}
