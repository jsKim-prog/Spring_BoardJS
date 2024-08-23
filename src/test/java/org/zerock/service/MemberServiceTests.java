package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.annotations.Mapper;
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
public class MemberServiceTests {
	@Setter(onMethod_ = @Autowired)
	private MemberService service; // setService(BoardService)

	// 객체생성 유무 테스트
	@Test
	public void testMemberService() {
		log.info(service); // interface를 필드로 생성했는데 서비스 객체를 실행하면 ..Impl이 붙은 class가 실행된다.
		assertNotNull(service);
		// org.zerock.service.MemberServiceImpl@1fdca564
	}

	// register(MemberVO member):회원등록(객체->int->boolean)
	@Test
	public void testRegister() {
		MemberVO newvo = new MemberVO();
		newvo.setNinckname("ccc");
		newvo.setEmail("ccc@ccc");
		newvo.setPhone("010-123-1234");
		newvo.setBirth("1999/12/31");
		newvo.setPw("ccc");

		boolean result = service.register(newvo);
		log.info("ccc등록결과 : " + result); // ccc등록결과 : true
	}

	// login(MemberVO member); read_one1: 로그인(email, pw->객체)
	@Test
	public void testLogin() {
		MemberVO logMem = new MemberVO();
		logMem.setEmail("aaa@test.com");
		logMem.setPw("aaa");
		logMem = service.login(logMem);
		log.info("로그인 계정 : " + logMem);
		// 로그인 계정 : MemberVO(ninckname=aaa, pw=aaa, email=aaa@test.com,
		// phone=010-111-1111, birth=1900-01-01 00:00:00, regdate=2024-08-22)
	}

	// get(String ninckname);회원정보 가져오기(nickname->객체)
	@Test
	public void testGet() {
		MemberVO vo = service.get("bbb");
		log.info(vo);
		// MemberVO(ninckname=bbb, pw=bbb, email=bbb@test.com, phone=010-111-1111,
		// birth=2020-01-01 00:00:00, regdate=2024-08-22)
	}

	// dupleCheck(String ninckname):회원유무, 닉네임 중복 검증(nickname->int)
	@Test
	public void testDupleCheck() {
		int rst = service.dupleCheck("bbb");
		log.info("bbb 닉네임 개수 : " + rst); //bbb 닉네임 개수 : 1
	}

	// modify(BoardVO board) :회원정보 변경(객체->int->boolean)
	@Test
	public void testModify() {
		MemberVO vo = service.get("ccc");
		if (vo == null) {
			log.info("찾는 객체가 없습니다.");
		}
		vo.setPw("111");
		log.info("service에서 수정한결과 : " + service.modify(vo));
	}

	// remove(MemberVO member) :회원탈퇴(객체->int->boolean)
	@Test
	public void testRemove() {
		MemberVO vo = service.get("ccc");
		log.info("삭제 결과:" + service.remove(vo)); // - 삭제 결과:true
	}

}
