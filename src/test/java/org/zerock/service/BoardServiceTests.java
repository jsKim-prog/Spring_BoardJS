package org.zerock.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private BoardService service; //setService(BoardService)
	
	//객체생성 유무 테스트
	@Test
	public void testBoardService() {
		log.info(service); // interface를 필드로 생성했는데 서비스 객체를 실행하면 ..Impl이 붙은 class가 실행된다.
		assertNotNull(service);
		//org.zerock.service.BoardServiceImpl@432f521f
	}
	
	//register(BoardVO board):객체를 받아 db에 입력(번호 생성 후 DB 입력)
	@Test
	public void testRegister() {
		BoardVO newvo = new BoardVO();
		newvo.setTitle("service 등록 제목");
		newvo.setContent("service 등록 내용");
		newvo.setWriter("service");
		
		service.register(newvo);
		log.info("등록된 게시물 번호 : "+newvo.getBno());
	}
	
	//getList()  : 전체 리스트를 받아 List로 리턴+페이징 처리 수정
	@Test
	public void testGetList() {
		//service.getList().forEach(board->log.info(board));
		service.getList(new Criteria(1, 5)).forEach(board->log.info(board));
		//5페이지씩 잘랐을 때 두 번째 페이지
	}
	
	//get(Long bno) :bno를 받아 객체로 출력한다.
	@Test
	public void testGet() {
		BoardVO vo = service.get(8L);
		log.info(vo);
	}
	
	//modify(BoardVO board) :객체를 받아 수정된 내용으로 update 하고 db에서 int로 받은 결과를 boolean으로 리턴
	@Test
	public void testModify() {
		BoardVO vo = service.get(1L);
		if(vo==null) {
			log.info("찾는 객체가 없습니다.");
		}
		vo.setTitle("service에서 수정제목");
		log.info("service에서 수정한결과 : " + service.modify(vo));
	}
	
	//remove(Long bno) :bno를 받아 객체를 삭제하고,  db에서 int로 받은 결과를 boolean으로 리턴
	@Test
	public void testRemove() {
		log.info("service에서 삭제한 결과 : " + service.remove(6L));
	}

}
