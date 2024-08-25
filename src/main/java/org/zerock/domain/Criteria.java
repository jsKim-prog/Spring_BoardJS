package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria { //페이징 처리 - 검색기준
	//필드
	private int pageNum; //(현재)페이지 번호
	private int amount; //한 페이지당 보여줄 보드 개수
	//필드 : 검색기능 위한 추가
	private String type;
	private String keyword;
	
	//생성자
	public Criteria() { //기본 생성자 -> pageNum = 1, amount = 10 을 기본으로 입력하여 생성(아래 생성자 사용)
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//메서드
	public String[] getTypeArr() {
		//검색조건 : T(title), W(writer), C(content)
		//검색조건을 배열로 만들어 한번에 처리한다. -> mybatis의 동적태그 이용
		//검색조건이 없으면 배열을 만들고, 있으면 글자별로 배열 삽입
		
		return (type==null)? new String[] {} : type.split("");
	}
	
	//메서드 - 검색조건 유지를 위한 url 생성
	public String getListLink() {
		//UriComponentsBuilder : uri 생성해주면서 자동으로 인코딩해준다(한글처리도 자동)
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		return builder.toUriString(); //**toUriString() 주의!!(toString()아님)
		//..?pageNum=3&amount=20&type=TC&keyword=user
	}

}
