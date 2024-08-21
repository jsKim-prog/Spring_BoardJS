package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service //Spring의 서비스를 담당한다라는 코드(비즈니스 로직 영역을 담당)
@Log4j2
@AllArgsConstructor //모든 파라미터를 이용하는 생성자 만듦 -> BoardServiceImpl(BoardMapper)
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper mapper; 

	@Override
	public void register(BoardVO board) {
		// 객체를 받아 db에 입력(번호 생성 후 DB 입력)
		log.info("BoardServiceImpl.register() 메서드 실행");
		mapper.insertSelectKey(board);		
	}

	@Override
	public List<BoardVO> getList() {
		// 전체 리스트를 받아 List로 리턴
		log.info("BoardServiceImpl.getList() 메서드 실행");
		return mapper.getList();
	}

	@Override
	public BoardVO get(Long bno) {
		// bno를 받아 객체로 출력한다.
		log.info("BoardServiceImpl.get() 메서드 실행");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		// 객체를 받아 수정된 내용으로 update 하고 db에서 int로 받은 결과를 boolean으로 리턴
		log.info("BoardServiceImpl.modify() 메서드 실행");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		// bno를 받아 객체를 삭제하고,  db에서 int로 받은 결과를 boolean으로 리턴
		log.info("BoardServiceImpl.remove() 메서드 실행");
		return mapper.delete(bno) == 1;
	}

}
