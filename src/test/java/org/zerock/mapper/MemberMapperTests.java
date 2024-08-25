package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	/*
	 * @Test public void testGetList() {
	 * mapper.getList().forEach(board->log.info(board)); }
	 */

	// create1 : 객체를 받아 db에 입력(DB에서 번호 생성)
	@Test
	public void testInsert() {
		MemberVO vo = new MemberVO();
		vo.setNinckname("ccc");
		vo.setEmail("ccc@ccc");
		vo.setPhone("010-123-1234");
		vo.setBirth("1999/12/31");
		vo.setPw("ccc");

		int rst = mapper.insert(vo);

		log.info("입력된 객체 : " + vo);
		log.info("입력객체 수 : " + rst);
		// 입력된 객체 : MemberVO(ninckname=ccc, pw=ccc, email=ccc@ccc, phone=010-123-1234,
		// birth=1999/12/31, regdate=null)
		// 입력객체 수 : 1
	}

	// read_one1: 로그인(email, pw->객체)
	@Test
	public void testSelectMem() {
		MemberVO vo = new MemberVO();
		vo.setPw("kkk");
		vo.setEmail("kkk@test.com");
		vo = mapper.selectMem(vo);

		log.info("로그인 회원 닉네임 : " + vo.getNinckname());
		// 로그인 회원 닉네임 : kkk
	}

	// read_one2: 회원정보 가져오기(nickname->객체)
	@Test
	public void testReadMem() {
		MemberVO vo = mapper.readMem("aaa");
		log.info("가져온 회원정보:" + vo);
		// 가져온 회원정보:MemberVO(ninckname=aaa, pw=aaa, email=aaa@test.com,
		// phone=010-111-1111, birth=1900-01-01 00:00:00, regdate=2024-08-22)
	}

	// read_one3: 회원유무, 닉네임 중복 검증(nickname->int)
	@Test
	public void testDupleCheck() {
		int result = mapper.readCheck("aaa");
		int result2 = mapper.readCheck("ksf");
		log.info("aaa 검색결과 : " + result); // aaa 검색결과 : 1
		log.info("ksf 검색결과 : " + result2); // ksf 검색결과 : 0
	}

	// update : 회원정보 변경(객체-> int)
	@Test
	public void testUpdate() {
		MemberVO upMem = mapper.readMem("ccc");
		log.info("가져온 객체 : " + upMem.getPw());
		upMem.setEmail("ccc@naver.com");

		int result = mapper.update(upMem);

		log.info("변경개수 : " + result); // 변경개수 : 1
		log.info("변경객체 : " + upMem);
		// 변경객체 : MemberVO(ninckname=ccc, pw=ccc, email=ccc@naver.com,
		// phone=010-123-1234, birth=1999-12-31 00:00:00, regdate=2024-08-22)

	}

	// delete : 회원탈퇴(객체-> int)
	@Test
	public void testDelete() {
		MemberVO delMem = mapper.readMem("ccc");
		int rst = mapper.delete(delMem);
		log.info("삭제 개수:" + rst); // - 삭제 개수:1
	}

}
