package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service //스프링에서 서비스 객체로 관리
@Log4j2
public class ReplyServiceImpl implements ReplyService{
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapp;

	@Override
	public int register(ReplyVO reply) {
		log.info("ReplyServiceImpl.register() 메서드 실행");
		return mapp.insert(reply);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("ReplyServiceImpl.get() 메서드 실행");
		return mapp.read(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("ReplyServiceImpl.modify() 메서드 실행");
		return mapp.update(reply);
	}

	@Override
	public int remove(Long rno) {
		log.info("ReplyServiceImpl.remove() 메서드 실행");
		return mapp.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("ReplyServiceImpl.getList() 메서드 실행");
		return mapp.getListWithPaging(cri, bno);
	}

}
