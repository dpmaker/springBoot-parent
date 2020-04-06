package boot.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/t1")
	@ResponseBody
	public Object test1(){
		return "SUCCESS";
	}


	@RequestMapping("t2")
	@ResponseBody
	public Object test2(String name){
		return "SUCCESS";
	}
}
