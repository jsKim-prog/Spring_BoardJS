package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	//C : 등록-insert(in : 객체 -> out : int)
	public int insert(ReplyVO reply);
	
	//R : 상세보기-read(in : rno -> out : 객체)
	public ReplyVO read(Long rno);
	
	//U : 수정 -update(in : 객체(reply+rno) -> out : int)
	public int update(ReplyVO reply);
	
	//D : 삭제 - delete(in : rno -> out : int)
	public int delete(Long rno);
	
	//댓글 리스트 
	//@Param : MyBatis로 여러 파라미터를 전달하기 위한 방법->#{}으로 sql에서 받을 수 있다.
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

}
