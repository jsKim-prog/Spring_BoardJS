package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j2
@WebAppConfiguration
public class SampleControllerTests {
	@Setter(onMethod_= @Autowired)
	private WebApplicationContext ctx; //tomcat 대리
	private MockMvc mockMvc; //chrome 대리
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //front 없이 테스트용
	}
	
	@Test
	public void testConvert() throws Exception{
		Ticket tc = new Ticket();
		tc.setTno(30);
		tc.setOwner("안재현");
		tc.setGrade("R");
		
		String jsonStr = new Gson().toJson(tc);
		log.info("객체->json:"+jsonStr); //객체->json:{"tno":30,"owner":"안재현","grade":"R"}
		
		mockMvc.perform(post("/sample/ticket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr)).andExpect(status().is(200));
		
		//convert 메서드 실행 결과: Ticket(tno=30, owner=안재현, grade=R)
		//postman(tomcat 실행결과) : convert 메서드 실행 결과: Ticket(tno=33, owner=표지훈, grade=S)
	}

}
