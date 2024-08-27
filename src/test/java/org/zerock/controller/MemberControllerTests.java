package org.zerock.controller;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j2
@WebAppConfiguration
public class MemberControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx; //tomcat 대신 setCtx(WebApplicationContext)
	private MockMvc mockMvc; //chrome 대신
	
	@Before //import org.junit.Before; 구동 전에 선행해야 되는 코드 작성(front 없이 test 할 수 있는 환경)
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//----test용 메서드
	@Test //회원가입
	public void testRegister() throws Exception{
		String rstPage = mockMvc.perform(MockMvcRequestBuilders.get("/member/register")).andReturn().getModelAndView().getViewName();
		log.info("결과URL : "+rstPage);
		//결과URL : member/register
	}
	
	@Test
	public void testRegisterMem() throws Exception{
		String rstPage = mockMvc.perform(MockMvcRequestBuilders.post("/member/register")
				.param("ninckname", "tester02")
				.param("pw", "tester")
				.param("email", "test@test")
				.param("phone", "010-123-1234")
				.param("birth", "2024/08/24"))
				.andReturn().getModelAndView().getViewName();
		log.info("결과URL : "+rstPage);
		//결과URL : redirect:/member/login
		//입력된 객체 : MemberVO(ninckname=tester02, pw=tester, email=test@test, phone=010-123-1234, birth=2024/08/24, regdate=null)
	}
	
}
