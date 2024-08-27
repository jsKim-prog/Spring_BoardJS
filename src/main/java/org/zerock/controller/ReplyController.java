package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController // --1.
@RequestMapping("/replies/") // --2~6.
@Log4j2
@AllArgsConstructor // --0.(또는 @Setter 주입사용)
public class ReplyController { // 댓글관련 url 관리
	// 1. REST 방식(pk(rno) 기준으로 작성)
	// 7. REST 방식은 직접 예외처리 필수 : 상태코드 삽입을 위해 ResponseEntity로 리턴
	// 8. Test : Postman 이용

	private ReplyService rservice; // 댓글 service 객체 주입--0.

	// 2. C : 등록 : /replies/new : post
	@PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE) 
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		log.info("등록할 객체 : " + reply);
		int count = rservice.register(reply); //입력한 결과값
		log.info("등록된 댓글수 : " + count);
		return (count==1)?
				new ResponseEntity<>("success", HttpStatus.OK): 
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		//200 OK | 500 Internal Server Error.
		//{"bno":3, "reply":"postMan 작성댓글", "replyer":"kjs"} -> success 200 OK
	}
	
	// 3. R : 조회 : /replies/{rno} : get
	//http://localhost:80/replies/4 -> xml
	//http://localhost:80/replies/4.json -> json	
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("가져올 rno : " + rno);
		return new ResponseEntity<>(rservice.get(rno), HttpStatus.OK);
		//가져온 객체(ReplyVO)와 status를 ResponseEntity에 넣어 return
		//{"rno":4,"bno":8,"reply":"댓글8","replyer":"kjs","replyDate":"2024-08-27 15:42:50","updateDate":"2024-08-27 15:42:50"}
	}
	
	
	// 4. U : 수정 : /replies/{rno} : put or patch--@RequestMapping의 배열로 이용
	//http://localhost:80/replies/4 ->String으로 결과만 본다
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply, @PathVariable("rno") Long rno){
		//(이미 폼(form)에 있는 값, 수정할 번호)
		reply.setRno(rno); //현재 form값(객체)에 url의 rno를 세팅 -> db 검색위한 필수!
		log.info("수정할 객체 : " +reply); //수정할 객체 : ReplyVO(rno=4, bno=11, reply=postMan 수정댓글, replyer=kjs, replyDate=null, updateDate=null)
		return (rservice.modify(reply)==1)?
				new ResponseEntity<>("sucess", HttpStatus.OK) : 
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		//put : {"bno":11, "reply":"postMan 수정댓글", "replyer":"kjs"} -> sucess
		//patch : {"bno":11, "reply":"postMan 수정2", "replyer":"kjs"} -> sucess
	}
	
		
	// 5. D : 삭제 : /replies/{rno} : delete
	//http://localhost:80/replies/5  ->String으로 결과만 본다
	@DeleteMapping(value = "/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("삭제할 댓글번호 : " +rno); //삭제할 댓글번호 : 5
		return (rservice.remove(rno)==1)?
				new ResponseEntity<>("sucess", HttpStatus.OK):
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	//sucess
	}
	
	// 6. 전체보기(List) : /replies/pages/{bno}/{page} : get
	//http://localhost:80/replies/pages/10/1
	@GetMapping(value = "/pages/{bno}/{page}")
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
		//(댓글의 현재페이지, 게시물 번호)
		log.info("게시물번호 : " +bno);
		log.info("댓글 페이지 : " +page);
		Criteria cri = new Criteria(page, 10);
		log.info("cri : " +cri);
		return new ResponseEntity<List<ReplyVO>>(rservice.getList(cri, bno), HttpStatus.OK);		
		//<List><item><rno>18</rno><bno>10</bno><reply>댓글10</reply><replyer>kjs</replyer><replyDate>2024-08-27 15:43:23</replyDate><updateDate>2024-08-27 15:43:23</updateDate></item><item><rno>13</rno><bno>10</bno><reply>댓글10</reply><replyer>kjs</replyer><replyDate>2024-08-27 15:43:07</replyDate><updateDate>2024-08-27 15:43:07</updateDate></item><item><rno>8</rno><bno>10</bno><reply>댓글10</reply><replyer>kjs</replyer><replyDate>2024-08-27 15:42:58</replyDate><updateDate>2024-08-27 15:42:58</updateDate></item><item><rno>2</rno><bno>10</bno><reply>댓글10</reply><replyer>kjs</replyer><replyDate>2024-08-27 15:42:48</replyDate><updateDate>2024-08-27 15:42:48</updateDate></item></List>
	}

}
