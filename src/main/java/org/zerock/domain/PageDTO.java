package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO { //페이징 처리를 위한 클래스
	//필드
	private int startPage;
	private int endPage;
	private boolean prev, next;
	//private int blocknum; //한 블록당 보여줄 페이지 번호 개수
	
	private int total; //전체 페이지 수
	private Criteria cri; //검색기준(현재페이지 번호, 한페이지당 보드개수)
	
	//생성자
	public PageDTO() {} //기본생성자
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		//this.blocknum = 10;
		
		//this.endPage = (int)(Math.ceil(cri.getPageNum()/cri.getAmount()))*cri.getAmount();
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = this.endPage - 9;
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		if(realEnd<this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	

}
