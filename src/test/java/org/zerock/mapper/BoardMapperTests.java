package org.zerock.mapper;

import java.util.List;

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
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board->log.info(board));
	}
	
	//create1 : 객체를 받아 db에 입력(DB에서 번호 생성)
	@Test
	public void testInsert() {
		 BoardVO vo = new BoardVO();
		 vo.setTitle("mapper에서 만든 제목");
		 vo.setContent("mapper에서 만든 내용");
		 vo.setWriter("mapper"); //객체 삽입 완료
		 
		 mapper.insert(vo);
		 
		 log.info("입력된 객체 : "+vo);	
		 //입력된 객체 : BoardVO(bno=null, title=mapper에서 만든 제목, content=mapper에서 만든 내용, writer=mapper, regdate=null, updateDate=null)
		 //DB에서 입력되는 내용은 콘솔에서는 null로 뜬다.
	}
	
	//create2 : 객체를 받아 db에 입력(번호 생성 후 DB 입력)
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("SelectKey 사용 제목");
		vo.setContent("SelectKey 사용 내용");
		vo.setWriter("SelectKey");
		
		mapper.insertSelectKey(vo);
		
		log.info("입력된 객체 : "+vo);
		//BoardVO(bno=8, title=SelectKey 사용 제목, content=SelectKey 사용 내용, writer=SelectKey, regdate=null, updateDate=null)
		//selectkey로 생성된 bno 출력
	}
	
	//read_one : bno를 받아 객체로 출력한다.
	@Test
	public void testRead() {
		BoardVO readboard = mapper.read(3L);
		log.info("3번 객체 출력:"+readboard);
	}
	
	//update : 객체를 받아 수정된 내용으로 update 하고 결과를 int로 받는다.(bno 필요)
	@Test
	public void testUpdate() {
		BoardVO upBoard = new BoardVO();
		upBoard.setBno(2L); //내용을 변경할 번호
		upBoard.setTitle("mapper에서 변경한 제목");
		upBoard.setContent("mapper에서 변경한 내용");
		upBoard.setWriter("차승원");
		
		int cnt = mapper.update(upBoard); //결과를 int로 받는다.
		log.info("변경개수 : " + cnt);
		log.info("변경내용 : " + upBoard);
	}
	
	//delete : bno를 받아 객체를 삭제하고, 결과를 int로 알려준다.
	@Test
	public void testDelete() {
		log.info("삭제한 개수 : "+ mapper.delete(5L));
	}
	
	//페이징 처리 테스트
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria(2,10);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}

}
