/**
 * 
 */
package boot.mockmvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import boot.jdbc.mybatis.pagehelper.TestController;
import boot.start.example.TestStart2;

/**
 * @author d_mail_p@sina.com
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {
	
	// 此处引入mockMvc
		private MockMvc mockMvc;

		// 此处注入controller层
		@Autowired
		TestController testController;

		// 初始化
		@Before
		public void setUp() throws Exception {
			System.out.println("执行初始化");
			// 此处初始化MockMvc
			mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
		}

		// 此处测试mvc的控制层方法
		@Test
		public void methodA() throws Exception {

			// 此处构造request，并传入请求参数
			RequestBuilder request = MockMvcRequestBuilders.get("/pagehelper/test").contentType(MediaType.APPLICATION_JSON) // 发送所用的数据格式
					.accept(MediaType.APPLICATION_JSON) // 接收所使用的数据格式
					.param("pageNum", "6").param("pageSize", "2");

			// 此处获取响应
			ResultActions result = mockMvc.perform(request);

			// 此处处理并检测响应结果，最后返回一个对象
			MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk()) // 执行状态
					.andExpect(MockMvcResultMatchers.jsonPath("pageNum").value(4)) // 期望值
					//.andExpect(MockMvcResultMatchers.jsonPath("name").value("tom"))
					//.andExpect(MockMvcResultMatchers.jsonPath("data.account").value(100))
					.andDo(MockMvcResultHandlers.print()) // 打印
					.andReturn(); // 返回

			// 通过返回的对象，来获取返回值，可以再进行其他处理
			// {"data":{"account":100},"name":"tom","id":"test2"}
			String resultStr = mvcResult.getResponse().getContentAsString();

			System.out.println(resultStr);

		}


}
