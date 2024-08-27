package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //front test를 위한 servlet-context.xml 추가
@Log4j2
@WebAppConfiguration //front 영역 테스트용
public class BoardControllerTests {
	//필드 : tomcat, chrome 대신할 객체를 멤버변수로 사용
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx; //tomcat 대신 setCtx(WebApplicationContext)
	private MockMvc mockMvc; //chrome 대신
	
	@Before //import org.junit.Before; 구동 전에 선행해야 되는 코드 작성(front 없이 test 할 수 있는 환경)
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//----test용 코드
	
	@Test
	public void testlist() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	
	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "컨트롤러 테스트 제목")
				.param("content", "컨트롤러 테스트 내용")
				.param("writer", "컨트롤러"))
				.andReturn().getModelAndView().getViewName();
		log.info("결과 url : "+resultPage);	
		//결과 url : redirect:/board/list
	}
	
	@Test
	public void testGet() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "7"))
				.andReturn().getModelAndView().getModelMap());		
		//{findboard=BoardVO(bno=7, title=mapper에서 만든 제목, content=mapper에서 만든 내용, writer=mapper, regdate=Tue Aug 20 17:27:18 KST 2024, updateDate=Tue Aug 20 17:27:18 KST 2024)
	}
	

	@Test
	public void testModify() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "9276")
				.param("title", "컨트롤러 수정제목")
				.param("content", "컨트롤러 수정내용")
				.param("writer", "수정자con"))
				.andReturn().getModelAndView().getViewName();
		log.info("결과 url : "+resultPage);	
		//결과 url : redirect:/board/list
		
	}
	
	@Test
	public void testRemove() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "9272"))
				.andReturn().getModelAndView().getViewName();
		log.info("결과 url : "+resultPage);	
		
	}
	
	//페이징 처리 테스트
	@Test
	public void testListPaging() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "1")
						.param("amount", "5"))
				.andReturn().getModelAndView().getModelMap());
	}

}
