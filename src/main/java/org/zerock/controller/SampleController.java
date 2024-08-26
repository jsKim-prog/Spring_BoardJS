package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j2;

@RestController //jsp가 아니라 순수한 데이터(xml, json)을 반환하는 controller
@RequestMapping("/sample") // http://localhost:80/sample/
@Log4j2
public class SampleController {
	// REST : Representational State Transfer -> uri를 통해 Method 가 반응한다.
	// Method는 get(select) / post(insert) / put(update) / delete(delete) 로 세분화됨
	// @RequestBody : 요청에 대한 처리(JSON 데이터를 원하는 타입으로 바인딩 처리)
	// @ResponseBody : 응답에 대한 처리(jsp로 view 처리가 아님 -> xml, json으로 보냄)
	// @PathVariable : URL(URI, 주소, 경로)에 있는 값을 파라미터로 추출
	// @CrossOrigin : AJAX(프론트)의 크로스 도메인 문제를 해결
	
	//1. Get
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8") // http://localhost:80/sample/getText
	//value=url, produces = 메서드가 생산하는 MIME 타입
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요. SampleController <br> 테스트"; //화면에 return값 그대로 반환
	}
	
	//1. Get - 객체의 반환(객체 -> json, xml)
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE}) 
	// http://localhost:80/sample/getSample ->xml
	// http://localhost:80/sample/getSample.json -> json으로 반환 {"mno":100,"firstName":"스타","lastName":"로드"}
	public SampleVO getSample() {
		return new SampleVO(100, "스타", "로드");
	}
	
	@GetMapping(value = "/getSample02")
	// http://localhost:80/sample/getSample02.json ->{"mno":101,"firstName":"MBC","lastName":"아카데미"}
	// produces 는 생략 가능
	public SampleVO getSample02() {
		return new SampleVO(101, "MBC", "아카데미");
	}
	
	//1. Get - 컬렉션 타입의 반환(List, Map -> json, xml)
	@GetMapping(value = "/getList") // http://localhost:80/sample/getList.json
	public List<SampleVO> getList(){
		return IntStream.rangeClosed(1, 10).mapToObj(i -> new SampleVO(i, i+"FirstN", i+"LastN")).collect(Collectors.toList());
		//IntStream.rangeClosed(1, 10) : 정수의 범위 생성
		//.map() : 각 요소에 주어진 함수를 적용하여 값을 변환
		//Stream.collect() : Stream의 아이템들을 List 또는 Set 자료형으로 변환
	}
	
	
	@GetMapping(value = "/getMap") // http://localhost:80/sample/getMap.json
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("ysy", new SampleVO(312, "1조", "조장"));
		map.put("mjh", new SampleVO(312, "1조", "조원1"));
		map.put("jyj", new SampleVO(312, "1조", "조원2"));
		map.put("kjs", new SampleVO(312, "1조", "조원3"));
		return map;
		//{"kjs":{"mno":312,"firstName":"1조","lastName":"조원3"},
		//"mjh":{"mno":312,"firstName":"1조","lastName":"조원1"},
		//"jyj":{"mno":312,"firstName":"1조","lastName":"조원2"},
		//"ysy":{"mno":312,"firstName":"1조","lastName":"조장"}}
	}
	
	//1. Get -  ResponseEntity : 헤더에 상태메시지 등을 같이 전달한다.
	@GetMapping(value = "/check", params = {"height", "weight"}) 
	// http://localhost:80/sample/check.json?height=140&weight=60 -> Status Code: 502 Bad Gateway
	// http://localhost:80/sample/check.json?height=200&weight=60 -> Status Code: 200 OK
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		//height < 150 -> 502 error 처리(502 Bad Gateway - 잘못된 응답 수신) , 나머지는 200 ok
		SampleVO vo = new SampleVO(0, ""+height, ""+weight); //height, weight에 "" 붙임 -> String 처리
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	//1. Get -  @PathVariable : URL 경로의 일부를 파라미터로 이용할 경우 -> (?+query string -> /{param}/ 형태로 처리)	
	@GetMapping("/product/{cat}/{pid}") 
	// http://localhost:80/sample/product/geaborm/200 -> {cat} = geaborm, {pid}=200 지정한 변수명에 변수값이 들어간다.(기본 자료형은 사용불가)
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] {"category : "+cat, "productid : "+pid};
		// .json : ["category : geaborm","productid : 200"]
	}
	
	//2. POST -  @RequestBody : 전달된 요청(request)의 내용(body)를 이용해서 해당 파라미터의 타입으로 변환을 요구->다양한 포맷의 입력데이터를 변환
	// json -> 객체 변환, 기타 데이터-> 원하는 타입
	// -> Junit 또는 POSTMAN으로 테스트
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket tc) {
		log.info("convert 메서드 실행 결과: " + tc);
		return tc;
	}
	
	

}
