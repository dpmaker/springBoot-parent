package mvc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test/mvc1/test")
public class TestController1 {

	@Autowired
	private TestService1 testService1;
	
	@ResponseBody
	@RequestMapping("/sayHello")
	public String sayHello(@RequestParam("name") String name,ModelMap model){
		model.put("name", name);
		this.testService1.sayHello(name);
		return this.testService1.sayHello(name);
	}
}
