package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller //Spring이 컨트롤러 역할을 제공
@Log4j2
@RequestMapping("/board/*") // http://localhost:80/board/??   -> 기본 url 지정
@AllArgsConstructor //모든 필드값으로 생성자 만듦 -> BoardController(BoardService)
public class BoardController {
	//필드 : service 메서드 이용
	private BoardService service; //BoardController boardController = new BoardController(BoardService);
	
	// list 전체목록 : get , url 이동 없음 -> **페이징 처리 위해 아래 메서드로 교체
//	@GetMapping("/list") // http://localhost:80/board/list
//	public void list(Model model) { //import org.springframework.ui.Model; 스프링이 관리하는 메모리 영역
//		//스프링의 메서드 매개값은 값을 전달하는 통로역할-> 값을 가지고 프론트까지 전달한다.
//		log.info("BoardController.list() 메서드 실행");
//		model.addAttribute("list", service.getList()); 
//		//String name : list , Object value : List<BoardVO>		
//	}
	
	//list 전체목록 : get , url 이동 없음 + 페이징 처리
	@GetMapping("/list") // http://localhost:80/board/list?pageNum=1&amount=10
	public void list(Criteria cri, Model model) {
		log.info("BoardController.list() 메서드 실행");
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		log.info("게시글 개수(total):"+total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	// register 등록 : 화면 연결은 get
	@GetMapping("/register") //http://localhost:80/board/register
	public void register() {
	//void -> 	../WEB-INF/view/board/register.jsp (메서드와 같은 이름의 jsp를 찾는다.)
	}
	
	// register 등록 : post,  url 이동 있음(등록->list)
	@PostMapping("/register") // http://localhost:80/board/register
	public String register(BoardVO board, RedirectAttributes rttr) { //객체로 받은 form데이터, RedirectAttributes 객체 사용
		//RedirectAttributes : 1회성 값을 제공 addFlashAttribute("이름", "값")
		log.info("BoardController.register() 메서드 실행");
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno()); //객체의 bno 값을 1회성으로 model 영역에서 가지고 있음
		return "redirect:/board/list"; //== jsp : response.sendRedirect()
		//등록후 list 페이지로 보냄 
	}
	
	// read 조회 : get(bno) ,url 이동 없음
	@GetMapping({"/get", "/modify"}) //**수정
	//이중화 작업
	//..board/get?bno=3  , ..board/modify?bno=3  -> 두 가지 url을 받았을 때 아래 메소드 실행
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		//@RequestParam : url의 쿼리파라미터(?name=kkk)을 매개변수로 받기위해 사용
		// @ModelAttribute("cri") Criteria cri : 페이징처리를 위한 추가(명시적으로 이름지정)
		log.info("BoardController.get() 메서드 실행");
		model.addAttribute("findboard", service.get(bno));
		//name : findboard, value : BoardVO
	}
	
	// modify 수정 : post,  url 이동 있음(수정->list)
	@PostMapping("/modify") // http://localhost:80/board/modify
	public String modify(BoardVO board,  Criteria cri, RedirectAttributes rttr) {
		log.info("BoardController.modify() 메서드 실행");
		//service.modify(board)의 리턴타입 : boolean
		// @ModelAttribute("cri") Criteria cri : 페이징처리를 위한 추가(명시적으로 이름지정)
		if(service.modify(board)) {
			//변경성공시 메시지
			rttr.addFlashAttribute("result", "success");
		}else {
			//변경실패시 메시지
			rttr.addFlashAttribute("result", "fail");
		}
		//getListLink()이용으로 아래 비활성화
//		rttr.addAttribute("pageNum", cri.getPageNum()); //페이징처리를 위한 추가
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType()); //검색어 처리를 위한 추가
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list"+cri.getListLink();
	}
	
	// remove 삭제 : post,  url 이동 있음(삭제->list)
	@PostMapping("/remove") // http://localhost:80/board/remove
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("BoardController.remove() 메서드 실행");
		//service.remove(bno)의 리턴타입 : boolean
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}else {
			rttr.addFlashAttribute("result", "fail");
		}
		//getListLink()이용으로 아래 비활성화
//		rttr.addAttribute("pageNum", cri.getPageNum()); //페이징처리를 위한 추가
//		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list"+cri.getListLink();
	}

}
