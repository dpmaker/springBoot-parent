/**
 * 
 */
package boot;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import boot.start.example.TestStart2;

/**
 * @author d_mail_p@sina.com
 *
 *
 */

// 此处的两个注解为固定的形式
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootTest {

	// 此处引入mockMvc
	private MockMvc mockMvc;

	// 此处注入controller层
	@Autowired
	TestStart2 testStart2;

	// 初始化
	@Before
	public void setUp() throws Exception {
		System.out.println("执行初始化");
		// 此处初始化MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(testStart2).build();
	}

	// 此处测试mvc的控制层方法
	@Test
	public void methodA() throws Exception {

		// 此处构造request，并传入请求参数
		RequestBuilder request = MockMvcRequestBuilders.get("/start2/test3").contentType(MediaType.APPLICATION_JSON) // 发送所用的数据格式
				.accept(MediaType.APPLICATION_JSON) // 接收所使用的数据格式
				.param("id", "test2").param("name", "tom");

		// 此处获取响应
		ResultActions result = mockMvc.perform(request);

		// 此处处理并检测响应结果，最后返回一个对象
		MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk()) // 执行状态
				.andExpect(MockMvcResultMatchers.jsonPath("id").value("test2")) // 期望值
				.andExpect(MockMvcResultMatchers.jsonPath("name").value("tom"))
				.andExpect(MockMvcResultMatchers.jsonPath("data.account").value(100))
				.andDo(MockMvcResultHandlers.print()) // 打印
				.andReturn(); // 返回

		// 通过返回的对象，来获取返回值，可以再进行其他处理
		// {"data":{"account":100},"name":"tom","id":"test2"}
		String resultStr = mvcResult.getResponse().getContentAsString();

		System.out.println(resultStr);

	}

	// 此处测试普通方法
	@Test
	public void methodB() throws Exception {
		Map data = new HashMap();
		data.put("account", 100);
		//Map m = (Map) this.testStart2.test4(data);
		//Assert.assertTrue("通过主数据的标准化编码进行查询=>测试失败", m.get("account").equals(new Integer(100)));
	}

}
