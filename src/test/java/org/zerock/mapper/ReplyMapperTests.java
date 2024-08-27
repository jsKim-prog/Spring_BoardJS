package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapp;
	
	//mapper 정상동작 테스트
	@Test
	public void testMapper() {
		log.info(mapp);
		//org.apache.ibatis.binding.MapperProxy@806996
	}
	
	//CRUD 테스트
	@Test
	public void testInsert() { //(in : 객체 -> out : int)
		ReplyVO vo = new ReplyVO();
		vo.setBno(2L);
		vo.setReply("mapper댓글");
		vo.setReplyer("mapper");
		int rst = mapp.insert(vo);
		log.info("입력된 댓글객체 : " + vo ); 
		//입력된 댓글객체 : ReplyVO(rno=null, bno=2, reply=mapper댓글, replyer=mapper, replyDate=null, updateDate=null)
		log.info("입력결과 : "+ rst);
		//입력결과 : 1
	}
	
	@Test
	public void testRead() {// (in : rno -> out : 객체)	
		log.info("가져온 댓글 : " + mapp.read(5L));
		//가져온 댓글 : ReplyVO(rno=5, bno=7, reply=댓글7, replyer=kjs, replyDate=2024-08-27 15:42:51, updateDate=2024-08-27 15:42:51)
	}
	
	@Test
	public void testUpdate() {//(in : 객체(reply+rno) -> out : int)
		ReplyVO vo = mapp.read(10L); //먼저 10번 댓글 가져오기
		vo.setReply("10번 수정댓글");
		int rstcnt = mapp.update(vo); 
		log.info("수정개수 : "+rstcnt); //수정개수 : 1
		log.info("수정결과 객체 : "+vo);
		//수정결과 객체 : ReplyVO(rno=10, bno=8, reply=10번 수정댓글, replyer=kjs, replyDate=2024-08-27 15:43:00, updateDate=2024-08-27 15:43:00)
	}
	
	@Test
	public void testDelete() {//(in : rno -> out : int)
		Long tgRno = 1L;
		int rstcnt = mapp.delete(tgRno);
		log.info("삭제개수 : "+rstcnt); //삭제개수 : 1
	}
	
	//리스트 테스트 (@Param("cri") Criteria cri, @Param("bno") Long bno) ->List<ReplyVO>
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> reList = mapp.getListWithPaging(cri, 10L);
		reList.forEach(list -> log.info(list));
		//ReplyVO(rno=18, bno=10, reply=댓글10, replyer=kjs, replyDate=2024-08-27 15:43:23, updateDate=2024-08-27 15:43:23)
		//ReplyVO(rno=13, bno=10, reply=댓글10, replyer=kjs, replyDate=2024-08-27 15:43:07, updateDate=2024-08-27 15:43:07)
		//ReplyVO(rno=8, bno=10, reply=댓글10, replyer=kjs, replyDate=2024-08-27 15:42:58, updateDate=2024-08-27 15:42:58)
		//ReplyVO(rno=2, bno=10, reply=댓글10, replyer=kjs, replyDate=2024-08-27 15:42:48, updateDate=2024-08-27 15:42:48)
	}
	

}
