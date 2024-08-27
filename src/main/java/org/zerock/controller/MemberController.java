package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller //Spring이 컨트롤러 역할
@Log4j2
@RequestMapping("/member/*") // http://localhost:80/member/??   -> 기본 url
@AllArgsConstructor //=new MemberController(service)
//@NoArgsConstructor
public class MemberController {
	//필드 : service 메서드 이용
	private MemberService service;
	
	//생성자 : @AllArgsConstructor ,@NoArgsConstructor
	
	//메서드
	//create : 회원가입(객체->int->boolean) -> 1. get으로 form 받기, 2. post로 객체 전송
	@GetMapping("/register") // http://localhost:80/member/register
	public void register() {
		//../WEB-INF/view/member/register.jsp 파일 리턴
	}
	
	@PostMapping("/register") // http://localhost:80/member/register
	//회원가입 후 성공-> login 페이지로/ 실패->되돌아오기
	public String register(MemberVO member, RedirectAttributes rttr) {
		log.info("MemberController.register() post 메서드 실행");
		boolean rst = service.register(member);
		//**unique 제약조건 걸리면 아래는 실해안됨-> 중복확인으로 필수
		log.info("입력된 객체 : "+member);
		String resultURL="";
		if(rst) {//가입성공하면
			rttr.addFlashAttribute("result", member.getNinckname());
			resultURL="redirect:/member/login";
		}else {
			rttr.addFlashAttribute("result", "error : 관리자에게 문의하세요.");
			resultURL="redirect:/member/register";
		}
		return resultURL;
	}
		
	//read_one1: 로그인(email, pw->객체)
	
		
	//read_one2: 회원정보 가져오기(nickname->객체)
		
	//read_one3: 회원유무, 닉네임 중복 검증(nickname->int)
			
	//update : 회원정보 변경(객체->int->boolean)
		
	//delete : 회원탈퇴(객체->int->boolean)
		

}
